package org.advancia.service;

import java.util.List;
import java.util.Optional;

import org.advancia.dto.UtenteDto;
import org.advancia.entity.Utente;
import org.advancia.mapper.UtenteMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class UtenteService {

    @Inject
    UtenteMapper utenteMapper;

    //***GET***
    public UtenteDto getUtente(int id) {
        return utenteMapper.toUtenteDto(Utente.findById(id));
    }

    public List<UtenteDto> getAllUtentes() {
        return utenteMapper.toUtenteList(Utente.findAll().list());
    }
    
    public List<UtenteDto> findUtenteByUsername(String username) {
        return utenteMapper.toUtenteList(Utente.findUtenteByUsername(username));
    }

    //***POST***
    @Transactional
    public UtenteDto createUtente(UtenteDto utente) {

        Utente entity = utenteMapper.toUtenteEntity(utente);
        Utente.persist(entity);
        entity.persistAndFlush();
       
        if(entity.isPersistent()) {
            Optional<Utente> optionalUtente = Utente.findByIdOptional(entity.id);
            entity = optionalUtente.orElseThrow(NotFoundException::new);
            return utenteMapper.toUtenteDto(entity);
        } else {
            throw new PersistenceException();
        }

    }

    //***PUT***
    @Transactional
    public UtenteDto updateUtente(int id, UtenteDto utente) {
        Utente entity  = Utente.findById(id);
        if(entity == null) {
            throw new WebApplicationException("Utente with id of " + id + " does not exist.", 404);
        }
        utenteMapper.updateUtenteEntityFromDto(utente,entity);

        return utenteMapper.toUtenteDto(entity);
    }

    @Transactional
    public UtenteDto updateUtente(UtenteDto utente) {
        Utente entity  = Utente.findById(utente.getId());
        if(entity == null) {
            throw new WebApplicationException("Utente with id " + utente.getId() + " does not exist.", 404);
        }
        utenteMapper.updateUtenteEntityFromDto(utente,entity);
        entity =  Utente.getEntityManager().merge(entity);
        
        return utenteMapper.toUtenteDto(entity);
    }

    //***DELETE***
    @Transactional
    public Response deleteUtente(int id) {
        boolean isEntityDeleted = Utente.deleteById(id);
        if(!isEntityDeleted) {
            throw new WebApplicationException("Utente with id of " + id + " does not exist.", 404);
        }

        return Response.status(204).build();
    }
}
