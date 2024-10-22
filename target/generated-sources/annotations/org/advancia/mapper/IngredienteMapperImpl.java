package org.advancia.mapper;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.advancia.dto.IngredienteDto;
import org.advancia.entity.Ingrediente;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-22T10:23:26+0200",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 23 (Eclipse Adoptium)"
)
@Singleton
@Named
public class IngredienteMapperImpl implements IngredienteMapper {

    @Override
    public IngredienteDto toIngredienteDto(Ingrediente ingrediente) {
        if ( ingrediente == null ) {
            return null;
        }

        IngredienteDto ingredienteDto = new IngredienteDto();

        ingredienteDto.setId( ingrediente.id );
        ingredienteDto.setNome( ingrediente.nome );

        return ingredienteDto;
    }

    @Override
    public Ingrediente toIngredienteEntity(IngredienteDto dto) {
        if ( dto == null ) {
            return null;
        }

        Ingrediente ingrediente = new Ingrediente();

        ingrediente.id = dto.getId();
        ingrediente.nome = dto.getNome();

        return ingrediente;
    }

    @Override
    public List<IngredienteDto> toIngredienteList(List<Ingrediente> ingrediente) {
        if ( ingrediente == null ) {
            return null;
        }

        List<IngredienteDto> list = new ArrayList<IngredienteDto>( ingrediente.size() );
        for ( Ingrediente ingrediente1 : ingrediente ) {
            list.add( toIngredienteDto( ingrediente1 ) );
        }

        return list;
    }

    @Override
    public void updateIngredienteEntityFromDto(IngredienteDto dto, Ingrediente ingrediente) {
        if ( dto == null ) {
            return;
        }

        ingrediente.id = dto.getId();
        ingrediente.nome = dto.getNome();
    }
}
