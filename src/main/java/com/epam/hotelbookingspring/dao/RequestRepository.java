package com.epam.hotelbookingspring.dao;

import com.epam.hotelbookingspring.model.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
}
