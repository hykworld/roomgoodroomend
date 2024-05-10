package com.room.good.repository;


import com.room.good.entity.FAQ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<FAQ,Long> {
    Page<FAQ> findByFaqtitleContaining(String searchValue, Pageable pageable);
}
