package com.padillatomas.icons.icons.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padillatomas.icons.icons.dto.PaisBasicDTO;
import com.padillatomas.icons.icons.dto.PaisDTO;
import com.padillatomas.icons.icons.dto.PaisFiltersDTO;
import com.padillatomas.icons.icons.entity.IconEntity;
import com.padillatomas.icons.icons.entity.PaisEntity;
import com.padillatomas.icons.icons.exception.ParamNotFound;
import com.padillatomas.icons.icons.mapper.IconMapper;
import com.padillatomas.icons.icons.mapper.PaisMapper;
import com.padillatomas.icons.icons.repository.PaisRepository;
import com.padillatomas.icons.icons.repository.specifications.PaisSpecification;
import com.padillatomas.icons.icons.service.IconService;
import com.padillatomas.icons.icons.service.PaisService;

@Service
public class PaisServiceImpl implements PaisService {	
	
	// Instanciamos: Mapper
	@Autowired
	private PaisMapper paisMapper;
	@Autowired
	private IconMapper iconMapper;
	
	// Instanciamos: Specifications
	@Autowired
	private PaisSpecification paisSpecs;
	
	// Instanciamos: Service
	@Autowired
	private IconService iconServ;
	
	// Instanciamos: Repository
	@Autowired
	private PaisRepository paisRepo;		
		
	
	// === POST ===
	@Override
	public PaisDTO guardarPais(PaisDTO dto) {
		PaisEntity nuevaEntity = paisMapper.paisDTO2Entity(dto, false);
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
	
	@Override
	public List<PaisDTO> getByFilters(String name, String continent, String order) {
		PaisFiltersDTO filtersDTO = new PaisFiltersDTO(name, continent, order);
		List<PaisEntity> myPaises = paisRepo.findAll(paisSpecs.getByFilters(filtersDTO));
		List<PaisDTO> resultDTO = paisMapper.paisEntityList2DTOList(myPaises, true);
		return resultDTO;
	}
	
	@Override
	public PaisDTO getPaisDetailsById(Long id) {
		PaisEntity myPais = this.getPaisEntityById(id);		
		PaisDTO resultDTO = paisMapper.paisEntity2DTO(myPais, true);
		return resultDTO;
	}	

	// === DELETE ===
	@Override
	public void deletePais(Long id) {
		paisRepo.deleteById(id);
		
	}

	// === PUT ===
	@Override
	public PaisDTO editPais(Long id, PaisDTO paisToEdit) {
		PaisEntity savedPais = this.getPaisEntityById(id);
		
		savedPais.setImagen(paisToEdit.getImagen());
		savedPais.setDenominacion(paisToEdit.getDenominacion());
		savedPais.setCantidadHabitantes(paisToEdit.getCantidadHabitantes());
		savedPais.setSuperficie(paisToEdit.getSuperficie());
		savedPais.setContinenteId(paisToEdit.getContinenteId());
		savedPais.setIcons(iconMapper.iconDTOSet2EntitySet(paisToEdit.getIcons()));
		
		PaisEntity editedPais = paisRepo.save(savedPais);	
		
		PaisDTO savedDTO = paisMapper.paisEntity2DTO(editedPais, false);
		
		return savedDTO;
	}

	// ********
	// Methods:
	// ********
	
	@Override
	public void addIcon(Long paisId, Long iconId) {
		PaisEntity myPais = this.getPaisEntityById(paisId);
		
		// Invocando cualquier metodo, "salteamos" el LAZY, trayendonos la DATA.
		myPais.getIcons().size();
		
		IconEntity iconToAdd = iconServ.getIconEntityById(iconId);
		myPais.addIconToPais(iconToAdd);
		
		paisRepo.save(myPais);		
		
	}

	@Override
	public void removeIconFromPais(Long paisId, Long iconId) {
		PaisEntity myPais = this.getPaisEntityById(paisId);
		
		myPais.getIcons().size();
		IconEntity iconToRemove = iconServ.getIconEntityById(iconId);
		
		myPais.removeIconFromPais(iconToRemove);
		
		paisRepo.save(myPais);		
	}	
	
	@Override
	public PaisEntity getPaisEntityById(Long paisId) {
		Optional<PaisEntity> myPais = paisRepo.findById(paisId);
		if(!myPais.isPresent()) {
			throw new ParamNotFound("Pais with id: " + paisId + " not found.");
		}
		return myPais.get();
	}

	
	
}
