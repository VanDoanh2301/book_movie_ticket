package com.test.nmt.service.scheduleRequest;

import org.springframework.stereotype.Service;

import com.test.nmt.model.scheduleRequest.MovieScheduleRequestDTO;

@Service
public interface ScheduleRequestService {
    public MovieScheduleRequestDTO getDataByShowTimeID(Long id);

    public MovieScheduleRequestDTO getDataByMovieID(Long id);

}
