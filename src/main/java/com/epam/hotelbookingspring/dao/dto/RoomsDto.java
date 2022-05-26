package com.epam.hotelbookingspring.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoomsDto {
    @Id
    private Long id;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "type")
    private String type;
    @Column(name = "number")
    private Integer number;
    @Column(name = "is_blocked")
    private boolean isBlocked;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "valid_from")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validFrom;
}