package com.example.blog.Controllers;

import com.example.blog.Entity.Comments;
import com.example.blog.Entity.Posts;
import com.example.blog.Entity.Users;
import com.example.blog.Repository.CommentsRepository;
import com.example.blog.Repository.PostsRepository;
import com.example.blog.Repository.UsersRepository;
import com.example.blog.Service.CommentService;
import com.example.blog.Service.PostService;
import com.example.blog.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.stream.events.Comment;
import java.security.Principal;
import java.util.List;

@Controller
public class searchController {

    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;


    @GetMapping("/search")
    public String searchPage(Model model, @RequestParam(required = false) String search, Principal principal) {
        List<Users> usersList;
        List<Posts> postsList;
        List<Comments> commentsList;


        if (search == null) {
            postsList = postService.getAllPosts();
            usersList = userService.getAllUsers();
            commentsList = commentService.getAllComments();
        } else if (search.equals("")) {
            postsList = postService.getAllPosts();
            usersList = userService.getAllUsers();
            commentsList = commentService.getAllComments();

        } else {
            postsList = postService.search(search);
            usersList = userService.search(search);
            commentsList = commentService.search(search);
        }

        model.addAttribute("users", usersList);
        model.addAttribute("posts", postsList);
        return "search";



    }
}


