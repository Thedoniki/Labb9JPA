package com.example.blog.Service;

import com.example.blog.Entity.Comments;
import com.example.blog.Entity.Posts;
import com.example.blog.Repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentsRepository repository;


    public Comments findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<Comments> getAllComments() {
        return repository.findAll();
    }

    public Comments addComment(Comments comments) {
        return repository.save(comments);

    }

    public Comments deleteById(int id) {
        return repository.deleteById(id);
    }


    public Comments updateComment(Comments comments) {
        Comments existingComments = repository.findById(comments.getId()).orElse(null);
        existingComments.setText(comments.getText());
        existingComments.setGrade(comments.getGrade());
        return repository.save(existingComments);
    }
    public Comments findCommentById(int id){
        return repository.findById(id);
    }




    public List<Comments> search(String key) { return repository.search(key); }
    public void insert(Comments comments) {
    }


}

