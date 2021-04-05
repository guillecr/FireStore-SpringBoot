package com.ddbb.firebase.repository;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;

import com.ddbb.firebase.model.Mascota;

import reactor.core.publisher.Flux;

public interface RepositorioMascota extends FirestoreReactiveRepository<Mascota>{
	
	Flux<Mascota> findByIdOwner(String idOwner);
}
