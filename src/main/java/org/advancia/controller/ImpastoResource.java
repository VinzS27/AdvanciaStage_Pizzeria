package org.advancia.controller;

import java.util.List;

import org.advancia.dto.ImpastoDto;
import org.advancia.service.ImpastoService;

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

@Path("/impasto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ImpastoResource {

    @Inject
    ImpastoService impastoService;

    @GET
    @Path("/{id}")
    public ImpastoDto getImpasto(@PathParam(value = "id") int id) {
        return impastoService.getImpasto(id);
    }

    @GET
    @Path("/queryParam")
    public ImpastoDto getImpastoById(@QueryParam(value = "id") int id) {
        return impastoService.getImpasto(id);
    }

    @GET
    public List<ImpastoDto> getAllImpastos() {
        return impastoService.getAllImpastos();
    }

    @GET
    @Path("/search/{nome}")
    public List<ImpastoDto> findImpastoByName(@PathParam(value = "nome") String name) {
        return impastoService.findImpastoByName(name);
    }

    @POST
    public ImpastoDto createImpasto(ImpastoDto impasto) {
        return impastoService.createImpasto(impasto);
    }

    @PUT
    @Path("/{id}")
    public ImpastoDto updateImpasto(@PathParam(value = "id") int id, ImpastoDto impasto) {

        if (impasto.getNome() == null) {
            throw new WebApplicationException("name was not set on request.", 422);
        }
        return impastoService.updateImpasto(id, impasto);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteImpasto(@PathParam(value = "id") int id) {
        return impastoService.deleteImpasto(id);
    }
}