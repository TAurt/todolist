package com.mtx.todolist.validator;

public interface Validator<T> {
    ValidationResult isValid(T object);
}
