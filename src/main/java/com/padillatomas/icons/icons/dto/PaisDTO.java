package com.padillatomas.icons.icons.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaisDTO {
	
	private Long id;
	private String imagen;
	private String denominacion;
	private Long cantidadHabitantes;	
	private Long superficie; // m2	
	
	// Pasar un Continente ID : Desde formulario habra un input(Continente id)
	private Long continenteId;	
	
	// Pasar un Javascript ARRAY: [{imagen:"asd", denominacion:"asd"},{imagen:"123", denominacion:"123"}]
	Set<IconDTO> icons;
	

}
