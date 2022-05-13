package com.epam.hotelbookingspring.model;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reservation")
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "room_capacity")
    private Integer roomCapacity;
//    @Enumerated(value = EnumType.STRING)
    @Column(name = "room_class")
    private String roomClass;
    @Column(name = "is_approved")
    private boolean isApproved;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "room_id")
    private Long roomId;
}
