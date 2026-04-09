package org.marco.infraestructure.persistance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.marco.domain.model.Cliente;
import org.marco.infraestructure.persistance.entities.ClienteEntity;

@Mapper(componentModel = "cdi")
public interface ClienteEntityMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "fechaRegistro", ignore = true)
  ClienteEntity toEntity(Cliente dto);
}
