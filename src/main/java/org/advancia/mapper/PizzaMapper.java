package org.advancia.mapper;

import java.util.List;

import org.advancia.dto.PizzaDto;
import org.advancia.entity.Pizza;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface PizzaMapper {

	PizzaDto toPizzaDto(Pizza pizza);
	List<PizzaDto> toPizzaDtoList(List<Pizza> pizzas);
	Pizza toPizzaEntity(PizzaDto pizzaDto);
}
