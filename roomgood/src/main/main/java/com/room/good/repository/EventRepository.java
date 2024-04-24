package com.room.good.repository;

import com.room.good.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event,Long> {


    @Query("select e, ei from Event e " +
            "left outer join EventImage ei on ei.event = e " +
            " group by e ")
    Page<Object[]> getListPage(Pageable pageable);
    // select Movie.* ,MovieImage.* ,  avg(coalesce(Review.grade,0)), count(Review.rno)
    // from Movie left outer join MovieImage on Movie.mno = MovieImage.movie_mno
    // left outer join review on  Movie.bno = review.movie_mno
    // group by movie.mno;
}
