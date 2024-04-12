package com.daw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.daw.model.Usuario;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public boolean existsByDni(String dni);
	
	public Usuario findByNombre(String nombre);
	
	@Modifying
    @Query(" DELETE FROM Usuario u WHERE u.nombre = :nombre ")
	public void deleteByName(String nombre);
	
}