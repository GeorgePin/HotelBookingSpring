package com.epam.hotelbookingspring.dao;

import com.epam.hotelbookingspring.dao.dto.RequestsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsDtoRepository extends JpaRepository<RequestsDto, Long> {
    @Query(value = "SELECT new com.epam.hotelbookingspring.dao.dto.RequestsDto(" +
            "req.id, req.startDate, req.endDate, req.roomCapacity, req.roomClass, req.isApproved, rp.price) " +
            "FROM Request req LEFT JOIN Room room ON room.id = req.roomId LEFT JOIN " +
            "RoomPrice rp ON rp.id = room.roomPriceId WHERE req.userId = :userId")
    Page<RequestsDto> getAllRequestsByUserId(Long userId, Pageable pageable);
}
