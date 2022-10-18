package com.sparta.jwt_submit_try4.controller.dto;

import com.sparta.jwt_submit_try4.entity.Member;
import com.sparta.jwt_submit_try4.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LikeRequestDto {
    private Long LikeId;
    private Long PostId;
    private Long UserId;

    public LikeRequestDto(Member member, Post post) {
        this.PostId = post.getId();
        this.UserId = member.getId();
    }
}
