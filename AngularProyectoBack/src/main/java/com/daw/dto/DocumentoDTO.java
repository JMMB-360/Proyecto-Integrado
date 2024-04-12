package com.daw.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DocumentoDTO {
	
	@NotEmpty
	private String nombre;
	
	@NotNull
	private Long idUsuario;
	
}