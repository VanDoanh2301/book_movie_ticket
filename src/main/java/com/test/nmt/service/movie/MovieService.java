package com.test.nmt.service.movie;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.nmt.model.movie.MovieDTO;

@Service
public interface MovieService {
    public MovieDTO findById(Long id);

    public List<MovieDTO> findAll();

    public void createMovie(MovieDTO movieDTO);

    public void deleteMovieByID(Long id);
}
