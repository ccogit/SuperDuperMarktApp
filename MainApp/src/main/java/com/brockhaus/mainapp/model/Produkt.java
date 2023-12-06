package com.brockhaus.mainapp.model;

import com.brockhaus.mainapp.model.enums.ProduktTyp;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString
public abstract class Produkt implements Serializable {

    private Long id;
    private String bezeichnung;
    private Integer startQualitaet;
    @Builder.Default
    private LocalDate lieferDatum = LocalDate.now();
    private Double grundpreis;
    @Builder.Default
    private Boolean ausliegend = true;
    private ProduktTyp produktTyp;

    public abstract ProduktTyp getProduktTyp();

    public abstract Double getPreisAktuell();

    public abstract Integer getQualitaetAktuell();

    public abstract Integer getQualitaetspunkteUeberSollNiveau();

    public abstract Boolean qualitaetsniveauUnterschritten();

    public abstract Integer getTageBisVerfall();

    public abstract Boolean verfallDatumErreicht();

    public boolean vonAuslageEntfernen() {
        return ausliegend && (verfallDatumErreicht() || qualitaetsniveauUnterschritten());
    }

//    public int getTageSeitLieferung() {
//        return Math.toIntExact(lieferDatum.until(LocalDate.now(), ChronoUnit.DAYS));
//    }

}
