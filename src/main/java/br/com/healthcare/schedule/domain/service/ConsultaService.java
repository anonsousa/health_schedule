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
import br.com.healthcare.schedule.infra.exceptions.NotFoundException;
import br.com.healthcare.schedule.infra.notification.MailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ConsultaReturnDto createConsulta(ConsultaAddDto consultaAddDto){

        var schedule = scheduleValidation.isScheduleValid(consultaAddDto.dataConsulta(), consultaAddDto.dataConsulta().plusHours(1));

        if (schedule){

            List<MedicoEntity> medicoEntityList = medicoRepository.findMedicosDisponiveis(consultaAddDto.especialidade(),
                    consultaAddDto.dataConsulta(),
                    consultaAddDto.dataConsulta().plusHours(1));

            if (!medicoEntityList.isEmpty()){
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
                } catch (MessagingException e){
                    throw new RuntimeException("Failed to send message: "+ e.getMessage(), e);
                }

                return new ConsultaReturnDto(consulta);

            } else {
                throw new NotFoundException(String.format("Nao temos medico com a especialidade %s para a data solicitada!", consultaAddDto.especialidade()));
            }
        } else {
            throw new NotFoundException("Data da consulta invalida!");
        }
    }

    @Transactional
    public ConsultaReturnDto finishConsulta(Long id){

        var consulta = consultaRepository.findById(id);

        if (consulta.isPresent()){
            ConsultaEntity consultaEntity = consulta.get();
            consultaEntity.setStatus(EnumStatus.REALIZADA);
            return new ConsultaReturnDto(consultaEntity);
        } else {
            throw new NotFoundException("Consulta nao encontrada!");
        }
    }

    @Transactional
    public ConsultaReturnDto unmarkConsulta(Long id){

        var consulta = consultaRepository.findById(id);

        if (consulta.isPresent()){
            ConsultaEntity consultaEntity = consulta.get();
            consultaEntity.setStatus(EnumStatus.DESMARCADA);
            return new ConsultaReturnDto(consultaEntity);
        } else {
            throw new NotFoundException("Consulta nao encontrada!");
        }
    }











}
