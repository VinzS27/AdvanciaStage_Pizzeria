package com.advancia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advancia.model.Ingrediente;
import com.advancia.repository.IngredienteRepository;

@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class IngredienteController {
	@Autowired
	IngredienteRepository genreRepository;

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/ingrediente")
	public ResponseEntity<List<Ingrediente>> getAllIngrediente() {
		try {
			List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
			genreRepository.findAll().forEach(ingredienti::add);
			return new ResponseEntity<>(ingredienti, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/ingrediente")
	public ResponseEntity<Ingrediente> createIngrediente(@RequestBody Ingrediente genre) {
		try {
			Ingrediente ingredienti = genreRepository.save(genre);
			return new ResponseEntity<>(ingredienti, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
