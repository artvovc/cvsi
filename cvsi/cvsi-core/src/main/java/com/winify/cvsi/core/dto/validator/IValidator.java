package com.winify.cvsi.core.dto.validator;

public interface IValidator<T> {
    Boolean isValid(T request);
}
