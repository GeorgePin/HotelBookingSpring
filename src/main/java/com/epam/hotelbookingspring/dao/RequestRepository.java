package com.epam.hotelbookingspring.dao;

import com.epam.hotelbookingspring.model.Request;
import com.epam.hotelbookingspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query(value = "SELECT new com.epam.hotelbookingspring.model.Request" +
            "(req.id, req.startDate, req.endDate, req.roomCapacity, req.roomClass, req.isApproved, req.userId, req.roomId) FROM Request req where req.isApproved=false")
    List<Request> getRequestsForAdmin();

    Request findRequestById(Long id);

    @Modifying
    @Query(value = "UPDATE Request req SET req.roomId= :roomId, req.isApproved=true where req.id = :reqId")
    void handleRequest(Long reqId, Long roomId);
}
