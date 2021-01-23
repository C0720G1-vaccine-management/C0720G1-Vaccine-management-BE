package com.project.service;

import com.project.entity.Position;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionService {
    @Query(value = "select * from position;", nativeQuery = true)
    List<Position> getAllPosition();
}
