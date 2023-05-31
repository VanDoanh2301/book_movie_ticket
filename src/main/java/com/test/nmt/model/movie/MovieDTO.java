package com.test.nmt.model.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long movieID;
    private String movieName;
    private String description;
    private String banner_url;

    public MovieDTO loadFromEntity(MoviesEntity entity) {
        this.movieID = entity.getMovieID();
        this.movieName = entity.getMovieName();
        this.description = entity.getDescription();
        this.banner_url = entity.getBanner_url();
        return this;
    }
}
