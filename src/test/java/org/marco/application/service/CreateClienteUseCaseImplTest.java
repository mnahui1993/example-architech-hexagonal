package org.marco.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.marco.application.dto.PagedResult;
import org.marco.application.port.out.ClienteRepositoryPort;
import org.marco.domain.exception.BusinessException;
import org.marco.domain.model.Cliente;
import org.marco.infraestructure.persistance.entities.ClienteEntity;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateClienteUseCaseImplTest {

  @Mock
  ClienteRepositoryPort clienteRepositoryPort;


  @InjectMocks
  CreateClienteUseCaseImpl createClienteUseCase;

  @Test
  void createClient_withValidData_shouldSaveClient() {
    Cliente cliente = new Cliente("nombre", "marcoanv14@gmail.com");
    createClienteUseCase.crearCliente(cliente);
    verify(clienteRepositoryPort).guardarCliente(cliente);
  }

  @Test
  void createClient_withForbiddenEmail_shouldThrowBusinessException() {
    Cliente cliente = new Cliente("nombre", "marcoanv13@gmail.com");

    BusinessException exception = assertThrows(
        BusinessException.class,
        () -> createClienteUseCase.crearCliente(cliente)
    );

    assertEquals("error al crear cliente", exception.getMessage());

    verify(clienteRepositoryPort, never()).guardarCliente(cliente);
  }

  @Test
  void listadoOk() {
    Cliente cliente = new Cliente("nombre", "marcoanv14@gmail.com");

    when(clienteRepositoryPort.listar(any())).thenReturn(listado());

    createClienteUseCase.listar(1L);
    verify(clienteRepositoryPort).listar(1L);
  }

  Optional<PagedResult<ClienteEntity>> listado() {

    ClienteEntity entity = new ClienteEntity();
    entity.id = 1;
    entity.fechaRegistro = LocalDateTime.now();
    entity.nombre = "marco";
    entity.email = "marcoanv14@gmail.com";

    PagedResult<ClienteEntity> pagedResult = new PagedResult<>(List.of(entity), 1, 0, 5);
    return Optional.of(pagedResult);

  }

}
