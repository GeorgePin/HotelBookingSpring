package com.epam.hotelbookingspring.model.metamodel;

import com.epam.hotelbookingspring.model.Request;
import com.epam.hotelbookingspring.model.Room;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "com.epam.hotelbookingspring.model.Request")
@StaticMetamodel(Request.class)
public abstract class Request_ {

    public static volatile SingularAttribute<Room, Long> id;
    public static volatile SingularAttribute<Room, Long> roomId;
    public static final String ID = "id";
    public static final String ROOM_ID = "roomId";

}