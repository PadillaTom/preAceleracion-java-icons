package com.padillatomas.icons.icons.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.padillatomas.icons.icons.dto.ContinenteDTO;
import com.padillatomas.icons.icons.entity.ContinenteEntity;

@Component
public class ContinenteMapper {
	
	//
	// === DTO -> Entity ===
	public ContinenteEntity continenteDTO2Entity(ContinenteDTO dto) {		
		ContinenteEntity contEntity = new ContinenteEntity();	
				
		contEntity.setImagen(dto.getImagen());
		contEntity.setDenominacion(dto.getDenominacion());
		
		return contEntity;
	}
	
	//
	// === Entity -> DTO ===
	public ContinenteDTO continenteEntity2DTO(ContinenteEntity entity) {
		ContinenteDTO contDto = new ContinenteDTO();
		
		contDto.setId(entity.getId());
		contDto.setImagen(entity.getImagen());
		contDto.setDenominacion(entity.getDenominacion());
		
		return contDto;
	}
	
	//
	// === List<Entity> ->  List<DTO> ===
	public List<ContinenteDTO> continenteEntityList2DTOList(List<ContinenteEntity> contEnt) {
		List<ContinenteDTO> dtoList = new ArrayList<>();
		
		for(ContinenteEntity ent : contEnt) {
			dtoList.add(continenteEntity2DTO(ent));
		}
		
		return dtoList;
	};
	

}
