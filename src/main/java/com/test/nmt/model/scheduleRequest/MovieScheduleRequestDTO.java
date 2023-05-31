package com.test.nmt.model.scheduleRequest;

import java.util.List;

import com.test.nmt.model.movie.MovieDTO;
import com.test.nmt.model.showTime.ShowTimeDetailsDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieScheduleRequestDTO {
    private MovieDTO movieDTO;
    private List<ShowTimeDetailsDTO> showTimeDetailsDTOs;
}
