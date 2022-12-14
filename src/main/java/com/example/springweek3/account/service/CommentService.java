package com.example.springweek3.account.service;

import com.example.springweek3.account.dto.request.CommentRequestDto;
import com.example.springweek3.account.dto.response.CommentResponseDto;
import com.example.springweek3.account.dto.response.PostResponseDto;
import com.example.springweek3.account.entity.Comment;
import com.example.springweek3.account.entity.Post;
import com.example.springweek3.account.repository.CommentRepository;
import com.example.springweek3.account.repository.PostRepository;
import com.example.springweek3.global.dto.GlobalResponseDto;
import com.example.springweek3.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private final CommentRepository commentRepository;


    @Transactional
    public GlobalResponseDto createComment(CommentRequestDto commentRequestDto, @RequestHeader("ACCESS_TOKEN") String token) {

        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(() ->
                new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );

        String email = jwtUtil.getEmailFromToken(token);

        Comment comment = new Comment(commentRequestDto, post, email);
        commentRepository.save(comment);

        return new GlobalResponseDto("Success Save Comment", HttpStatus.OK.value());
    }

    @Transactional
    public CommentResponseDto findDetail(Long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );

        return new CommentResponseDto(comment);
    }

    @Transactional
    public GlobalResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, String token) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );

        String email = jwtUtil.getEmailFromToken(token);

        if(!email.equals(comment.getUserEmail())){
            throw new RuntimeException("Account Check");
        }

        comment.update(commentRequestDto);

        return new GlobalResponseDto("Success Update Comment", HttpStatus.OK.value());
    }

    @Transactional
    public GlobalResponseDto deleteComment(Long commentId, String token) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );

        String email = jwtUtil.getEmailFromToken(token);

        if(!email.equals(comment.getUserEmail())){
            throw new RuntimeException("Account Check");
        }

        postRepository.deleteById(commentId);

        return new GlobalResponseDto("Success Delete Comment", HttpStatus.OK.value());
    }
}
