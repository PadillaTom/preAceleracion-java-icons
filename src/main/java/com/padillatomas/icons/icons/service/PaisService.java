package com.padillatomas.icons.icons.service;

import java.util.List;

import com.padillatomas.icons.icons.dto.PaisBasicDTO;
import com.padillatomas.icons.icons.dto.PaisDTO;
import com.padillatomas.icons.icons.entity.PaisEntity;

public interface PaisService {

	PaisDTO guardarPais(PaisDTO dto);

	List<PaisBasicDTO> getAllPaisBasic();

	List<PaisDTO> getAllPaises();
	
	void deletePais(Long id);

	PaisDTO editPais(Long id, PaisDTO paisToEdit);

	void addIcon(Long paisId, Long iconId);

	void removeIconFromPais(Long paisId, Long iconId);

	List<PaisDTO> getByFilters(String name, String continent, String order);
	
	PaisDTO getPaisDetailsById(Long id);
	
	PaisEntity getPaisEntityById(Long paisId);

}
