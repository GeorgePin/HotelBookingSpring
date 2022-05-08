package com.epam.hotelbookingspring.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
