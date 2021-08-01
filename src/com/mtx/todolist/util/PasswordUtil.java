package com.mtx.todolist.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PasswordUtil {

    public String encode(String password) {
        return password;
    }

    public boolean authentication(String password, String encodePassword) {
        return  encodePassword.equals(encode(password));
    }
}
