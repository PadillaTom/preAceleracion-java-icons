package com.padillatomas.icons.icons.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padillatomas.icons.icons.dto.PaisBasicDTO;
import com.padillatomas.icons.icons.dto.PaisDTO;
import com.padillatomas.icons.icons.entity.PaisEntity;
import com.padillatomas.icons.icons.mapper.PaisMapper;
import com.padillatomas.icons.icons.repository.PaisRepository;
import com.padillatomas.icons.icons.service.PaisService;

@Service
public class PaisServiceImpl implements PaisService {	
	
	// Instanciamos: Mapper
	@Autowired
	private PaisMapper paisMapper;
	
	// Instanciamos: Repository
	@Autowired
	private PaisRepository paisRepo;		
		
	// ********
	// Methods:
	// ********
	
	// === POST ===
	@Override
	public PaisDTO guardarPais(PaisDTO dto) {
		PaisEntity nuevaEntity = paisMapper.paisDTO2Entity(dto);
		PaisEntity savedEntity = paisRepo.save(nuevaEntity);
		PaisDTO resultadoDTO = paisMapper.paisEntity2DTO(savedEntity, false);
		return resultadoDTO;
	}
	
	// === GET ===
	@Override
	public List<PaisBasicDTO> getAllPaisBasic() {
		List<PaisEntity> entityList = paisRepo.findAll();
		List<PaisBasicDTO> basicDTOList = paisMapper.paisEntityList2DTOBasicList(entityList);		
		return basicDTOList;
	}

	@Override
	public List<PaisDTO> getAllPaises() {
		List<PaisEntity> entityList = paisRepo.findAll();
		List<PaisDTO> listDTO = paisMapper.paisEntityList2DTOList(entityList, true);
		
		return listDTO;
	}

	// === DELETE ===
	@Override
	public void deletePais(Long id) {
		paisRepo.deleteById(id);
		
	}
	
	
	// === PUT ===
}
