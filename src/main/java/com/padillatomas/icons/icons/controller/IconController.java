package com.padillatomas.icons.icons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
	// == POST ==
			
	// == GET ==
	@GetMapping
	public ResponseEntity<List<IconBasicDTO>> getAllIconEntitiy(){
		List<IconBasicDTO> myList = iconServ.getAllIcons();
		return ResponseEntity.status(HttpStatus.OK).body(myList);
	}
	
	@GetMapping("/detalle")
	public ResponseEntity<List<IconDTO>> getAllIconDetalleEntitiy(){
		List<IconDTO> myList = iconServ.getAllIconDetails();
		return ResponseEntity.status(HttpStatus.OK).body(myList);
	}
			
	// == DELETE ==		
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteIconById(@PathVariable Long id) {
		iconServ.deleteIcon(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
		
	}
	
	// == POST ==
	// == POST ==
	
}
