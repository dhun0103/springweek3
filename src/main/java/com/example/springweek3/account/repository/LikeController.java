package com.sparta.jwt_submit_try4.controller;

import com.sparta.jwt_submit_try4.controller.dto.LikeRequestDto;
import com.sparta.jwt_submit_try4.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {
    private LikeService likeService;

    @PostMapping
    public ResponseEntity<LikeRequestDto> like(@RequestBody @Valid LikeRequestDto likeRequestDto){
        likeService.like(likeRequestDto);
    }
}
