package com.ddbb.firebase.model;

import java.util.Date;

import org.springframework.cloud.gcp.data.firestore.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.google.cloud.firestore.annotation.DocumentId;


@Document(collectionName = "personas")
public class Persona {

	@DocumentId
	private String id;
	
	private String nombre;
	private String ciudad;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	
	public Persona() {
		this.id = String.valueOf(System.currentTimeMillis());
	}
	
	public Persona(String nombre, String ciudad, Date dob) {
		this.id = String.valueOf(System.currentTimeMillis());
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.dob = dob;
	}
	public Persona(String id, String nombre, String ciudad, Date dob) {
		this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.dob = dob;
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
}
