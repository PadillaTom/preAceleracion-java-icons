package com.padillatomas.icons.icons.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IconDTO {

	private Long id;
	private String imagen;
	private String denominacion;
	private String fechaCreacion;
	private Long altura;
	private String historia;
	private List<PaisDTO> paises;
	
}
