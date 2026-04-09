package org.marco.infraestructure.persistance;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.Optional;
import org.marco.application.dto.PagedResult;
import org.marco.application.port.out.ClienteRepositoryPort;
import org.marco.domain.model.Cliente;
import org.marco.infraestructure.persistance.entities.ClienteEntity;
import org.marco.infraestructure.persistance.mapper.ClienteEntityMapper;
import org.marco.infraestructure.persistance.repository.ClienteRepository;

@ApplicationScoped
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

  private final ClienteRepository repository;
  private final ClienteEntityMapper clienteMapper;

  public ClienteRepositoryAdapter(ClienteRepository repository, ClienteEntityMapper clienteMapper) {
    this.repository = repository;
    this.clienteMapper = clienteMapper;
  }

  @Override
  public void guardarCliente(Cliente cliente) {

    ClienteEntity clie = clienteMapper.toEntity(cliente);
    clie.fechaRegistro= LocalDateTime.now();
    repository.persist(clie);
  }

  @Override
  public Optional<PagedResult<ClienteEntity>> listar(Long id) {
    return repository.listarPaginado(0,5);
  }
}
