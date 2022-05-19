package com.epam.hotelbookingspring.dao;

import com.epam.hotelbookingspring.dao.dto.RequestsDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RequestsDtoRepository {
    List<RequestsDto> getAllRequestsByUserId(Long userId);
}
