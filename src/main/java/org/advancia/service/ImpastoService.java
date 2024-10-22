package org.advancia.service;

import java.util.List;
import java.util.Optional;

import org.advancia.dto.ImpastoDto;
import org.advancia.entity.Impasto;
import org.advancia.mapper.ImpastoMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ImpastoService {

	@Inject
    ImpastoMapper impastoMapper;

    //***GET***
    public ImpastoDto getImpasto(int id) {
        return impastoMapper.toImpastoDto(Impasto.findById(id));
    }

    public List<ImpastoDto> getAllImpastos() {
        return impastoMapper.toImpastoList(Impasto.findAll().list());
    }
    
    public List<ImpastoDto> findImpastoByName(String name) {
        return impastoMapper.toImpastoList(Impasto.findImpastoByName(name));
    }

    //***POST***
    @Transactional
    public ImpastoDto createImpasto(ImpastoDto impasto) {

        Impasto entity = impastoMapper.toImpastoEntity(impasto);
        Impasto.persist(entity);
        entity.persistAndFlush();
       
        if(entity.isPersistent()) {
            Optional<Impasto> optionalImpasto = Impasto.findByIdOptional(entity.id);
            entity = optionalImpasto.orElseThrow(NotFoundException::new);
            return impastoMapper.toImpastoDto(entity);
        } else {
            throw new PersistenceException();
        }

    }

    //***PUT***
    @Transactional
    public ImpastoDto updateImpasto(int id, ImpastoDto impasto) {
        Impasto entity  = Impasto.findById(id);
        if(entity == null) {
            throw new WebApplicationException("Impasto with id of " + id + " does not exist.", 404);
        }
        impastoMapper.updateImpastoEntityFromDto(impasto,entity);

        return impastoMapper.toImpastoDto(entity);
    }

    @Transactional
    public ImpastoDto updateImpasto(ImpastoDto impasto) {
        Impasto entity  = Impasto.findById(impasto.getId());
        if(entity == null) {
            throw new WebApplicationException("Impasto with id " + impasto.getId() + " does not exist.", 404);
        }
        impastoMapper.updateImpastoEntityFromDto(impasto,entity);
        entity =  Impasto.getEntityManager().merge(entity);
        
        return impastoMapper.toImpastoDto(entity);
    }

    //***DELETE***
    @Transactional
    public Response deleteImpasto(int id) {
        boolean isEntityDeleted = Impasto.deleteById(id);
        if(!isEntityDeleted) {
            throw new WebApplicationException("Impasto with id of " + id + " does not exist.", 404);
        }

        return Response.status(204).build();
    }
}
