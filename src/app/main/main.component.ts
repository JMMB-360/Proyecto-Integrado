import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

  usrsForm: FormGroup;
  docForm: FormGroup;
  documentos: any[] = [];
  usuarios: any[] = [];
  menuActual: String = 'registro';

  constructor(private formBuilder: FormBuilder) {
    this.usrsForm = this.formBuilder.group({
      id: [null, Validators.required],
      dni: ['', Validators.required],
      nombre: ['', Validators.required],
      apellidos: ['', Validators.required],
      usuario: ['', Validators.required],
      contrasena: ['', Validators.required],
    });
    this.docForm = this.formBuilder.group({
      id: [null, Validators.required],
      nombre: ['', Validators.required],
      id_usuario: [null, Validators.required]
    });
    
  }

  ngOnInit() {
    this.buscarUsuarios();
    this.buscarDocumentos();
  }

// ------------- CRUD USUARIOS ------------- //

  async buscarUsuarios() {
    const URL = `http://localhost:9999/usuarios`;
    const respuesta = await fetch(URL).then(respuesta => respuesta.json());
    this.usuarios = respuesta;
  }

  async buscarUnUsuario() {
    const URL = `http://localhost:9999/usuarios/${this.usrsForm.get("nombre")?.value}`;
    const configuracion = {
      method: "GET",
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    try {
      const respuesta = await fetch(URL, configuracion);
      const usr = await respuesta.json();//todo: Hacer algo útil con esta var
      alert(`Nombre: ${usr.nombre}`);
    } catch (error) {
      console.error("Error en la solicitud:", error);
    }
  }

  async crearUsuario() {
    const dni = this.usrsForm.get("dni")?.value;
    const nombre = this.usrsForm.get("nombre")?.value;
    const apellidos = this.usrsForm.get("apellidos")?.value;
    const usuario = this.usrsForm.get("usuario")?.value;
    const contrasena = this.usrsForm.get("contrasena")?.value;

    const URL = `http://localhost:9999/usuarios`;
    const configuracion = {
      method: "POST",
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      },
      body: JSON.stringify({ dni, nombre, apellidos, usuario, contrasena })
    }
    const respuesta = await fetch(URL, configuracion).then(respuesta => respuesta.json());
    this.buscarUsuarios();
    return respuesta;
  }

  async modificarUsuario() {
    const dni = this.usrsForm.get("dni")?.value;
    const nombre = this.usrsForm.get("nombre")?.value;
    const apellidos = this.usrsForm.get("apellidos")?.value;
    const usuario = this.usrsForm.get("usuario")?.value;
    const contrasena = this.usrsForm.get("contrasena")?.value;
    
    const URL = `http://localhost:9999/usuarios`;
    const configuracion = {
      method: "PUT",
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      },
      body: JSON.stringify({ dni, nombre, apellidos, usuario, contrasena })
    }
    const respuesta = await fetch(URL, configuracion).then(respuesta => respuesta.json());
    this.buscarUsuarios();
    return respuesta;
  }

  async eliminarUsuario() {
    const URL = `http://localhost:9999/usuarios/${this.usrsForm.get("nombre")?.value}`;
    const configuracion = {
      method: "DELETE",
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      },
    }
    const respuesta = await fetch(URL, configuracion);
    this.buscarUsuarios();
    if(respuesta.ok) {
      return alert("Usuario eliminado correctamente");
    }
    return respuesta;
  }

// ------------- CRUD DOCUMENTOS ------------- //

  async buscarDocumentos() {
    const URL = `http://localhost:9999/documentos`;
    const respuesta = await fetch(URL).then(respuesta => respuesta.json());
    this.documentos = respuesta;
  }

  async buscarUnDocumento() {
    const nombre = this.docForm.get("nombre")?.value;
    const URL = `http://localhost:9999/documentos/${nombre}`;
    const configuracion = {
      method: "GET",
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    try {
      const respuesta = await fetch(URL, configuracion);
      const documento = await respuesta.json();//todo: Hacer algo útil con esta var
      alert(`Nombre: ${documento.nombre}`);
    } catch (error) {
      console.error("Error en la solicitud:", error);
    }
  }

  async crearDocumento() {
    const nombre = this.docForm.get("nombre")?.value;
    const URL = `http://localhost:9999/documentos`;
    const configuracion = {
      method: "POST",
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      },
      body: JSON.stringify({ nombre })//todo: falta el id de usuario, sección y contenido
    }
    const respuesta = await fetch(URL, configuracion).then(respuesta => respuesta.json());
    this.buscarDocumentos();
    return respuesta;
  }

  async modificarDocumento() {
    const nombre = this.docForm.get("nombre")?.value;
    const URL = `http://localhost:9999/documentos/${nombre}`;
    const configuracion = {
      method: "PUT",
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      },
      body: JSON.stringify({ nombre })//todo: falta el id de usuario, sección y contenido
    }
    const respuesta = await fetch(URL, configuracion).then(respuesta => respuesta.json());
    this.buscarDocumentos();
    return respuesta;
  }

  async eliminarDocumento() {
    const URL = `http://localhost:9999/documentos/${this.docForm.get("nombre")?.value}`;
    const configuracion = {
      method: "DELETE",
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      },
    }
    const respuesta = await fetch(URL, configuracion);
    this.buscarDocumentos();
    if(respuesta.ok) {
      return alert("Documento eliminado correctamente");
    }
    return respuesta;
  }

// ------------- FUNCIONES ------------- //

  // todo: Validar si se ha registrado para poder ir a los menús de los Docs o buscar usuarios
  cambiarMenu(menu: String) {
    this.menuActual = menu;
  }

  resetForm() {
    (document.getElementById('dni') as HTMLInputElement).value = '';
    (document.getElementById('nombre') as HTMLInputElement).value = '';
    (document.getElementById('apellidos') as HTMLInputElement).value = '';
    (document.getElementById('usuario') as HTMLInputElement).value = '';
    (document.getElementById('contrasena') as HTMLInputElement).value = '';
  }

}
