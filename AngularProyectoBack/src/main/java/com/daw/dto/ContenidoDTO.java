package com.daw.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContenidoDTO {
	
	private String contenido;
	
	@NotNull
	private Long idSeccion;
	
}