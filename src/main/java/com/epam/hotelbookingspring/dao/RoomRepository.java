package com.epam.hotelbookingspring.dao;

import com.epam.hotelbookingspring.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>, PagingAndSortingRepository<Room, Long> {
}
