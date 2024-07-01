package br.com.healthcare.schedule.domain.service;

import br.com.healthcare.schedule.domain.dtos.ConsultaAddDto;
import br.com.healthcare.schedule.domain.dtos.ConsultaReturnDto;
import br.com.healthcare.schedule.domain.entities.ConsultaEntity;
import br.com.healthcare.schedule.domain.entities.MedicoEntity;
import br.com.healthcare.schedule.domain.entities.PacienteEntity;
import br.com.healthcare.schedule.domain.enums.EnumStatus;
import br.com.healthcare.schedule.domain.repositories.ConsultaRepository;
import br.com.healthcare.schedule.domain.repositories.MedicoRepository;
import br.com.healthcare.schedule.domain.repositories.PacienteRepository;
import br.com.healthcare.schedule.domain.validations.ScheduleValidation;
import br.com.healthcare.schedule.infra.exceptions.InvalidRequestException;
import br.com.healthcare.schedule.infra.exceptions.NotFoundException;
import br.com.healthcare.schedule.infra.notification.MailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MailSenderService mailSender;

    @Autowired
    private ScheduleValidation scheduleValidation;

    @Transactional
    public ConsultaReturnDto createConsulta(ConsultaAddDto consultaAddDto) {


        var schedule = scheduleValidation.isScheduleValid(consultaAddDto.dataConsulta(), consultaAddDto.dataConsulta().plusHours(1));
        var pacienteFreeSchedule = pacienteRepository.existsConsultaAgendadaByPacienteEHorario(
                consultaAddDto.idPaciente(),
                consultaAddDto.dataConsulta(),
                consultaAddDto.dataConsulta().plusHours(1));

        if (!pacienteFreeSchedule) {

            if (schedule) {

                List<MedicoEntity> medicoEntityList = medicoRepository.findMedicosDisponiveis(consultaAddDto.especialidade(),
                        consultaAddDto.dataConsulta(),
                        consultaAddDto.dataConsulta().plusHours(1));

                if (!medicoEntityList.isEmpty()) {
                    var medico = medicoEntityList.getFirst();

                    PacienteEntity paciente = pacienteRepository.findById(consultaAddDto.idPaciente()).orElseThrow(() -> new NotFoundException("Paciente nao encontrado"));

                    var consulta = new ConsultaEntity();
                    consulta.setMedico(medico);
                    consulta.setPaciente(paciente);
                    consulta.setEspecialidade(consultaAddDto.especialidade());
                    consulta.setDataInicioConsulta(consultaAddDto.dataConsulta());
                    consulta.setDataFimConsulta(consultaAddDto.dataConsulta().plusHours(1));
                    consulta.setStatus(EnumStatus.AGENDADA);

                    consultaRepository.save(consulta);

                    try {
                        mailSender.sendScheduledAppointment(consulta.getIdConsulta(),
                                paciente.getEmail(),
                                paciente.getNome(),
                                medico.getNome(),
                                String.valueOf(consulta.getDataInicioConsulta()));
                    } catch (MessagingException e) {
                        throw new RuntimeException("Failed to send message: " + e.getMessage(), e);
                    }

                    return new ConsultaReturnDto(consulta);

                } else {
                    throw new NotFoundException(String.format("Nao temos medico com a especialidade %s para a data solicitada!", consultaAddDto.especialidade()));
                }
            } else {
                throw new InvalidRequestException("Data da consulta invalida");
            }
        } else {
            throw new InvalidRequestException("Paciente ja tem consulta no horario!");
        }
    }



    @Transactional
    public ConsultaReturnDto finishConsulta(Long id){

        var consulta = consultaRepository.findById(id);

        if (consulta.isPresent()){
            if (consulta.get().getStatus() == EnumStatus.AGENDADA){
                ConsultaEntity consultaEntity = consulta.get();
                consultaEntity.setStatus(EnumStatus.REALIZADA);
                return new ConsultaReturnDto(consultaEntity);
            } else {
                throw new InvalidRequestException("Consulta ja foi finalizada!");
            }
        } else {
            throw new NotFoundException("Consulta nao encontrada!");
        }
    }

    @Transactional
    public ConsultaReturnDto unmarkConsulta(Long id){

        var consulta = consultaRepository.findById(id);

        if (consulta.isPresent()){
            if (consulta.get().getStatus() == EnumStatus.AGENDADA){
                ConsultaEntity consultaEntity = consulta.get();
                consultaEntity.setStatus(EnumStatus.DESMARCADA);
                return new ConsultaReturnDto(consultaEntity);
            } else {
                throw new InvalidRequestException("Consulta ja foi finalizada!");
            }
        } else {
            throw new NotFoundException("Consulta nao encontrada!");
        }
    }

    @Transactional(readOnly = true)
    public Page<ConsultaReturnDto> findAllConsultasByYearAndMonth(int ano, int mes, Pageable pageable) {
        Page<ConsultaEntity> consultaPage = consultaRepository.findConsultasByMesEAno(ano, mes, pageable);
        return consultaPage.map(ConsultaReturnDto::new);
    }

    @Transactional(readOnly = true)
    public ConsultaReturnDto findConsultaById(Long id){
        var consulta = consultaRepository.findById(id);

        if (consulta.isPresent()){
            return new ConsultaReturnDto(consulta.get());
        }
        throw new NotFoundException("Consulta nao encontrada!");
    }











}
