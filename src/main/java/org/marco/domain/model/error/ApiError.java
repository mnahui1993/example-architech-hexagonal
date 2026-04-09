package org.marco.domain.model.error;

public record ApiError(
    String code,
    String message
) {}
