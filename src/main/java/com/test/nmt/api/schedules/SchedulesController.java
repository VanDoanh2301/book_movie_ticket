package com.test.nmt.api.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.nmt.model.scheduleRequest.MovieScheduleRequestDTO;
import com.test.nmt.service.scheduleRequest.ScheduleRequestService;

@RestController
@RequestMapping("/api/schedules")
public class SchedulesController {
    @Autowired
    private ScheduleRequestService scheduleRequestService;

    @GetMapping("{id}")
    public MovieScheduleRequestDTO getScheduleByShowTimeID(@PathVariable Long id) {
        return scheduleRequestService.getDataByShowTimeID(id);
    }

    @GetMapping("movies/{id}")
    public MovieScheduleRequestDTO getScheduleByMovieID(@PathVariable Long id) {
        return scheduleRequestService.getDataByMovieID(id);
    }
}
