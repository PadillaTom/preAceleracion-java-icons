package com.padillatomas.icons.icons.service;

import java.util.List;

import com.padillatomas.icons.icons.dto.PaisBasicDTO;
import com.padillatomas.icons.icons.dto.PaisDTO;

public interface PaisService {

	PaisDTO guardarPais(PaisDTO dto);

	List<PaisBasicDTO> getAllPaisBasic();

	List<PaisDTO> getAllPaises();

	void deletePais(Long id);

}
