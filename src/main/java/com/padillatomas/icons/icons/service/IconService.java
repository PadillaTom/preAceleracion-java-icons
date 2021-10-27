package com.padillatomas.icons.icons.service;

import java.util.List;

import com.padillatomas.icons.icons.dto.IconBasicDTO;
import com.padillatomas.icons.icons.dto.IconDTO;


public interface IconService {
	
	void deleteIcon(Long id);

	List<IconBasicDTO> getAllIcons();

	List<IconDTO> getAllIconDetails();
}
