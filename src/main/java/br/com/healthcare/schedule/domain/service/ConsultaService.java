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
import br.com.healthcare.schedule.infra.exceptions.NotFoundException;
import br.com.healthcare.schedule.infra.notification.MailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public ConsultaReturnDto createConsulta(ConsultaAddDto consultaAddDto){

        MedicoEntity medico = medicoRepository.findById(consultaAddDto.idMedico()).orElseThrow(() -> new NotFoundException("Medico nao encontrado"));
        PacienteEntity paciente = pacienteRepository.findById(consultaAddDto.idPaciente()).orElseThrow(() -> new NotFoundException("Paciente nao encontrado"));

        var consulta = new ConsultaEntity();
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataConsulta(consultaAddDto.dataConsulta());
        consulta.setStatus(EnumStatus.AGENDADA);

        consultaRepository.save(consulta);

        try {
            mailSender.sendScheduledAppointment(consulta.getIdConsulta(),
                    paciente.getEmail(),
                    paciente.getNome(),
                    medico.getNome(),
                    String.valueOf(consulta.getDataConsulta()));
        } catch (MessagingException e){
            throw new RuntimeException("Failed to send message: "+ e.getMessage(), e);
        }

        return new ConsultaReturnDto(consulta);

    }















}
