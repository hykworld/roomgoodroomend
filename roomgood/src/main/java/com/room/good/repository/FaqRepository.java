package com.room.good.repository;


import com.room.good.entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaqRepository extends JpaRepository<FAQ,Long> {

}
