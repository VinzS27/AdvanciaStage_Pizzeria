package com.advancia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advancia.model.Pizza;
import com.advancia.repository.PizzaRepository;

@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PizzaController {
	@Autowired
	PizzaRepository pizzaRepository;

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/pizza/{user_id}")
	public ResponseEntity<List<Pizza>> getPizzaByUserId(@PathVariable("user_id") int id) {
		try {
			List<Pizza> pizzas = pizzaRepository.findAllByUserID(id);
			return new ResponseEntity<>(pizzas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/pizza")
	public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza) {
		try {
			Pizza _pizza = pizzaRepository.save(pizza);
			return new ResponseEntity<>(_pizza, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping("/pizza/{id}")
	public ResponseEntity<Pizza> updatePizza(@PathVariable("id") int id, @RequestBody Pizza pizza) {
		Optional<Pizza> pizzaData = pizzaRepository.findById(id);
		if (pizzaData.isPresent()) {
			Pizza _pizza = pizzaData.get();
			_pizza.setImpasto(pizza.getImpasto());
			_pizza.setIngredienti(pizza.getIngredienti());
			_pizza.setName(pizza.getName());
			return new ResponseEntity<>(pizzaRepository.save(_pizza), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/pizza/{id}")
	public ResponseEntity<HttpStatus> deletePizza(@PathVariable("id") int id) {
		try {
			pizzaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
