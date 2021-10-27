package com.padillatomas.icons.icons.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padillatomas.icons.icons.dto.ContinenteDTO;
import com.padillatomas.icons.icons.entity.ContinenteEntity;
import com.padillatomas.icons.icons.mapper.ContinenteMapper;
import com.padillatomas.icons.icons.repository.ContinenteRepository;
import com.padillatomas.icons.icons.service.ContinenteService;

@Service
public class ContinenteServiceImpl implements ContinenteService {

	// Instanciamos: Mapper
	@Autowired
	private ContinenteMapper contMapper;
	
	// Instanciamos: Repository
	@Autowired
	private ContinenteRepository contRepo;	
	
	// === POST ===
	@Override
	public ContinenteDTO guardarContinente(ContinenteDTO dto) {
		// Guardar
		ContinenteEntity contEntity = contMapper.continenteDTO2Entity(dto);		
		ContinenteEntity savedEntity = contRepo.save(contEntity);		
		ContinenteDTO resultadoDto = contMapper.continenteEntity2DTO(savedEntity);
		
		// Devolver
		return resultadoDto;		
	}

	// === GET ===
	@Override
	public List<ContinenteDTO> getAllContinentes() {
		// Pedir
		List<ContinenteEntity> contEnt = contRepo.findAll();
		List<ContinenteDTO> resultadoDto = contMapper.continenteEntityList2DTOList(contEnt);
		
		return resultadoDto;
	}
	
	

}
