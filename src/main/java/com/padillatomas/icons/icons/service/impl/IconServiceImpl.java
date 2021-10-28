package com.padillatomas.icons.icons.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padillatomas.icons.icons.dto.IconBasicDTO;
import com.padillatomas.icons.icons.dto.IconDTO;
import com.padillatomas.icons.icons.entity.IconEntity;
import com.padillatomas.icons.icons.mapper.IconMapper;
import com.padillatomas.icons.icons.mapper.PaisMapper;
import com.padillatomas.icons.icons.repository.IconRepository;
import com.padillatomas.icons.icons.service.IconService;

@Service
public class IconServiceImpl implements IconService {
	
	// Instanciamos: Mapper
	@Autowired
	private IconMapper iconMapper;
	@Autowired
	private PaisMapper paisMapper;
	
	// Instanciamos: Repository
	@Autowired
	private IconRepository iconRepo;

	// === GET ===
	@Override
	public List<IconBasicDTO> getAllIcons() {
		List<IconEntity> myList =  iconRepo.findAll();
		List<IconBasicDTO> listBasicDTO = iconMapper.iconBasicEntityList2ListBasicDTO(myList);
		return listBasicDTO;
	}
	
	@Override
	public List<IconDTO> getAllIconDetails() {
		List<IconEntity> myList =  iconRepo.findAll();
		List<IconDTO> listDTO = iconMapper.iconEntityList2ListDTO(myList, true);
		return listDTO;
	}
	
	// === DELETE ===	
	@Override
	public void deleteIcon(Long id) {
		iconRepo.deleteById(id);		
	}

	// === PUT ===		
	@Override
	public IconDTO editIcon(Long id, IconDTO iconToEdit) {
		IconEntity savedIcon = iconRepo.getById(id);
		
		// Cast String to Date:
		String dateDTO = iconToEdit.getFechaCreacion().toString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate transformedDate = LocalDate.parse(dateDTO, formatter);
		
		// SET:
		savedIcon.setImagen(iconToEdit.getImagen());
		savedIcon.setDenominacion(iconToEdit.getDenominacion());
		savedIcon.setFechaCreacion(transformedDate);
		savedIcon.setAltura(iconToEdit.getAltura());
		savedIcon.setHistoria(iconToEdit.getHistoria());
		savedIcon.setPaises(paisMapper.paisDTOList2EntityList(iconToEdit.getPaises(), false));	
				
		IconEntity editedIcon = iconRepo.save(savedIcon);	
		
		IconDTO savedDTO = iconMapper.iconEntity2DTO(editedIcon, false);
		
		return savedDTO;
	}

	


	

}
