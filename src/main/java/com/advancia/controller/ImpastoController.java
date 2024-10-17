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

import com.advancia.model.Impasto;
import com.advancia.repository.ImpastoRepository;

@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ImpastoController {
	@Autowired
	ImpastoRepository impastoRepository;
	
	//su postman mettere su barer il token eyJhbGciOiJI... generato in fase di login
	@PreAuthorize("isAuthenticated()") 
	@GetMapping("/impasto")
	public ResponseEntity<List<Impasto>> getAllImpasto() {
		try {
			List<Impasto> impasti = new ArrayList<Impasto>();
			impastoRepository.findAll().forEach(impasti::add);
			return new ResponseEntity<>(impasti, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/impasto")
	public ResponseEntity<Impasto> createImpasto(@RequestBody Impasto impasto) {
		try {
			Impasto _impasto = impastoRepository.save(impasto);
			return new ResponseEntity<>(_impasto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
