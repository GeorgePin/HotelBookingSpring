package com.epam.hotelbookingspring.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RequestsDto {
    @Id
    private Long id;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "room_capacity")
    private Integer roomCapacity;
    @Column(name = "room_class")
    private String roomClass;
    @Column(name = "is_approved")
    private boolean isApproved;
    @Column(name = "price")
    private BigDecimal price;


}
