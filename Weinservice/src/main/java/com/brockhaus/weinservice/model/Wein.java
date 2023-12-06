package com.brockhaus.weinservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wein {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bezeichnung;
    private Integer startQualitaet;
    @Builder.Default
    private LocalDate lieferDatum = LocalDate.now();
    private Double grundpreis;
    @Builder.Default
    private Boolean ausliegend = true;

}
