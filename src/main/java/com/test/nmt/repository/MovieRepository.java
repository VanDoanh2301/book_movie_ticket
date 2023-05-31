package com.test.nmt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.nmt.model.movie.MoviesEntity;

@Repository
public interface MovieRepository extends JpaRepository<MoviesEntity, Long> {

    Page<MoviesEntity> findByMovieNameContaining(String name,Pageable pageable);

}
