package com.ddbb.firebase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddbb.firebase.lib.Fecha;
import com.ddbb.firebase.model.Mascota;
import com.ddbb.firebase.model.Persona;
import com.ddbb.firebase.repository.Repositorio;
import com.ddbb.firebase.repository.RepositorioMascota;

@RestController
@RequestMapping(path = "/api")
public class ControlerPersona {

	@Autowired
	Repositorio repositorio;
	
	@Autowired
	RepositorioMascota repositorioMascota;
	
	// Personas
	@GetMapping()
	public List<Persona> obtenerAll() {
		List<Persona> personas =  repositorio.findAll().collectList().block();
		return personas;
	}
	
	@GetMapping(path = "/filtro",params = "id")
	public Persona obtenerId(@RequestParam String id) {
		return repositorio.findById(id).block();
	}
	
	@GetMapping(path = "/filtro",params = "ciudad")
	public List<Persona> obtenerCiudad(@RequestParam String ciudad) {
		return repositorio.findByCiudad(ciudad).collectList().block();
	}
	
	@PostMapping()
	public void guardar(@RequestParam String nombre, String ciudad, String fecha) {
		Persona p = new Persona();
		p.setNombre(nombre);
		p.setCiudad(ciudad);
		p.setDob(new Fecha(fecha,"yyyy-MM-dd").obtenerDate());
		repositorio.save(p).block();
	}
	
	@PostMapping(path = "/object")
	public void guardarObjeto(@RequestBody Persona persona) {
		repositorio.save(persona).block();
	}
	
	@DeleteMapping()
	public void eliminar(@RequestParam String id) {
		repositorio.deleteById(id).block();
		repositorioMascota.findByIdOwner(id).collectList().block()
			.forEach(m -> repositorioMascota.deleteById(m.getId()).block());
	}
	
	
	// MASCOTAS
	@GetMapping(path = "/mascotas")
	public List<Mascota> obtenerMascotas() {
		return repositorioMascota.findAll().collectList().block();
	}

	@PostMapping(path = "/mascotas")
	public void guardarMascota(@RequestBody Mascota mascota) {
		repositorioMascota.save(mascota).block();
	}
	
	@GetMapping(path = "/mascotas",params = "idOwner")
	public List<Mascota> obtenerMascotasOwner(@RequestParam String idOwner) {
		return repositorioMascota.findByIdOwner(idOwner).collectList().block();
	}
	
	@DeleteMapping(path = "/mascotas",params = "id")
	public void eliminarMascota(@RequestParam String id) {
		System.out.println("Delete pet");
		repositorioMascota.deleteById(id).block();
	}
}
