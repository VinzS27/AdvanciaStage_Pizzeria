package com.advancia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advancia.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {
	Optional<Utente> findByUsername(String username);

	Optional<Utente> findByUsernameAndPassword(String username, String password);

	Boolean existsByUsername(String username);
}
