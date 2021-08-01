package com.mtx.todolist.service;

import com.mtx.todolist.dao.UserDao;
import com.mtx.todolist.dto.CreateUserDto;
import com.mtx.todolist.mapper.CreateUserMapper;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    public Integer create(CreateUserDto createUserDto) {
        //TODO validate createUserDto
        var user = createUserMapper.mapFrom(createUserDto);
        //TODO create image save
        var saveUser = userDao.save(user);
        return saveUser.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
