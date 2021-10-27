package com.padillatomas.icons.icons.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.padillatomas.icons.icons.dto.IconDTO;
import com.padillatomas.icons.icons.dto.PaisBasicDTO;
import com.padillatomas.icons.icons.dto.PaisDTO;
import com.padillatomas.icons.icons.entity.IconEntity;
import com.padillatomas.icons.icons.entity.PaisEntity;

@Component
public class PaisMapper {
	
	// Instanciar IconMapper:
	@Autowired
	private IconMapper iconMapper;

	//
	// === DTO -> Entity ===
	public PaisEntity paisDTO2Entity(PaisDTO dto) {
		
		PaisEntity newEntity = new PaisEntity();
		
		newEntity.setImagen(dto.getImagen());
		newEntity.setDenominacion(dto.getDenominacion());
		newEntity.setCantidadHabitantes(dto.getCantidadHabitantes());
		newEntity.setSuperficie(dto.getSuperficie());
		
		// "Ingrese Continente ID:"
		newEntity.setContinenteId(dto.getContinenteId());	
		
		// Transformo ICON a Entity (Dentro de cada getIcons());
		Set<IconEntity> myList = new HashSet<>();
		for(IconDTO icon : dto.getIcons()) {
			myList.add(iconMapper.iconDTO2IconEntity(icon));
		}
		newEntity.setIcons(myList);		
		
		return newEntity;
	}
	
	//
	// === Entity -> DTO ===
	public PaisDTO paisEntity2DTO(PaisEntity savedEntity, boolean fetchIcons) {
		
		PaisDTO newDTO = new PaisDTO();
		
		newDTO.setId(savedEntity.getId());
		newDTO.setImagen(savedEntity.getImagen());
		newDTO.setDenominacion(savedEntity.getDenominacion());
		newDTO.setCantidadHabitantes(savedEntity.getCantidadHabitantes());
		newDTO.setSuperficie(savedEntity.getSuperficie());
		newDTO.setContinenteId(savedEntity.getContinenteId());
		
		if(fetchIcons) {			
			// Transformo ICON a Entity (Dentro de cada getIcons());
			List<IconDTO> myList = new ArrayList<>();
			for(IconEntity icon : savedEntity.getIcons()) {
				myList.add(iconMapper.iconEntity2DTO(icon, false));
				newDTO.setIcons(myList);		
			}
		}
		
		return newDTO;
	}
	
	//
	// === List<Entity> -> List<DTO> ===
	public List<PaisDTO> paisEntityList2DTOList(List<PaisEntity> entityList, boolean b) {
		List<PaisDTO> newList = new ArrayList<>();
		
		for(PaisEntity ent : entityList) {
			newList.add(paisEntity2DTO(ent, b));
		}
		
		return newList;
	}
	

	// ****************
	// 	  BASIC ICON
	// ****************
	
	//
	// === Entity -> DTO ===
	public PaisBasicDTO paisEntity2BasicDTO(PaisEntity entity) {
		PaisBasicDTO newBasic = new PaisBasicDTO();
		
		newBasic.setImagen(entity.getImagen());
		newBasic.setDenominacion(entity.getDenominacion());
		newBasic.setCantidadHabitantes(entity.getCantidadHabitantes());
		
		return newBasic;		
	}
	
	//
	// === List<Entity> -> List<BasicDTO> ===
	public List<PaisBasicDTO> paisEntityList2DTOBasicList(List<PaisEntity> myList) {
		List<PaisBasicDTO> newList = new ArrayList<>();
		
		for(PaisEntity ent : myList) {
			newList.add(paisEntity2BasicDTO(ent));
		}
		return newList;
	}

	
	
	//
	// === Entity -> DTO ===

}
