package com.example.blog.Repository;


import com.example.blog.Entity.Comments;
import com.example.blog.Entity.Posts;
import com.example.blog.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    Comments deleteById(int id);
    Comments findById(int id);


    @Query("SELECT c FROM Comments c WHERE CONCAT(c.text, c.grade, c.creationDate) LIKE %?1%")
    List<Comments> search(String keyword);


    @Query("SELECT text, grade, creationDate FROM Comments ORDER BY creationDate DESC, grade DESC")
    List<Comments> sortbyGradeAndDate(Comments comments);

}

