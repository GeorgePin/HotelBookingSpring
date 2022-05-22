package com.epam.hotelbookingspring.dao.impl;

import com.epam.hotelbookingspring.dao.RequestsDtoRepository;
import com.epam.hotelbookingspring.dao.dto.RequestsDto;
import com.epam.hotelbookingspring.model.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RequestsDtoRepositoryImpl {
    private static final int ITEMS_PER_PAGE = 5;
    @PersistenceContext
    private final EntityManager entityManager;
    private static final String SELECT_ALL_USER_REQUESTS = "SELECT new com.epam.hotelbookingspring.dao.dto.RequestsDto(" +
            "req.startDate, req.endDate, req.roomCapacity, req.roomClass, req.isApproved, rp.price) " +
            "FROM Request req LEFT JOIN Room room ON room.id = req.roomId LEFT JOIN " +
            "RoomPrice rp ON rp.id = room.roomPriceId WHERE req.userId = :userId";

//    @Override
    public Page<RequestsDto> getAllRequestsByUserId(Long userId, Pageable page) {
        TypedQuery<RequestsDto> query = entityManager.createQuery(SELECT_ALL_USER_REQUESTS,
                RequestsDto.class);
//        Pageable pageable = new PageRequest(1, ITEMS_PER_PAGE);
//        query.setFirstResult(startElement);
//        query.setMaxResults(ITEMS_PER_PAGE);
        query.setParameter("userId", userId);
//        Page<RequestsDto> requestsDto = query.getResultList();
        return null;
    }
}
