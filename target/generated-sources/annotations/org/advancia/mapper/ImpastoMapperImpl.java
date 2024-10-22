package org.advancia.mapper;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.advancia.dto.ImpastoDto;
import org.advancia.entity.Impasto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-22T10:23:26+0200",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 23 (Eclipse Adoptium)"
)
@Singleton
@Named
public class ImpastoMapperImpl implements ImpastoMapper {

    @Override
    public ImpastoDto toImpastoDto(Impasto impasto) {
        if ( impasto == null ) {
            return null;
        }

        ImpastoDto impastoDto = new ImpastoDto();

        impastoDto.setId( impasto.id );
        impastoDto.setNome( impasto.nome );

        return impastoDto;
    }

    @Override
    public Impasto toImpastoEntity(ImpastoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Impasto impasto = new Impasto();

        impasto.id = dto.getId();
        impasto.nome = dto.getNome();

        return impasto;
    }

    @Override
    public List<ImpastoDto> toImpastoList(List<Impasto> impasto) {
        if ( impasto == null ) {
            return null;
        }

        List<ImpastoDto> list = new ArrayList<ImpastoDto>( impasto.size() );
        for ( Impasto impasto1 : impasto ) {
            list.add( toImpastoDto( impasto1 ) );
        }

        return list;
    }

    @Override
    public void updateImpastoEntityFromDto(ImpastoDto dto, Impasto impasto) {
        if ( dto == null ) {
            return;
        }

        impasto.id = dto.getId();
        impasto.nome = dto.getNome();
    }
}
