package com.daw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.dto.ContenidoDTO;
import com.daw.dto.DocumentoDTO;
import com.daw.dto.SeccionDTO;
import com.daw.dto.UsuarioDTO;
import com.daw.model.ContenidoSeccion;
import com.daw.model.Documento;
import com.daw.model.Seccion;
import com.daw.model.Usuario;
import com.daw.service.Servicio;

import jakarta.validation.Valid;

@Validated
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/formularios")
public class ApplicationController {
	
	@Autowired
	private Servicio servicio;
	
	/*---------------------------- BUSCAR CONCRETO ----------------------------*/
	
	@GetMapping("/usuarios/{nombre}")
	public ResponseEntity<Usuario> buscarUsuario(@PathVariable("nombre") String nombre){
		return ResponseEntity.ok().body(servicio.buscarUsuario(nombre));
	}
	@GetMapping("/documentos/{nombre}")
	public ResponseEntity<Documento> buscarDocumento(@PathVariable("nombre") String nombre){
		return ResponseEntity.ok().body(servicio.buscarDocumento(nombre));
	}
	@GetMapping("/secciones/{nombre}")
	public ResponseEntity<Seccion> buscarSeccion(@PathVariable("nombre") String nombre){
		return ResponseEntity.ok().body(servicio.buscarSeccion(nombre));
	}
	@GetMapping("/contenidos/{seccion}")
	public ResponseEntity<ContenidoSeccion> buscarContenido(@PathVariable("seccion") String seccion){
		return ResponseEntity.ok().body(servicio.buscarContenido(seccion));
	}
	
	/*---------------------------- BUSCAR TODOS ----------------------------*/
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> buscarUsuarios(){
		return ResponseEntity.ok().body(servicio.buscarUsuarios());
	}
	@GetMapping("/documentos")
	public ResponseEntity<List<Documento>> buscarDocumentos(){
		return ResponseEntity.ok().body(servicio.buscarDocumentos());
	}
	@GetMapping("/secciones")
	public ResponseEntity<List<Seccion>> buscarSecciones(){
		return ResponseEntity.ok().body(servicio.buscarSecciones());
	}
	@GetMapping("/contenidos")
	public ResponseEntity<List<ContenidoSeccion>> buscarContenidos(){
		return ResponseEntity.ok().body(servicio.buscarContenidos());
	}
	
	/*---------------------------- CREAR ----------------------------*/
	
	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UsuarioDTO usDTO) throws Exception{
		return ResponseEntity.ok().body(servicio.crearUsuario(usDTO.getDni(),
															  usDTO.getNombre(),
															  usDTO.getApellidos(),
															  usDTO.getUsuario(),
															  usDTO.getContrasena()));
	}
	@PostMapping("/documentos")
	public ResponseEntity<Documento> crearDocumento(@Valid @RequestBody DocumentoDTO docDTO){
		return ResponseEntity.ok().body(servicio.crearDocumento(docDTO.getNombre(),
																docDTO.getIdUsuario()));
	}
	@PostMapping("/secciones")
	public ResponseEntity<Seccion> crearSeccion(@Valid @RequestBody SeccionDTO secDTO){
		return ResponseEntity.ok().body(servicio.crearSeccion(secDTO.getNombre(),
															  secDTO.getNumero(),
															  secDTO.getIdDocumento()));
	}
	@PostMapping("/contenidos")
	public ResponseEntity<ContenidoSeccion> crearContenido(@Valid @RequestBody ContenidoDTO contDTO){
		return ResponseEntity.ok().body(servicio.crearContenido(contDTO.getContenido(),
																contDTO.getIdSeccion()));
	}
	
	/*---------------------------- MODIFICAR ----------------------------*/
	
	@PutMapping("/usuarios/{oNombre}")
	public ResponseEntity<Usuario> modificarUsuario(@PathVariable("oNombre") String oNombre,
													@Valid @RequestBody UsuarioDTO usDTO) throws Exception{
		return ResponseEntity.ok().body(servicio.modificarUsuario(oNombre, usDTO.getDni(),
															  usDTO.getNombre(),
															  usDTO.getApellidos(),
															  usDTO.getUsuario(),
															  usDTO.getContrasena()));
	}
	@PutMapping("/documentos/{oNombre}")
	public ResponseEntity<Documento> modificarDocumento(@PathVariable("oNombre") String oNombre,
														@Valid @RequestBody DocumentoDTO docDTO){
		return ResponseEntity.ok().body(servicio.modificarDocumento(oNombre, docDTO.getNombre(),
																docDTO.getIdUsuario()));
	}
	@PutMapping("/secciones/{oNombre}")
	public ResponseEntity<Seccion> modificarSeccion(@PathVariable("oNombre") String oNombre,
													@Valid @RequestBody SeccionDTO secDTO){
		return ResponseEntity.ok().body(servicio.modificarSeccion(oNombre, secDTO.getNombre(), 
															  secDTO.getNumero(),
															  secDTO.getIdDocumento()));
	}
	@PutMapping("/contenidos/{seccion}")
	public ResponseEntity<ContenidoSeccion> modificarContenido(@PathVariable("seccion") String seccion,
															   @Valid @RequestBody ContenidoDTO contDTO){
		return ResponseEntity.ok().body(servicio.modificarContenido(seccion, contDTO.getContenido(),
																contDTO.getIdSeccion()));
	}
	
	/*---------------------------- ELIMINAR ----------------------------*/

	@DeleteMapping("/usuarios/{nombre}")
	public void eliminarUsuario(@PathVariable("nombre") String nombre){
		servicio.eliminarUsuario(nombre);
	}
	@DeleteMapping("/documentos/{nombre}")
	public void eliminarDocumento(@PathVariable("nombre") String nombre){
		servicio.eliminarDocumento(nombre);
	}
	@DeleteMapping("/secciones/{nombre}")
	public void eliminarSeccion(@PathVariable("nombre") String nombre){
		servicio.eliminarSeccion(nombre);
	}
	@DeleteMapping("/contenidos/{seccion}")
	public void eliminarContenido(@PathVariable("seccion") String seccion){
		servicio.eliminarContenido(seccion);
	}
}