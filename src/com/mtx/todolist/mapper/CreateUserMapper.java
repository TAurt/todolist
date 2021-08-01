package com.mtx.todolist.mapper;

import com.mtx.todolist.dto.CreateUserDto;
import com.mtx.todolist.entity.Gender;
import com.mtx.todolist.entity.Role;
import com.mtx.todolist.entity.User;
import com.mtx.todolist.util.LocalDateFormatter;
import com.mtx.todolist.util.PasswordUtil;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User>{
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private static final String IMAGE_FOLDER = "users";

    @Override
    public User mapFrom(CreateUserDto createUserDto) {
        return User.builder()
                .name(createUserDto.getName())
                .email(createUserDto.getEmail())
                .birthday(LocalDateFormatter.format(createUserDto.getBirthday()))
                .registeredDate(LocalDate.now())
                .password(PasswordUtil.encode(createUserDto.getPassword()))
                .gender(Gender.valueOf(createUserDto.getGender()))
                .role(Role.valueOf(createUserDto.getRole()))
                .image(IMAGE_FOLDER + File.separator + createUserDto.getImage().getSubmittedFileName())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
