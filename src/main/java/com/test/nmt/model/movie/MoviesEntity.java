package com.test.nmt.model.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class MoviesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieID;
    private String movieName;
    private String description;
    private String banner_url;

    public MoviesEntity loadFromDTO(MovieDTO dto) {
        this.movieID = dto.getMovieID();
        this.movieName = dto.getMovieName();
        this.description = dto.getDescription();
        this.banner_url = dto.getBanner_url();
        return this;
    }
}
