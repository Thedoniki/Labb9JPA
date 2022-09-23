package com.example.blog.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

public class Posts{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String text;
    private Date creationDate;

@OneToMany(
    mappedBy = "posts",
     cascade = CascadeType.ALL,
    orphanRemoval = true
    )

private List<Comments> commentsList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;


public void addposts(Comments comments){
    commentsList.add(comments);
    comments.setPosts(this);
}

    public Posts(String title, String text, Date creationDate) {
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
    }

    public Posts(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}



