package com.indatacore.backend.fuel.domain;

import com.indatacore.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class Fuel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String type;
        @Column(nullable = false)
        private double price;

        @Column(nullable = false)
        private double totale;

        @Column(nullable = false)
        private double litre;
        @Column(nullable = false)
        private Date date;

        @ManyToOne(fetch = FetchType.EAGER)
        private User user;
}
