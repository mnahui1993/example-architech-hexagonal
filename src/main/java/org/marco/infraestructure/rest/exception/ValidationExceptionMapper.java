package org.marco.infraestructure.rest.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.List;
import org.jboss.logging.Logger;
import org.marco.domain.model.error.FieldError;
import org.marco.domain.model.error.ValidationError;

@Provider
public class ValidationExceptionMapper
    implements ExceptionMapper<ConstraintViolationException> {

  private static final Logger LOG =
      Logger.getLogger(ValidationExceptionMapper.class);

  @Override
  public Response toResponse(ConstraintViolationException ex) {
    LOG.infof("Validation error: %s ", ex.getMessage());
    List<FieldError> errors = ex.getConstraintViolations()
        .stream()
        .map(v -> {
          String field = v.getPropertyPath()
              .toString()
              .replaceAll(".*\\.", "");

          return new FieldError(field, v.getMessage());
        })
        .toList();

    return Response.status(Response.Status.BAD_REQUEST)
        .entity(
            new ValidationError("Constraint Violation", Response.Status.BAD_REQUEST.getStatusCode(),
                errors))
        .build();
  }
}
