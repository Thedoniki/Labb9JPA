package com.example.blog.Repository;

import com.example.blog.Entity.Posts;
import com.example.blog.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository <Users, Integer> {
    Users findByUsername(String username);


    @Query("SELECT u FROM Users u WHERE CONCAT(u.id, u.username) LIKE %?1%")
    List<Users> search(String keyword);
}
