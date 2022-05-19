package com.epam.hotelbookingspring.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "room_price")
@Data
public class RoomPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "valid_from")
    private Date validFrom;
    @Column(name = "price")
    private BigDecimal price;
}

