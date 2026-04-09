package org.marco.infraestructure.rest.mapper;

import org.mapstruct.Mapper;
import org.marco.domain.model.Cliente;
import org.marco.infraestructure.rest.dto.ClienteCreateRequest;

@Mapper(componentModel = "cdi")
public interface ClienteRequestMapper {

  Cliente toDomain(ClienteCreateRequest clienteCreateRequest);
}
