package com.epam.hotelbookingspring.service;

import com.epam.hotelbookingspring.dao.dto.RequestsDto;
import com.epam.hotelbookingspring.model.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class RequestMapper {
    abstract RequestsDto toDto(Request request);
}
