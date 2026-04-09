package org.marco.infraestructure.persistance;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.marco.application.dto.PagedResult;
import org.marco.domain.model.Cliente;
import org.marco.infraestructure.persistance.entities.ClienteEntity;
import org.marco.infraestructure.persistance.mapper.ClienteEntityMapper;
import org.marco.infraestructure.persistance.repository.ClienteRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClienteRepositoryAdapterTest {

  @Mock
  ClienteRepository repository;

  @Mock
  ClienteEntityMapper mapper;

  @InjectMocks
  ClienteRepositoryAdapter adapter;

  @Test
  void guardarCliente() {

    Cliente cliente = new Cliente("marco", "marcoanv14@gmail.com");
    ClienteEntity entity = new ClienteEntity();

    entity.id = 1;
    entity.fechaRegistro = LocalDateTime.now();
    entity.nombre = "marco";
    entity.email = "marcoanv14@gmail.com";

    when(mapper.toEntity(any(Cliente.class))).thenReturn(entity);

    adapter.guardarCliente(cliente);

    verify(repository).persist(entity);
  }

  @Test
  void paginadoTest(){


    adapter.listar(1L);
    verify(repository).listarPaginado(0,5);

  }



}