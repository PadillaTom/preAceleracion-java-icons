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

import com.padillatomas.icons.icons.dto.PaisBasicDTO;
import com.padillatomas.icons.icons.dto.PaisDTO;
import com.padillatomas.icons.icons.service.PaisService;

@RestController
@RequestMapping("cities")
public class PaisController {

	// === Instanciamos SERVICE ===
	@Autowired
	private PaisService paisServ;
	
	// == POST ==
	@PostMapping
	public ResponseEntity<PaisDTO> guardarNuevoPais(@RequestBody PaisDTO dto){
		PaisDTO savedPais = paisServ.guardarPais(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPais);
	}
	
	@PostMapping("/{paisId}/icon/{iconId}")
	public ResponseEntity<Void> addIcon(@PathVariable Long paisId, @PathVariable Long iconId){
		paisServ.addIcon(paisId, iconId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	// == GET ==
	@GetMapping("/all")
	public ResponseEntity<List<PaisBasicDTO>> getPaisBasic(){
		List<PaisBasicDTO> myList = paisServ.getAllPaisBasic();
		return ResponseEntity.status(HttpStatus.OK).body(myList);
	}
	
	@GetMapping("/detalle")
	public ResponseEntity<List<PaisDTO>> getPaises(){
		List<PaisDTO> myList = paisServ.getAllPaises();
		return ResponseEntity.status(HttpStatus.OK).body(myList);
	}
	
	// By Filters
	@GetMapping
	public ResponseEntity<List<PaisDTO>> getDetailsByFilters(
				@RequestParam(required =false) String name,
				@RequestParam(required =false) String continent,
				@RequestParam(required =false, defaultValue = "ASC") String order
			) {
		List<PaisDTO> paises = paisServ.getByFilters(name, continent, order);
		return ResponseEntity.status(HttpStatus.OK).body(paises);
	}
	
	// == DELETE ==		
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePaisById(@PathVariable Long id) {
		paisServ.deletePais(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();			
	}
	
	@DeleteMapping("/{paisId}/icon/{iconId}")
	public ResponseEntity<Void> removeIcon(@PathVariable Long paisId, @PathVariable Long iconId	){
		paisServ.removeIconFromPais(paisId, iconId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	// == PUT ==
	@PutMapping("/{id}")
	public ResponseEntity<PaisDTO> editPaisById(@PathVariable Long id, @RequestBody PaisDTO paisToEdit){
		PaisDTO editedPais = paisServ.editPais(id, paisToEdit);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedPais);
	}	
			
			
}
