package com.mtx.todolist.validator;

import com.mtx.todolist.dto.CreateUserDto;
import com.mtx.todolist.entity.Gender;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto createUserDto) {
        ValidationResult validationResult = new ValidationResult();

        if (Gender.find(createUserDto.getGender()).isEmpty()) {
            validationResult.add(Error.of("gender.invalid", "Gender is invalid"));
        }

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
