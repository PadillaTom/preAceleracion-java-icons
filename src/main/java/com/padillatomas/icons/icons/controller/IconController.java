package com.padillatomas.icons.icons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.padillatomas.icons.icons.dto.IconBasicDTO;
import com.padillatomas.icons.icons.dto.IconDTO;
import com.padillatomas.icons.icons.service.IconService;

@RestController
@RequestMapping("icons")
public class IconController {

	// === Instanciamos SERVICE ===
	@Autowired
	private IconService iconServ;		
	
			
	// == GET ==
	@GetMapping("/all")
	public ResponseEntity<List<IconBasicDTO>> getAllIconEntitiy(){
		List<IconBasicDTO> myList = iconServ.getAllIcons();
		return ResponseEntity.status(HttpStatus.OK).body(myList);
	}
	
	@GetMapping("/all/detalle")
	public ResponseEntity<List<IconDTO>> getAllIconDetalleEntitiy(){
		List<IconDTO> myList = iconServ.getAllIconDetails();
		return ResponseEntity.status(HttpStatus.OK).body(myList);
	}	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<IconDTO> getDetailsById(@PathVariable Long id){
		IconDTO myIcon = iconServ.getIconDetailsById(id);
		return ResponseEntity.status(HttpStatus.OK).body(myIcon);
	}	
	
	// By Filters
	@GetMapping
	public ResponseEntity<List<IconDTO>> getDetailsByFilters(
				@RequestParam(required =false) String name,
				@RequestParam(required =false) String date,
				@RequestParam(required =false) List<Long> cities,
				@RequestParam(required =false, defaultValue = "ASC") String order
			) {
		List<IconDTO> icons = iconServ.getByFilters(name, date, cities, order);
		return ResponseEntity.status(HttpStatus.OK).body(icons);
	}
	
	// == POST ==
		@PostMapping
		public ResponseEntity<IconDTO> saveNewIcon(@RequestBody IconDTO dto){
			IconDTO savedIcon = iconServ.saveIcon(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedIcon);
		}	
			
	// == DELETE ==		
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteIconById(@PathVariable Long id) {
		iconServ.deleteIcon(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
		
	}
	
	// == PUT ==
	@PutMapping("/{id}")
	public ResponseEntity<IconDTO> editIconById(@PathVariable Long id, @RequestBody IconDTO iconToEdit){
		IconDTO editedIcon = iconServ.editIcon(id, iconToEdit);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedIcon);
	}

	
}
