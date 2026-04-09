package org.marco.infraestructure.rest;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.marco.application.dto.PagedResult;
import org.marco.application.port.in.CreateClienteUserCase;
import org.marco.infraestructure.persistance.entities.ClienteEntity;
import org.marco.infraestructure.rest.dto.ClienteCreateRequest;
import org.marco.infraestructure.rest.mapper.ClienteRequestMapper;

@Path("/clientes")
public class ClienteResource {

  @Inject
  private CreateClienteUserCase primerService;
  private final ClienteRequestMapper clienteRequestMapper;

  public ClienteResource(ClienteRequestMapper clienteRequestMapper) {
    this.clienteRequestMapper = clienteRequestMapper;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response crearCliente(@Valid ClienteCreateRequest clienteCreateRequest) {
    primerService.crearCliente(clienteRequestMapper.toDomain(clienteCreateRequest));
    return Response.status(Response.Status.CREATED).build();
  }


  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public PagedResult<ClienteEntity> obtenerCliente(@PathParam("id") Long id) {
    return primerService.listar(id);
  }
}
