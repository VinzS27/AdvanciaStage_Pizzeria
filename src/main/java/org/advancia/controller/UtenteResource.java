package org.advancia.controller;

import java.util.List;

import org.advancia.dto.UtenteDto;
import org.advancia.service.UtenteService;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/utente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UtenteResource {

    @Inject
    UtenteService utenteService;

    @GET
    @Path("/{id}")
    public UtenteDto getUtente(@PathParam(value = "id") int id) {
        return utenteService.getUtente(id);
    }

    @GET
    @Path("/queryParam")
    public UtenteDto getUtenteById(@QueryParam(value = "id") int id) {
        return utenteService.getUtente(id);
    }

    @GET
    public List<UtenteDto> getAllUtentes() {
        return utenteService.getAllUtentes();
    }

    @GET
    @Path("/search/{username}")
    public List<UtenteDto> findUtenteByUsername(@PathParam(value = "username") String username) {
        return utenteService.findUtenteByUsername(username);
    }

    @POST
    public UtenteDto createUtente(UtenteDto utente) {
        return utenteService.createUtente(utente);
    }

    @PUT
    @Path("/{id}")
    public UtenteDto updateUtente(@PathParam(value = "id") int id, UtenteDto utente) {

        if (utente.getUsername() == null) {
            throw new WebApplicationException("username was not set on request.", 422);
        }
        return utenteService.updateUtente(id, utente);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUtente(@PathParam(value = "id") int id) {
        return utenteService.deleteUtente(id);
    }
}
