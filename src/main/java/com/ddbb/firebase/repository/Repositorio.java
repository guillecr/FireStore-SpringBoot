package com.ddbb.firebase.repository;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;

import com.ddbb.firebase.model.Persona;

import reactor.core.publisher.Flux;

public interface Repositorio extends FirestoreReactiveRepository<Persona> {
	Flux<Persona> findByCiudad(String ciudad);
	

}
