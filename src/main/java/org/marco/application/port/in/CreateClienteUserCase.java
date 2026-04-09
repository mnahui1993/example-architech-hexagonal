package org.marco.application.port.in;

import org.marco.application.dto.PagedResult;
import org.marco.domain.model.Cliente;
import org.marco.infraestructure.persistance.entities.ClienteEntity;

public interface CreateClienteUserCase {

  void crearCliente(Cliente cliente);
   PagedResult<ClienteEntity> listar(Long id);
}
