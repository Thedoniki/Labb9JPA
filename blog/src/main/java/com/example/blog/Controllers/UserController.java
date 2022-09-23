package com.example.blog.Controllers;

import com.example.blog.Entity.Comments;
import com.example.blog.Entity.Posts;
import com.example.blog.Entity.Users;
import com.example.blog.Pojo.UserRegistration;
import com.example.blog.Service.CommentService;
import com.example.blog.Service.HashService;
import com.example.blog.Service.PostService;
import com.example.blog.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;


    @PostMapping(value = "/register")
    public String register(@ModelAttribute("register") UserRegistration userRegistration) {
        if (!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation()))
            return "Password does not match";
        else if (userService.getUser(userRegistration.getUsername()) != null)
            return "User already exists";
        Users users = new Users();
        users.setUsername(userRegistration.getUsername());
        users.setRole("FOLLOWER");
        users.setPassword(HashService.hash(userRegistration.getPassword()));
        users.setEnabled(true);
        userService.save(users);
        return "redirect:/login";
    }

    @GetMapping(value = "/registration")
    public String registration() {
        return "userview";
    }

    @GetMapping(value = "/users")
    public String getusers(Model model, Principal principal) {
        List<Users> users = userService.getAllUsers();
        List<Comments> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        model.addAttribute("users", users);
        return "postview";
    }



}

