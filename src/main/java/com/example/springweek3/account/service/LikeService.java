package com.sparta.jwt_submit_try4.service;

import com.sparta.jwt_submit_try4.controller.dto.LikeRequestDto;
import com.sparta.jwt_submit_try4.entity.Like;
import com.sparta.jwt_submit_try4.entity.Member;
import com.sparta.jwt_submit_try4.entity.Post;
import com.sparta.jwt_submit_try4.repository.LikeRepository;
import com.sparta.jwt_submit_try4.repository.MemberRepository;
import com.sparta.jwt_submit_try4.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final MemberService memberService;

    public void like(Long PostId,LikeRequestDto likeRequestDto) throws IOException {
        if(findLikeWithMemberAndPostId(likeRequestDto).isPresent()){
            throw new IOException();
        }

        Member member = memberService.getInfo();
        Post post = likeRequestDto.getPostId();
        Like like = new Like();
        likeRepository.save(like);
    }

    public void disLike(LikeRequestDto likeRequestDto) throws IOException{
        Optional<Like> likes = findLikeWithMemberAndPostId(likeRequestDto);
        likeRepository.delete(likes.get());
    }
    public Optional<Like> findLikeWithMemberAndPostId(LikeRequestDto likeRequestDto){
        return likeRepository.findLikeByUserIdAndPostId(member,likeRequestDto.getPostId());
    }
}
