package com.test.nmt.service.showTime;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.nmt.model.showTime.ShowTimeDTO;
import com.test.nmt.model.showTime.ShowTimeEntity;
import com.test.nmt.repository.ShowTimeRepository;

@Service
public class ShowtimeServiceImp implements ShowTimeService {
    @Autowired
    private ShowTimeRepository showTimeRepository;

    @Override
    public ShowTimeDTO getByID(Long id) {
        return new ShowTimeDTO().loadFromEntity(showTimeRepository.findById(id).orElse(null));
    }

    @Override
    public List<ShowTimeDTO> getAll() {
        return showTimeRepository.findAll().stream().map(show -> new ShowTimeDTO().loadFromEntity(show))
                .collect(Collectors.toList());
    }

    @Override
    public void increaseTicketSold(Long id) {
        ShowTimeEntity showTimeEntity = showTimeRepository.findById(id).orElse(null);
        showTimeEntity.setNumTicketSold(showTimeEntity.getNumTicketSold() + 1);
        showTimeRepository.save(showTimeEntity);
    }

    @Override
    public void createShowTime(ShowTimeDTO showTimeDTO) {
        showTimeRepository.save(new ShowTimeEntity().loadFromDTO(showTimeDTO));
    }

    @Override
    public void deleteShowTimeByID(Long id) {
        showTimeRepository.deleteById(id);
    }

    @Override
    public void deleteAllShowTimeByMovieID(Long id) {
        List<ShowTimeEntity> ids = showTimeRepository.findAll().stream()
                .filter(show -> show.getMovieID().equals(id)).collect(Collectors.toList());
        ids.stream().forEach(showid -> showTimeRepository.delete(showid));
    }
}