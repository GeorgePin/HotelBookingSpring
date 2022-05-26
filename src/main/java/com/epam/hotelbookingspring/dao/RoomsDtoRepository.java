package com.epam.hotelbookingspring.dao;

import com.epam.hotelbookingspring.dao.dto.RequestsDto;
import com.epam.hotelbookingspring.dao.dto.RoomsDto;
import com.epam.hotelbookingspring.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsDtoRepository extends JpaRepository<RoomsDto, Long> {

    @Query(value = "SELECT new com.epam.hotelbookingspring.dao.dto.RoomsDto(" +
            "rm.id, rm.capacity, rm.type, rm.number, rm.isBlocked,  rp.price,rp.validFrom) " +
            "FROM Room rm INNER JOIN RoomPrice rp ON rp.id = rm.roomPriceId WHERE rm.isDeleted = false ")
    Page<RoomsDto> getRoomsWithPrices(Pageable pageable);

    @Query(value = "SELECT new com.epam.hotelbookingspring.dao.dto.RoomsDto(" +
            "rm.id, rm.capacity, rm.type, rm.number, rm.isBlocked,  rp.price,rp.validFrom) " +
            "FROM Room rm INNER JOIN RoomPrice rp ON rp.id = rm.roomPriceId WHERE rm.isDeleted = false and rm.isBlocked=false")
    Page<RoomsDto> getRoomsWithPricesAndNonBlocked(Pageable pageable);
}
