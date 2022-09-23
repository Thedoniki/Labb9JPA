package com.example.blog.Controllers;


import com.example.blog.Entity.Comments;
import com.example.blog.Entity.Posts;
import com.example.blog.Entity.Users;
import com.example.blog.Pojo.UserRegistration;
import com.example.blog.Service.CommentService;
import com.example.blog.Service.HashService;
import com.example.blog.Service.PostService;
import com.example.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.Date;
import java.util.List;


@Controller
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    private Users users;



    @GetMapping(value = "/posts")
    public String getpost(Model model, Principal principal) {
        List<Posts> posts = postService.getAllPosts();
        List<Comments> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        model.addAttribute("posts", posts);
        return "postview";
    }

    @PostMapping(value = "/addPost")
    public String addPost(@ModelAttribute("posts") Posts posts, Principal principal) {
        posts.setUsers(userService.getUser(principal.getName()));
        if(posts.getCreationDate() == null)
            posts.setCreationDate(new Date());
        Posts post = new Posts();
        posts.setTitle(posts.getTitle());
        posts.setText(posts.getText());
        postService.addPost(posts);
        return "redirect:/posts";



    }
    @GetMapping(value = "/deletePost/{id}")
    public String deletePost(@PathVariable int id){
        postService.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping(value = "/editview/{id}")
    public String edit(@PathVariable int id, Posts posts){
            posts.getId();
            posts.getTitle();
            posts.getText();
            postService.findById(id);
            return "editview";
    }

    //@GetMapping("/postById/{id}")
    //public String findPostById(@PathVariable int id, Posts posts){
        //posts.getTitle();
        //posts.getText();
        //postService.findPostById(id);
       // return "editview";
    //}


    @GetMapping("/updatePost/{id}")
    public String updatePost(Posts posts, @RequestParam ("id") Integer id) {
        posts.setId(posts.getId());
        posts.setTitle(posts.getTitle());
        posts.setText(posts.getText());
        postService.updatePost(posts);
        return "redirect:/posts";
    }


    }






