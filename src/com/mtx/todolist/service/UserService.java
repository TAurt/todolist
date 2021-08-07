package com.mtx.todolist.service;

import com.mtx.todolist.dao.UserDao;
import com.mtx.todolist.dto.CreateUserDto;
import com.mtx.todolist.dto.UserDto;
import com.mtx.todolist.exception.ValidationException;
import com.mtx.todolist.mapper.CreateUserMapper;
import com.mtx.todolist.mapper.UserMapper;
import com.mtx.todolist.validator.CreateUserValidator;
import com.mtx.todolist.validator.Error;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    @SneakyThrows
    public Integer create(CreateUserDto createUserDto) {

        var validationResult = createUserValidator.isValid(createUserDto);

        if (userDao.findByEmail(createUserDto.getEmail()).isPresent()) {
            validationResult.add(Error.of("email.invalid", "Email is already existing!"));
        }

        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        var user = createUserMapper.mapFrom(createUserDto);
        if (!createUserDto.getImage().getSubmittedFileName().isEmpty()) {
            imageService.upload(user.getImage(), createUserDto.getImage().getInputStream());
        }
        var saveUser = userDao.save(user);

        return saveUser.getId();
    }

    public List<UserDto> getAll() {
        return userDao.findAll().stream()
                .map(userMapper::mapFrom)
                .collect(Collectors.toList());
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}