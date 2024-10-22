package org.advancia.mapper;

import java.util.List;

import org.advancia.dto.IngredienteDto;
import org.advancia.entity.Ingrediente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "jakarta")
public interface IngredienteMapper {

	IngredienteDto toIngredienteDto(Ingrediente ingrediente);
    Ingrediente toIngredienteEntity(IngredienteDto dto);
    List<IngredienteDto> toIngredienteList(List<Ingrediente> ingrediente);
    void updateIngredienteEntityFromDto(IngredienteDto dto, @MappingTarget Ingrediente ingrediente);
}
