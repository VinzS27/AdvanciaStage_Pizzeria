package org.advancia.mapper;

import java.util.List;

import org.advancia.dto.ImpastoDto;
import org.advancia.entity.Impasto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "jakarta")
public interface ImpastoMapper {

	ImpastoDto toImpastoDto(Impasto impasto);
    Impasto toImpastoEntity(ImpastoDto dto);
    List<ImpastoDto> toImpastoList(List<Impasto> impasto);
    void updateImpastoEntityFromDto(ImpastoDto dto, @MappingTarget Impasto impasto);
}
