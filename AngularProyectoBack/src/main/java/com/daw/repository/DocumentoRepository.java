package com.daw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.daw.model.Documento;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface DocumentoRepository extends JpaRepository<Documento, Long>{

	public Documento findByNombre(String nombre);
	
	@Modifying
    @Query(" DELETE FROM Documento d WHERE d.nombre = :nombre ")
	public void deleteByName(String nombre);
	
}