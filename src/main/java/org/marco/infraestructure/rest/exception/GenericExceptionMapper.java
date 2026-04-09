package org.marco.infraestructure.rest.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import org.marco.domain.model.error.ApiError;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

  private static final Logger LOG =
      Logger.getLogger(GenericExceptionMapper.class);

  @Override
  public Response toResponse(Throwable ex) {

    LOG.infof("unxceptio error: %s ", ex.getMessage());
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(new ApiError(
            "INTERNAL_ERROR",
            "Ocurrió un error inesperado"
        ))
        .build();
  }
}

