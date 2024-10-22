package org.advancia.mapper;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.advancia.dto.PizzaDto;
import org.advancia.entity.Ingrediente;
import org.advancia.entity.Pizza;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-22T10:23:26+0200",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 23 (Eclipse Adoptium)"
)
@Singleton
@Named
public class PizzaMapperImpl implements PizzaMapper {

    @Override
    public PizzaDto toPizzaDto(Pizza pizza) {
        if ( pizza == null ) {
            return null;
        }

        PizzaDto pizzaDto = new PizzaDto();

        pizzaDto.setNome( pizza.nome );
        pizzaDto.setId( pizza.id );
        pizzaDto.setImpasto( pizza.impasto );
        List<Ingrediente> list = pizza.ingredienti;
        if ( list != null ) {
            pizzaDto.setIngredienti( new ArrayList<Ingrediente>( list ) );
        }
        pizzaDto.setUtente( pizza.utente );

        return pizzaDto;
    }

    @Override
    public List<PizzaDto> toPizzaDtoList(List<Pizza> pizzas) {
        if ( pizzas == null ) {
            return null;
        }

        List<PizzaDto> list = new ArrayList<PizzaDto>( pizzas.size() );
        for ( Pizza pizza : pizzas ) {
            list.add( toPizzaDto( pizza ) );
        }

        return list;
    }

    @Override
    public Pizza toPizzaEntity(PizzaDto pizzaDto) {
        if ( pizzaDto == null ) {
            return null;
        }

        Pizza pizza = new Pizza();

        pizza.id = pizzaDto.getId();
        pizza.nome = pizzaDto.getNome();
        pizza.impasto = pizzaDto.getImpasto();
        pizza.utente = pizzaDto.getUtente();
        List<Ingrediente> list = pizzaDto.getIngredienti();
        if ( list != null ) {
            pizza.ingredienti = new ArrayList<Ingrediente>( list );
        }

        return pizza;
    }
}
