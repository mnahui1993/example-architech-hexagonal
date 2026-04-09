package org.marco.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;
import org.marco.application.dto.PagedResult;
import org.marco.application.port.in.CreateClienteUserCase;
import org.marco.application.port.out.ClienteRepositoryPort;
import org.marco.domain.exception.BusinessException;
import org.marco.domain.model.Cliente;
import org.marco.infraestructure.persistance.entities.ClienteEntity;

@ApplicationScoped
public class CreateClienteUseCaseImpl implements CreateClienteUserCase {

  private static final Logger LOG = Logger.getLogger(CreateClienteUseCaseImpl.class);

  private final ClienteRepositoryPort clienteRepositoryPort;

  private final static String EMAIL_PROHIBIDO = "marcoanv13@gmail.com";

  public CreateClienteUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort) {
    this.clienteRepositoryPort = clienteRepositoryPort;
  }


  @Transactional
  @Override
  public void crearCliente(Cliente clienteDto) {
    if (clienteDto.email().equals(EMAIL_PROHIBIDO)) {
      throw new BusinessException("error al crear cliente");
    }
    clienteRepositoryPort.guardarCliente(clienteDto);

    LOG.infof("cliente registrado exitosamente con Id : %s", 1L);


  }

  public PagedResult<ClienteEntity> listar(Long id){
    return clienteRepositoryPort.listar(id)
        .orElseThrow(() -> new BusinessException("Cliente no encontrado"));
  }

}
