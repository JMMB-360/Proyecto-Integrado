package com.daw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.daw.model.ContenidoSeccion;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ContenidoRepository extends JpaRepository<ContenidoSeccion, Long>{
	
	public ContenidoSeccion findBySeccionNombre(String nombreSeccion);
	
	@Modifying
    @Query(" DELETE FROM ContenidoSeccion cs "
    	 + " WHERE cs.seccion.nombre = :nombre ")
	public void deleteBySeccionNombre(String nombre);
}