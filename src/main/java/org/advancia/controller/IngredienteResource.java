package org.advancia.controller;

import java.util.List;

import org.advancia.dto.IngredienteDto;
import org.advancia.service.IngredienteService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ingrediente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IngredienteResource {

    @Inject
    IngredienteService ingredienteService;

    @GET
    @Path("/{id}")
    public IngredienteDto getIngrediente(@PathParam(value = "id") int id) {
        return ingredienteService.getIngrediente(id);
    }

    @GET
    @Path("/queryParam")
    public IngredienteDto getIngredienteById(@QueryParam(value = "id") int id) {
        return ingredienteService.getIngrediente(id);
    }

    @GET
    public List<IngredienteDto> getAllIngredientes() {
        return ingredienteService.getAllIngredientes();
    }

    @GET
    @Path("/search/{nome}")
    public List<IngredienteDto> findIngredienteByName(@PathParam(value = "nome") String name) {
        return ingredienteService.findIngredienteByName(name);
    }

    @POST
    public IngredienteDto createIngrediente(IngredienteDto ingrediente) {
        return ingredienteService.createIngrediente(ingrediente);
    }

    @PUT
    @Path("/{id}")
    public IngredienteDto updateIngrediente(@PathParam(value = "id") int id, IngredienteDto ingrediente) {

        if (ingrediente.getNome() == null) {
            throw new WebApplicationException("name was not set on request.", 422);
        }
        return ingredienteService.updateIngrediente(id, ingrediente);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteIngrediente(@PathParam(value = "id") int id) {
        return ingredienteService.deleteIngrediente(id);
    }
}
