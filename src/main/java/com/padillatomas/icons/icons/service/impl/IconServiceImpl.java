package com.padillatomas.icons.icons.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padillatomas.icons.icons.dto.IconBasicDTO;
import com.padillatomas.icons.icons.dto.IconDTO;
import com.padillatomas.icons.icons.dto.IconFiltersDTO;
import com.padillatomas.icons.icons.entity.IconEntity;
import com.padillatomas.icons.icons.mapper.IconMapper;
import com.padillatomas.icons.icons.mapper.PaisMapper;
import com.padillatomas.icons.icons.repository.IconRepository;
import com.padillatomas.icons.icons.repository.specifications.IconSpecification;
import com.padillatomas.icons.icons.service.IconService;

@Service
public class IconServiceImpl implements IconService {
	
	// Instanciamos: Mapper
	@Autowired
	private IconMapper iconMapper;
	@Autowired
	private PaisMapper paisMapper;	
	@Autowired
	private IconSpecification iconSpecs;
	
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
	

	@Override
	public List<IconDTO> getByFilters(String name, String date, List<Long> cities, String order) {
		IconFiltersDTO filtersDTO = new IconFiltersDTO(name, date, cities, order);
		
		// Mandamos al Repo -> traer todo con las Specifications declaradas en IconSpecifications.
		List<IconEntity> entities = iconRepo.findAll(iconSpecs.getByFilters(filtersDTO));
		
		// pasamos a DTO la lista retornada por el repo.
		List<IconDTO> resultDTO = iconMapper.iconEntityList2ListDTO(entities, true);
		
		return resultDTO;
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

	// === POST ===	
	@Override
	public IconDTO saveIcon(IconDTO dto) {
		IconEntity newIcon = new IconEntity();
		
		newIcon.setImagen(dto.getImagen());
		newIcon.setDenominacion(dto.getDenominacion());
		
		// Cast String to Date:
		String dateDTO = dto.getFechaCreacion();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate transformedDate = LocalDate.parse(dateDTO, formatter);
		newIcon.setFechaCreacion(transformedDate);
		
		newIcon.setAltura(dto.getAltura());
		newIcon.setHistoria(dto.getHistoria());
		
		IconEntity savedIcon = iconRepo.save(newIcon);
		
		IconDTO savedDTO = iconMapper.iconEntity2DTO(savedIcon, false);
		
		return savedDTO;
	}
	
	
	// Methods:	
	@Override
	public IconEntity getIconEntityById(Long iconId) {
		IconEntity myIcon = iconRepo.getById(iconId);
		return myIcon;
	}


}
