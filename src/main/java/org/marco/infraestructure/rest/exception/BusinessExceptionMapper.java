package org.marco.infraestructure.rest.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import org.marco.domain.model.error.ApiError;
import org.marco.domain.exception.BusinessException;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

  private static final Logger LOG =
      Logger.getLogger(BusinessExceptionMapper.class);

  @Override
  public Response toResponse(BusinessException ex) {
    LOG.infof("Business error: %s ", ex.getMessage());

    return Response.status(Status.BAD_REQUEST)
        .entity(new ApiError("BAD_REQUEST",
            ex.getMessage()
        ))
        .build();
  }
}
