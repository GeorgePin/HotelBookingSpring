package com.epam.hotelbookingspring.dao.impl;

import com.epam.hotelbookingspring.dao.RequestsDtoRepository;
import com.epam.hotelbookingspring.dao.dto.RequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RequestsDtoRepositoryImpl implements RequestsDtoRepository {
    private final EntityManager entityManager;
    private static final String SELECT_ALL_USER_REQUESTS = "SELECT reservation.*, room_price.price FROM "
            + "reservation LEFT JOIN room ON room.id = reservation.room_id LEFT JOIN "
            + "room_price ON room_price.id = room.room_price_id WHERE reservation.user_id= :userId";

    @Override
    public List<RequestsDto> getAllRequestsByUserId(Long userId) {
        Query query = entityManager.createNativeQuery(SELECT_ALL_USER_REQUESTS,
                RequestsDto.class);
        query.setParameter("userId", userId);
        List<RequestsDto> requestsDto = query.getResultList();
        return requestsDto;
    }
}
