package org.marco.application.port.out;

import java.util.Optional;
import org.marco.application.dto.PagedResult;
import org.marco.domain.model.Cliente;
import org.marco.infraestructure.persistance.entities.ClienteEntity;

public interface ClienteRepositoryPort {

  void guardarCliente(Cliente cliente);
  Optional<PagedResult<ClienteEntity>> listar(Long id);
}
