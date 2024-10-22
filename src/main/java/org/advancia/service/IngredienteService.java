package org.advancia.service;

import java.util.List;
import java.util.Optional;

import org.advancia.dto.IngredienteDto;
import org.advancia.entity.Ingrediente;
import org.advancia.mapper.IngredienteMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class IngredienteService {
	
	@Inject
    IngredienteMapper ingredienteMapper;

    //***GET***
    public IngredienteDto getIngrediente(int id) {
        return ingredienteMapper.toIngredienteDto(Ingrediente.findById(id));
    }

    public List<IngredienteDto> getAllIngredientes() {
        return ingredienteMapper.toIngredienteList(Ingrediente.findAll().list());
    }
    
    public List<IngredienteDto> findIngredienteByName(String name) {
        return ingredienteMapper.toIngredienteList(Ingrediente.findIngredienteByName(name));
    }

    //***POST***
    @Transactional
    public IngredienteDto createIngrediente(IngredienteDto ingrediente) {

        Ingrediente entity = ingredienteMapper.toIngredienteEntity(ingrediente);
        Ingrediente.persist(entity);
        entity.persistAndFlush();
       
        if(entity.isPersistent()) {
            Optional<Ingrediente> optionalIngrediente = Ingrediente.findByIdOptional(entity.id);
            entity = optionalIngrediente.orElseThrow(NotFoundException::new);
            return ingredienteMapper.toIngredienteDto(entity);
        } else {
            throw new PersistenceException();
        }

    }

    //***PUT***
    @Transactional
    public IngredienteDto updateIngrediente(int id, IngredienteDto ingrediente) {
        Ingrediente entity  = Ingrediente.findById(id);
        if(entity == null) {
            throw new WebApplicationException("Ingrediente with id of " + id + " does not exist.", 404);
        }
        ingredienteMapper.updateIngredienteEntityFromDto(ingrediente,entity);

        return ingredienteMapper.toIngredienteDto(entity);
    }

    @Transactional
    public IngredienteDto updateIngrediente(IngredienteDto ingrediente) {
        Ingrediente entity  = Ingrediente.findById(ingrediente.getId());
        if(entity == null) {
            throw new WebApplicationException("Ingrediente with id " + ingrediente.getId() + " does not exist.", 404);
        }
        ingredienteMapper.updateIngredienteEntityFromDto(ingrediente,entity);
        entity =  Ingrediente.getEntityManager().merge(entity);
        
        return ingredienteMapper.toIngredienteDto(entity);
    }

    //***DELETE***
    @Transactional
    public Response deleteIngrediente(int id) {
        boolean isEntityDeleted = Ingrediente.deleteById(id);
        if(!isEntityDeleted) {
            throw new WebApplicationException("Ingrediente with id of " + id + " does not exist.", 404);
        }

        return Response.status(204).build();
    }
}
