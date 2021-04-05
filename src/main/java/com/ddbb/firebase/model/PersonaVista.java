package com.ddbb.firebase.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PersonaVista {
	private String id;
	
	private String nombre;
	private String ciudad;
	private Mascota[] mascotas;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	
	public PersonaVista () {
	}
	
	public PersonaVista (String id, String nombre, String ciudad, Date dob, Mascota[] mascotas) {
		this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.dob = dob;
		this.mascotas = mascotas;
	}
	
	public PersonaVista (Persona persona, Mascota[] mascotas) {
		this.id = persona.getId();
		this.nombre = persona.getNombre();
		this.ciudad = persona.getCiudad();
		this.dob = persona.getDob();
		this.mascotas = mascotas;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Mascota[] getMascotas() {
		return mascotas;
	}
	public void setMascotas(Mascota[] mascotas) {
		this.mascotas = mascotas;
	}
	
}
