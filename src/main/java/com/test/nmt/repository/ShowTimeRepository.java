package com.test.nmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.nmt.model.showTime.ShowTimeEntity;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTimeEntity, Long> {

}
