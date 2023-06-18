package com.rst.metrics.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MomentPrice {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private LocalDateTime date;
    private Double price;

    public MomentPrice(LocalDateTime date, Double price) {
        this.date = date;
        this.price = price;
    }
}
