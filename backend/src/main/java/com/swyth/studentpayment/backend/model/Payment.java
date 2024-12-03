package com.swyth.studentpayment.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private LocalDate date;

    private double amount;

    private PaymentType type;

    private PaymentStatus status;

    private String file;

    @ManyToOne
    private Student student;
}
