package org.advancia.mapper;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.advancia.dto.UtenteDto;
import org.advancia.entity.Pizza;
import org.advancia.entity.Utente;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-22T10:23:26+0200",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 23 (Eclipse Adoptium)"
)
@Singleton
@Named
public class UtenteMapperImpl implements UtenteMapper {

    @Override
    public UtenteDto toUtenteDto(Utente utente) {
        if ( utente == null ) {
            return null;
        }

        UtenteDto utenteDto = new UtenteDto();

        utenteDto.setId( utente.id );
        utenteDto.setUsername( utente.username );
        utenteDto.setPassword( utente.password );
        List<Pizza> list = utente.pizza;
        if ( list != null ) {
            utenteDto.setPizza( new ArrayList<Pizza>( list ) );
        }

        return utenteDto;
    }

    @Override
    public Utente toUtenteEntity(UtenteDto dto) {
        if ( dto == null ) {
            return null;
        }

        Utente utente = new Utente();

        utente.id = dto.getId();
        utente.username = dto.getUsername();
        utente.password = dto.getPassword();
        List<Pizza> list = dto.getPizza();
        if ( list != null ) {
            utente.pizza = new ArrayList<Pizza>( list );
        }

        return utente;
    }

    @Override
    public List<UtenteDto> toUtenteList(List<Utente> utente) {
        if ( utente == null ) {
            return null;
        }

        List<UtenteDto> list = new ArrayList<UtenteDto>( utente.size() );
        for ( Utente utente1 : utente ) {
            list.add( toUtenteDto( utente1 ) );
        }

        return list;
    }

    @Override
    public void updateUtenteEntityFromDto(UtenteDto dto, Utente utente) {
        if ( dto == null ) {
            return;
        }

        utente.id = dto.getId();
        utente.username = dto.getUsername();
        utente.password = dto.getPassword();
        if ( utente.pizza != null ) {
            List<Pizza> list = dto.getPizza();
            if ( list != null ) {
                utente.pizza.clear();
                utente.pizza.addAll( list );
            }
            else {
                utente.pizza = null;
            }
        }
        else {
            List<Pizza> list = dto.getPizza();
            if ( list != null ) {
                utente.pizza = new ArrayList<Pizza>( list );
            }
        }
    }
}
