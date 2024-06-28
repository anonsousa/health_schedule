package br.com.healthcare.schedule.domain.service;

import br.com.healthcare.schedule.domain.dtos.MedicoAddDto;
import br.com.healthcare.schedule.domain.dtos.MedicoReturnDto;
import br.com.healthcare.schedule.domain.enums.EnumEspecialidade;
import br.com.healthcare.schedule.domain.entities.MedicoEntity;
import br.com.healthcare.schedule.domain.repositories.MedicoRepository;
import br.com.healthcare.schedule.infra.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<MedicoReturnDto> findAllMedicos(Pageable pageable){
        Page<MedicoEntity> medicos = medicoRepository.findAll(pageable);
        return medicos.map(MedicoReturnDto::new);
    }

    @Transactional(readOnly = true)
    public MedicoReturnDto findOneMedico(Long id){
        var medico = medicoRepository.findById(id);
        if (medico.isPresent()){
            return new MedicoReturnDto(medico.get());
        } else {
            throw new NotFoundException("Medico nao encontrado!");
        }
    }

    @Transactional(readOnly = true)
    public Page<MedicoReturnDto> findMedicosByEspecialidade(EnumEspecialidade especialidade, Pageable pageable){
        Page<MedicoEntity> medico = medicoRepository.findByEspecialidade(especialidade, pageable);
        return medico.map(MedicoReturnDto::new);
    }

    @Transactional
    public MedicoReturnDto updateMedico(Long id, MedicoAddDto medicoDto){
        var medico = medicoRepository.findById(id);
        if (medico.isPresent() || medico.get().isAtivo() == true){
            MedicoEntity medicoEntity = medico.get();
            BeanUtils.copyProperties(medicoDto, medicoEntity);

            return new MedicoReturnDto(medicoRepository.save(medicoEntity));
        } else {
            throw new NotFoundException("Medico nao encontrado!");
        }
    }

    @Transactional
    public void deleteMedicoById(Long id){
        var medico = medicoRepository.findById(id);
        if (medico.isPresent()){
            MedicoEntity medicoEntity = medico.get();
            medicoEntity.setAtivo(false);
            medicoRepository.save(medicoEntity);
        } else {
            throw new NotFoundException("Medico nao encontrado!");
        }
    }

















}
