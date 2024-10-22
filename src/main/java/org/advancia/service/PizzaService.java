package org.advancia.service;

import java.util.List;
import java.util.Optional;

import org.advancia.dto.PizzaDto;
import org.advancia.entity.Pizza;
import org.advancia.mapper.PizzaMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PizzaService {

	@Inject
	PizzaMapper pizzaMapper;

	// ***GET***
	public PizzaDto getPizza(int id) {
		Optional<Pizza> optionalPizza = Pizza.findByIdOptional(id);
		Pizza pizza = optionalPizza.orElseThrow(NotFoundException::new);
		return pizzaMapper.toPizzaDto(pizza);
	}

	public List<PizzaDto> getAllPizzas() {
		return pizzaMapper.toPizzaDtoList(Pizza.listAll());
	}

	// ***POST***
	@Transactional
	public PizzaDto createPizza(PizzaDto pizza) {

		Pizza entity = pizzaMapper.toPizzaEntity(pizza);
		Pizza.persist(entity);

		if (entity.isPersistent()) {
			Optional<Pizza> optionalDept = Pizza.findByIdOptional(entity.id);
			entity = optionalDept.orElseThrow(NotFoundException::new);
			return pizzaMapper.toPizzaDto(entity);
		} else {
			throw new PersistenceException();
		}

	}
}
