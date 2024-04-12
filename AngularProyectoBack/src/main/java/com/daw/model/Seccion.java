package com.daw.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="SECCION")
public class Seccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "NUMERO")
	private Integer numero;
	
	@ManyToOne
	@JsonBackReference
    @JoinColumn(name = "DOCUMENTO_ID")
    private Documento documento;

	@JsonManagedReference
	@OneToOne(mappedBy = "seccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ContenidoSeccion contenido;
	
}