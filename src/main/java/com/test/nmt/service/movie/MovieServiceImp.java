package com.test.nmt.service.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.nmt.model.movie.MovieDTO;
import com.test.nmt.model.movie.MoviesEntity;
import com.test.nmt.repository.MovieRepository;

@Service
public class MovieServiceImp implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieDTO findById(Long id) {
        MovieDTO dto = new MovieDTO();
        dto.loadFromEntity(movieRepository.findById(id).orElse(null));
        return dto;
    }

    @Override
    public List<MovieDTO> findAll() {
        return movieRepository.findAll().stream().skip(0).map(movie -> new MovieDTO().loadFromEntity(movie))
                .collect(Collectors.toList());
    }

    @Override
    public void createMovie(MovieDTO movieDTO) {
        movieRepository.save(new MoviesEntity().loadFromDTO(movieDTO));
    }

    @Override
    public void deleteMovieByID(Long id) {
        movieRepository.deleteById(id);
    }
}
