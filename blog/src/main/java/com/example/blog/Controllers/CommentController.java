package com.example.blog.Controllers;

import com.example.blog.Entity.Comments;
import com.example.blog.Entity.Posts;
import com.example.blog.Entity.Users;
import com.example.blog.Repository.CommentsRepository;
import com.example.blog.Repository.PostsRepository;
import com.example.blog.Service.CommentService;
import com.example.blog.Service.PostService;
import com.example.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {


 @Autowired
    private CommentService commentService;
 @Autowired
    private PostService postService;
 @Autowired
    private UserService userService;



    @GetMapping(value = "/comments")
    public String getcomment(Model model, Principal principal) {
        List<Posts> posts = postService.getAllPosts();
        List<Comments> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        return "postview";
    }

    @PostMapping(value = "/addComments/{id}")
    public String addComment(@ModelAttribute Comments comments,@PathVariable Integer id, Principal principal, Model model) {
        comments.setUsers(userService.getUser(principal.getName()));
        comments.setPosts(postService.getPostById(id));
        if(comments.getCreationDate() == null)
            comments.setCreationDate(new Date());
        comments.setText(comments.getText());
        comments.setGrade(comments.getGrade());
        commentService.addComment(comments);

        return "redirect:/posts";
    }


    @GetMapping(value = "/deleteComment/{id}")
    public String deleteComment(@PathVariable int id){
        commentService.deleteById(id);
    return "redirect:/posts";
    }

    @GetMapping(value = "/commentview/{id}")
    public String edit(@PathVariable int id, Comments comments){
        comments.getId();
        comments.getText();
        comments.getGrade();
        comments.getUsers();
        comments.getPosts();
        commentService.findById(id);
        postService.findPostById(id);
        return "editcommentview";
    }

    @GetMapping(value = "/commentviews/{id}")
    public String commentsA(@PathVariable Integer id, Comments comments, Model model){
        Posts posts = postService.findById(id);
        model.addAttribute("id",posts.getId());

        return "commentview";
    }

    @GetMapping("/updatecomment/{id}")
    public String updateComment(Comments comments, @RequestParam("id") Integer id) {
        comments.setId(comments.getId());
        comments.setText(comments.getText());
        comments.setGrade(comments.getGrade());
        commentService.updateComment(comments);
        return "redirect:/posts";
    }
}








