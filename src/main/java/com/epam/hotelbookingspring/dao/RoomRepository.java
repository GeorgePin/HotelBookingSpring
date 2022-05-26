package com.epam.hotelbookingspring.dao;

import com.epam.hotelbookingspring.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Modifying
    @Query(value = "UPDATE Room rm SET rm.isBlocked=true where rm.id= :roomId")
    void blockRoom(Long roomId);

    @Modifying
    @Query(value = "UPDATE Room rm SET rm.isBlocked=false where rm.id= :roomId")
    void unblockRoom(Long roomId);

    @Modifying
    @Query(value = "UPDATE Room rm SET rm.isDeleted=true where rm.id= :roomId")
    void deleteRoom(Long roomId);

    Room findRoomByNumber(Integer number);
}
