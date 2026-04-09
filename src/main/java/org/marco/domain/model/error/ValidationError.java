package org.marco.domain.model.error;

import java.util.List;

public record ValidationError(
    String title,
    int status,
    List<FieldError> violations
) {}