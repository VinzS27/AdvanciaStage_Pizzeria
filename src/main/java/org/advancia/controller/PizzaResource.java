package org.advancia.controller;

import java.util.List;

import org.advancia.dto.PizzaDto;
import org.advancia.service.PizzaService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/pizza")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PizzaResource {

    @Inject
    PizzaService pizzaService;

    @GET
    @Path("/{id}")
    public PizzaDto getPizza(@PathParam(value = "id") int id) {
        return pizzaService.getPizza(id);
    }

    @GET
    public List<PizzaDto> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @POST
    public PizzaDto createPizza(PizzaDto pizza) {
        return pizzaService.createPizza(pizza);
    }

}