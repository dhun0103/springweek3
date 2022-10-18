package com.example.springweek3.account.entity;

import com.example.springweek3.account.dto.request.CommentRequestDto;
import com.example.springweek3.account.dto.request.PostRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "commentId")
    private Long id;

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "postid")
    @JsonIgnore
    private Post post;

    public Comment(CommentRequestDto commentRequestDto, Post post, String userEmail) {
        this.comments = commentRequestDto.getComments();
        this.post = post;
        this.userEmail=userEmail;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comments = commentRequestDto.getComments();
    }
}
