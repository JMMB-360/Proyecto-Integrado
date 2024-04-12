package com.daw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.model.ContenidoSeccion;
import com.daw.model.Documento;
import com.daw.model.Seccion;
import com.daw.model.Usuario;
import com.daw.repository.ContenidoRepository;
import com.daw.repository.DocumentoRepository;
import com.daw.repository.SeccionRepository;
import com.daw.repository.UsuarioRepository;

import lombok.Data;

@Service
@Data
public class Servicio {
	
	@Autowired
	UsuarioRepository usRepo;
	@Autowired
	DocumentoRepository docRepo;
	@Autowired
	SeccionRepository secRepo;
	@Autowired
	ContenidoRepository contRepo;
	
	/*---------------------------- BUSCAR CONCRETO ----------------------------*/
	
	public Usuario buscarUsuario(String nombre){
		return usRepo.findByNombre(nombre);
	}
	public Documento buscarDocumento(String nombre){
		return docRepo.findByNombre(nombre);
	}
	public Seccion buscarSeccion(String nombre){
		return secRepo.findByNombre(nombre);
	}
	public ContenidoSeccion buscarContenido(String nombreSeccion){
		return contRepo.findBySeccionNombre(nombreSeccion);
	}
	
	/*---------------------------- BUSCAR TODOS ----------------------------*/
	
	public List<Usuario> buscarUsuarios(){
		return usRepo.findAll();
	}
	public List<Documento> buscarDocumentos(){
		return docRepo.findAll();
	}
	public List<Seccion> buscarSecciones(){
		return secRepo.findAll();
	}
	public List<ContenidoSeccion> buscarContenidos(){
		return contRepo.findAll();
	}
	
	/*---------------------------- CREAR ----------------------------*/
	
	public Usuario crearUsuario(String dni, String nombre, String apellidos, String usuario, String contrasena) throws Exception {
		Usuario us = new Usuario();
		
		if (usRepo.existsByDni(us.getDni())) {
            throw new Exception("El DNI ya está registrado");
        } else {
			us.setDni(dni);
			us.setNombre(nombre);
			us.setApellidos(apellidos);
			us.setUsuario(usuario);
			us.setContrasena(contrasena);
	        
			usRepo.save(us);
	        return us;
        }
    }
	public Documento crearDocumento(String nombre, Long id) {
		Documento doc = new Documento();
        
		doc.setNombre(nombre);
		doc.setUsuario(usRepo.getReferenceById(id));
        
        docRepo.save(doc);
        return doc;
    }
	public Seccion crearSeccion(String nombre, Integer numero, Long id) {
		Seccion sec = new Seccion();
        
		sec.setNombre(nombre);
		sec.setNumero(numero);
		sec.setDocumento(docRepo.getReferenceById(id));
        
        secRepo.save(sec);
        return sec;
    }
	public ContenidoSeccion crearContenido(String contenido, Long id) {
		ContenidoSeccion cont = new ContenidoSeccion();
		
		cont.setContenido(contenido);
		cont.setSeccion(secRepo.getReferenceById(id));
        
        contRepo.save(cont);
        return cont;
    }
	
	/*---------------------------- MODIFICAR ----------------------------*/
	
	public Usuario modificarUsuario(String nombreOriginal, String dni, String nombre, String apellidos, String usuario, String contrasena) throws Exception {
		Usuario us = usRepo.findByNombre(nombreOriginal);
        
		if (usRepo.existsByDni(us.getDni())) {
            throw new Exception("El DNI ya está registrado");
        } else {
			us.setDni(dni);
			us.setNombre(nombre);
			us.setApellidos(apellidos);
			us.setUsuario(usuario);
			us.setContrasena(contrasena);
	        
			usRepo.save(us);
	        return us;
        }
    }
	public Documento modificarDocumento(String nombreOriginal, String nombre, Long idUs) {
		Documento doc = docRepo.findByNombre(nombreOriginal);
        
		doc.setNombre(nombre);
		doc.setUsuario(usRepo.getReferenceById(idUs));
        
        docRepo.save(doc);
        return doc;
    }
	public Seccion modificarSeccion(String nombreOriginal, String nombre, Integer numero, Long idDoc) {
		Seccion sec = secRepo.findByNombre(nombreOriginal);
        
		sec.setNombre(nombre);
		sec.setNumero(numero);
		sec.setDocumento(docRepo.getReferenceById(idDoc));
        
        secRepo.save(sec);
        return sec;
    }
	public ContenidoSeccion modificarContenido(String nombreSeccion, String contenido, Long idSec) {
		ContenidoSeccion cont = contRepo.findBySeccionNombre(nombreSeccion);
        
		cont.setContenido(contenido);
		cont.setSeccion(secRepo.getReferenceById(idSec));
        
        contRepo.save(cont);
        return cont;
    }
	
	/*---------------------------- ELIMINAR ----------------------------*/
	
	public void eliminarUsuario(String nombre) {
		usRepo.deleteByName(nombre);
	}
	public void eliminarDocumento(String nombre) {
		docRepo.deleteByName(nombre);
	}
	public void eliminarSeccion(String nombre) {
		secRepo.deleteByName(nombre);
	}
	public void eliminarContenido(String nombreSeccion) {
		contRepo.deleteBySeccionNombre(nombreSeccion);
	}
}