package com.daw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.daw.model.Seccion;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface SeccionRepository extends JpaRepository<Seccion, Long>{
	
	public Seccion findByNombre(String nombre);
	
	@Modifying
    @Query(" DELETE FROM Seccion s WHERE s.nombre = :nombre ")
	public void deleteByName(String nombre);
}