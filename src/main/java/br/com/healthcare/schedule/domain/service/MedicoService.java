package br.com.healthcare.schedule.domain.service;

import br.com.healthcare.schedule.domain.dtos.MedicoAddDto;
import br.com.healthcare.schedule.domain.dtos.MedicoReturnDto;
import br.com.healthcare.schedule.domain.entities.MedicoEntity;
import br.com.healthcare.schedule.domain.repositories.MedicoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    public MedicoReturnDto saveMedico(MedicoAddDto medicoAddDto){
        var medico = new MedicoEntity();
        BeanUtils.copyProperties(medicoAddDto, medico);
        medico.setAtivo(true);

        return new MedicoReturnDto(medicoRepository.save(medico));
    }
}
