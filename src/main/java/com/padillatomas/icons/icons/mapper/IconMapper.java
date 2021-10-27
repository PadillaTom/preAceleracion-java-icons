package com.padillatomas.icons.icons.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.padillatomas.icons.icons.dto.IconBasicDTO;
import com.padillatomas.icons.icons.dto.IconDTO;
import com.padillatomas.icons.icons.dto.PaisDTO;
import com.padillatomas.icons.icons.entity.IconEntity;
import com.padillatomas.icons.icons.entity.PaisEntity;

@Component
public class IconMapper {
	
	@Autowired
	private PaisMapper paisMapper;	
	
	//
	// === DTO -> Entity ===
	public IconEntity iconDTO2IconEntity(IconDTO dto) {
		IconEntity newEntity = new IconEntity();
		
		newEntity.setImagen(dto.getImagen());
		newEntity.setDenominacion(dto.getDenominacion());
		
		String dtoDate = dto.getFechaCreacion();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate transformedDate = LocalDate.parse(dtoDate, formatter);
		
		// Cast STRING to DATE (LocalDate)
		newEntity.setFechaCreacion(transformedDate);
		
		newEntity.setAltura(dto.getAltura());
		newEntity.setHistoria(dto.getHistoria());
		
		return newEntity;		
	}
	
	//
	// === Entity -> DTO ===
	public IconDTO iconEntity2DTO(IconEntity icon, boolean fetchPaises) {
		IconDTO newDTO = new IconDTO();
		
		newDTO.setId(icon.getId());
		newDTO.setImagen(icon.getImagen());
		newDTO.setDenominacion(icon.getDenominacion());
		newDTO.setFechaCreacion(icon.getFechaCreacion().toString());
		newDTO.setHistoria(icon.getHistoria());
		newDTO.setAltura(icon.getAltura());
		
		if(fetchPaises) {			
			// Pais for each to DTO
			List<PaisDTO> myList = new ArrayList<>();
			for(PaisEntity ent : icon.getPaises()) {
				myList.add(paisMapper.paisEntity2DTO(ent, false));
			}
			newDTO.setPaises(myList);	
		}
		
		return newDTO;
	}	
	
	//
	// === List<Entity> -> List<DTO> ===
	public List<IconDTO> iconEntityList2ListDTO(List<IconEntity> myList, boolean fetchPaises) {
		List<IconDTO> newList = new ArrayList<IconDTO>();
		
		for(IconEntity ent : myList) {
			newList.add(iconEntity2DTO(ent, fetchPaises));
		}
		
		return newList;
	}
	
	//
	// === Set<DTO> -> Set<Entity> ===
	public Set<IconEntity> iconDTOSet2EntitySet(Set<IconDTO> mySet) {
		Set<IconEntity> newSet = new HashSet<IconEntity>();
		
		for(IconDTO dto : mySet) {
			newSet.add(iconDTO2IconEntity(dto));
		}
		
		return newSet;
	}

	
	// ****************
	// 	  BASIC ICON
	// ****************
	
	//
	// === Entity -> DTO ===
	public IconBasicDTO iconEntity2IconBasicDTO(IconEntity entity) {
		IconBasicDTO newBasicDTO = new IconBasicDTO();
		
		newBasicDTO.setImagen(entity.getImagen());
		newBasicDTO.setDenominacion(entity.getDenominacion());
		
		return newBasicDTO;
		
	}
	
	//
	// === List<Entity> -> List<DTO> ===
	public List<IconBasicDTO> iconBasicEntityList2ListBasicDTO(List<IconEntity> myList) {
		List<IconBasicDTO> basicList = new ArrayList<>();
		
		for (IconEntity ent : myList) {
			basicList.add(iconEntity2IconBasicDTO(ent));
		}
		return basicList;
	}
	
	//
	// === List<DTO> -> List<Entity> ===
	
	
}
