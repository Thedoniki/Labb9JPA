package com.example.blog.Service;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashService {
    public static String hash(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt(12));
    }
}
