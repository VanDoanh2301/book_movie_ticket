package com.test.nmt.api.movie;

import java.util.List;
import java.util.Optional;

import com.test.nmt.model.movie.MoviesEntity;
import com.test.nmt.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.nmt.model.movie.MovieDTO;
import com.test.nmt.service.movie.MovieService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepo;

    @GetMapping
    public List<MovieDTO> getAllMovie() {
        return movieService.findAll();
    }

    @GetMapping("{id}")
    public MovieDTO getMovieById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping("/create")
    public void createMovie(@RequestBody MovieDTO movieDTO) {
        movieService.createMovie(movieDTO);
    }


    @PostMapping("/update")
    public void updateMovie(@RequestBody MovieDTO movieDTO) {
        movieService.createMovie(movieDTO);
    }

    @PostMapping("/delete")

    public void deleteMovie(@RequestParam(name = "id") Long id) {
        movieService.deleteMovieByID(id);
    }

    @GetMapping("page")
    public ResponseEntity<?> getAllDoctor(@RequestParam(value = "name",required = false) String name
            , @RequestParam(value = "page") Optional<Integer> page
            , @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(30);

        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("movieName"));
        Page<MoviesEntity> resultPage = null;
        if (StringUtils.hasText(name)) {
            resultPage = movieRepo.findByMovieNameContaining(name, pageable);

        } else {
            resultPage =movieRepo.findAll(pageable);
        }
        return ResponseEntity.ok(resultPage);
    }

}
