package com.epam.hotelbookingspring.model.metamodel;

import com.epam.hotelbookingspring.model.Room;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "com.epam.hotelbookingspring.model.Room")
@StaticMetamodel(Room.class)
public abstract class Room_ {

    public static volatile SingularAttribute<Room, Long> id;
    public static volatile SingularAttribute<Room, Long> roomPriceId;

    public static final String ID = "id";
    public static final String ROOM_PRICE_ID = "roomPriceId";
}