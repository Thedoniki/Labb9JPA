package com.example.blog.Service;

import com.example.blog.Entity.Posts;
import com.example.blog.Repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PostService {
    @Autowired
    private PostsRepository repository;


    public Posts findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<Posts> getAllPosts() {
        return repository.findAll();
    }

    public Posts addPost(Posts posts) {
        return repository.save(posts);

    }

    public Posts deleteById(int id) {
        return repository.deleteById(id);
    }


    public Posts updatePost(Posts posts) {
        Posts existingPosts = repository.findById(posts.getId()).orElse(null);
        existingPosts.setTitle(posts.getTitle());
        existingPosts.setText(posts.getText());
        return repository.save(existingPosts);
    }
    public Posts findPostById(int id){
        return repository.findById(id);
    }

    public Posts getPostById(int id) {
        return repository.findById(id);
    }

    public List<Posts> listAll(String keyword) {
      if (keyword != null) {

    }
     return repository.findAll();
      }
    public List<Posts> search(String key) { return repository.search(key); }

}