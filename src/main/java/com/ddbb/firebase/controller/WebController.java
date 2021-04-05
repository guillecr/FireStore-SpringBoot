package com.ddbb.firebase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ddbb.firebase.model.Mascota;
import com.ddbb.firebase.model.Persona;
import com.ddbb.firebase.model.PersonaVista;

@Controller
@RequestMapping(path = "/vistas")
public class WebController {

	String urlApi = "https://hito2tad.herokuapp.com/api";
	RestTemplate restTemplate = new RestTemplate();
	
	
	// TODOS
	@GetMapping()
	public String verTodos(Model model) {
		System.out.println("Ver Lista");
		List<PersonaVista> lista = new ArrayList<>();
		for(Persona p : restTemplate.getForObject(urlApi, Persona[].class))
			lista.add(new PersonaVista(p,restTemplate.getForObject(
					urlApi+"/mascotas?idOwner="+p.getId(), Mascota[].class)));

		model.addAttribute("lista",lista);
		return "lista";
	}
	
	// NUEVO
	@GetMapping(path = "nuevo")
	public String formulario(Model model) {
		System.out.println("Formulario nueva Persona");
		model.addAttribute("Persona", new Persona());
		return "formulario";
	}
	
	// INSERTAR
	@PostMapping()
	public String guardar(Model model, @ModelAttribute Persona persona) {
		System.out.println("Guardar nueva Persona");
		restTemplate.postForObject(urlApi+"/object", persona, Persona.class);
		return verTodos(model);
	}
	
	// ELIMINAR
	@GetMapping(path = "/eliminar")
	public String eliminar(Model model, @RequestParam String id) {
		System.out.println("Eliminar Persona");
		restTemplate.delete(urlApi + "?id=" + id);
		return verTodos(model);
	}
	
	// ACTUALIZAR
	@GetMapping(path = "/actualizar")
	public String modificar(Model model, @RequestParam String id) {
		System.out.println("Formulario actualizar persona");
		Persona p = restTemplate.getForObject(urlApi+"/filtro?id="+id, Persona.class);
		model.addAttribute("Persona", p);
		return "formulario";
	}
	
	
	// MASCOTAS
	@GetMapping(path = "/nuevomascota")
	public String nuevaMascota(Model model, @RequestParam String id) {
		System.out.println("Formulario nueva mascota");
		Persona persona = restTemplate.getForObject(urlApi+"/filtro?id="+id, Persona.class);
		Mascota m = new Mascota();
		m.setIdOwner(persona.getId());
		model.addAttribute("Mascota", m);
		model.addAttribute("Propietario", persona.getNombre());
		return "formularioMascota";
	}
	
	@PostMapping(path = "/nuevamascota")
	public String guardarMascota(Model model, @ModelAttribute Mascota mascota) {
		System.out.println("Guardar nueva mascota");
		restTemplate.postForObject(urlApi+"/mascotas", mascota, Mascota.class);
		return verTodos(model);
	}
	
	@GetMapping(path = "/eliminarmascota")
	public String eliminarMascota(Model model, @RequestParam String id) {
		System.out.println("Eliminar mascota: " + id);
		restTemplate.delete(urlApi+"/mascotas?id="+id);
		return verTodos(model);
	}
}
