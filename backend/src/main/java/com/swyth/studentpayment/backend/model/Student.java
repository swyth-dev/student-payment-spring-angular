package com.swyth.studentpayment.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String code;

    private String firstName;

    private String lastName;

    private String programId;

    private String profilePicture;
}
