package com.room.good.repository;

import com.room.good.entity.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartMemberRepository extends JpaRepository<ClubMember, Long> {
    Optional<ClubMember> findById(Long id);
}
