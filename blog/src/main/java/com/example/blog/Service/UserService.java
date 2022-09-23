package com.example.blog.Service;

import com.example.blog.Entity.Posts;
import com.example.blog.Entity.Users;
import com.example.blog.Repository.PostsRepository;
import com.example.blog.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UsersRepository repository;

    public Users findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    public Users findByUsername(String username) {
        return repository.findByUsername(username);
    }



    public void save(Users users) {
        repository.save(users);
    }

    public Users getUser(String username){
        return repository.findByUsername(username);
    }



    public List<Users> search(String key) { return repository.search(key); }


}



