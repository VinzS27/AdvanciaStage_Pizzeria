package org.advancia.mapper;

import org.advancia.dto.UtenteDto;
import org.advancia.entity.Utente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface UtenteMapper {
    
	UtenteDto toUtenteDto(Utente utente);
    Utente toUtenteEntity(UtenteDto dto);
    List<UtenteDto> toUtenteList(List<Utente> utente);
    void updateUtenteEntityFromDto(UtenteDto dto, @MappingTarget Utente utente);

}
