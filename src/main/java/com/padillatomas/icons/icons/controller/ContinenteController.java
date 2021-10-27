package com.padillatomas.icons.icons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padillatomas.icons.icons.dto.ContinenteDTO;
import com.padillatomas.icons.icons.service.ContinenteService;

@RestController
@RequestMapping("continentes")
public class ContinenteController {
	
	// === Instanciamos SERVICE ===
	@Autowired
	private ContinenteService continenteServ;
	
		
	// == POST ==		
	@PostMapping
	public ResponseEntity<ContinenteDTO> guardarNuevoContinente(@RequestBody ContinenteDTO nuevoContinente) {
		ContinenteDTO continenteGuardado = continenteServ.guardarContinente(nuevoContinente);
		return ResponseEntity.status(HttpStatus.CREATED).body(continenteGuardado);		
	}
	
	// == GET ==	
	@GetMapping
	public ResponseEntity<List<ContinenteDTO>> getAllContinentesList() {	
		List<ContinenteDTO> contList = continenteServ.getAllContinentes();
		return ResponseEntity.status(HttpStatus.OK).body(contList);		
	}

}
