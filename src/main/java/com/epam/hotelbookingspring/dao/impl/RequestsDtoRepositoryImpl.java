package com.epam.hotelbookingspring.dao.impl;

import com.epam.hotelbookingspring.dao.RequestsDtoRepository;
import com.epam.hotelbookingspring.dao.dto.RequestsDto;
import com.epam.hotelbookingspring.model.Request;
import com.epam.hotelbookingspring.model.Room;
import com.epam.hotelbookingspring.model.RoomPrice;
import com.epam.hotelbookingspring.model.metamodel.Request_;
import com.epam.hotelbookingspring.model.metamodel.Room_;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RequestsDto> criteriaQuery = criteriaBuilder.createQuery(RequestsDto.class);
        Root<Request> root = criteriaQuery.from(Request.class);
        Join<Request, Room> room = root.join(Request_.ROOM_ID, JoinType.LEFT);
        Join<Room, RoomPrice> roomPrice = root.join(Room_.ROOM_PRICE_ID, JoinType.LEFT);
        criteriaQuery.select(criteriaBuilder.construct(
                RequestsDto.class,
                root.get(Room_.ID),room.get()));
//        Join<RoomPrice, Room> price = root.join("Request_.room", JoinType.LEFT);
        Query query = entityManager.createNativeQuery(SELECT_ALL_USER_REQUESTS,
                RequestsDto.class);
        query.setParameter("userId", userId);
        List<RequestsDto> requestsDto = query.getResultList();
        return requestsDto;
    }
}
