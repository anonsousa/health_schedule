package br.com.healthcare.schedule.domain.service;

import br.com.healthcare.schedule.domain.dtos.PacienteAddDto;
import br.com.healthcare.schedule.domain.dtos.PacienteReturnDto;
import br.com.healthcare.schedule.domain.entities.Endereco;
import br.com.healthcare.schedule.domain.entities.PacienteEntity;
import br.com.healthcare.schedule.domain.repositories.PacienteRepository;
import br.com.healthcare.schedule.infra.exceptions.NotFoundException;
import jakarta.mail.MessagingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MailSenderService mailSender;

    @Transactional
    public PacienteReturnDto savePaciente(PacienteAddDto pacienteAddDto){
        var paciente = new PacienteEntity();
        BeanUtils.copyProperties(pacienteAddDto, paciente, "endereco");

        var endereco = new Endereco();
        BeanUtils.copyProperties(pacienteAddDto.endereco(), endereco);

        paciente.setEndereco(endereco);

        try {
            mailSender.sendWelcomeEmail(paciente.getEmail(), paciente.getNome());
        } catch (MessagingException e){
            throw new RuntimeException("Error" + e.getMessage(), e);
        }

        return new PacienteReturnDto(pacienteRepository.save(paciente));
    }

    @Transactional(readOnly = true)
    public PacienteReturnDto findOnePaciente(Long id){
        var paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()){
            return new PacienteReturnDto(paciente.get());
        } else {
            throw new NotFoundException("Paciente nao encontrado!");
        }
    }

    @Transactional(readOnly = true)
    public Page<PacienteReturnDto> findAllPacientes(Pageable pageable){
        Page<PacienteEntity> pacienteEntities = pacienteRepository.findAll(pageable);
        return pacienteEntities.map(PacienteReturnDto::new);
    }
}
