package com.padillatomas.icons.icons.service;

import java.util.List;

import com.padillatomas.icons.icons.dto.ContinenteDTO;

public interface ContinenteService {

	ContinenteDTO guardarContinente(ContinenteDTO dto);
	
	List<ContinenteDTO> getAllContinentes();
	
}


