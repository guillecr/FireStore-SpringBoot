package com.ddbb.firebase.model;

import org.springframework.cloud.gcp.data.firestore.Document;

import com.google.cloud.firestore.annotation.DocumentId;

@Document(collectionName = "mascotas")
public class Mascota {
	
	@DocumentId
	private String id;
	
	private String idOwner;
	private String animal;
	private String nombre;
	
	public Mascota(String id, String idOwner, String animal, String nombre) {
		this.id = id;
		this.idOwner = idOwner;
		this.animal = animal;
		this.nombre = nombre;
	}
	public Mascota() {
		this.id = String.valueOf(System.currentTimeMillis());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdOwner() {
		return idOwner;
	}
	public void setIdOwner(String idOwner) {
		this.idOwner = idOwner;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
