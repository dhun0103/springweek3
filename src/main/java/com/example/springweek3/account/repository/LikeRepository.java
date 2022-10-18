package com.sparta.jwt_submit_try4.repository;

import com.sparta.jwt_submit_try4.entity.Like;
import com.sparta.jwt_submit_try4.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findLikeByUserIdAndPostId(Member member, String postId);
}
