package com.brockhaus.mainapp.model;

import com.brockhaus.mainapp.config.InitialDataGenerator;
import com.brockhaus.mainapp.model.enums.ProduktTyp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class Produkt implements Serializable {

    private Long id;
    private String bezeichnung;
    private Integer startQualitaet;
    @Builder.Default
    private LocalDate lieferDatum = InitialDataGenerator.lieferDatum;
    private LocalDate verfallDatum;
    private Double grundpreis;
    @Builder.Default
    private Boolean ausliegend = true;
    private ProduktTyp produktTyp;

    public abstract ProduktTyp getProduktTyp();

    public abstract Double getPreisAktuell();

    public abstract int getQualitaetAktuell();

    public abstract int getPunktabweichungVonSollNiveau();

    public Boolean qualitaetsniveauUnterschritten() {
        return getPunktabweichungVonSollNiveau() < 0;
    }

    public Integer getTageBisVerfall() {
        return Math.toIntExact(ChronoUnit.DAYS.between(getLieferDatum().plusDays(InitialDataGenerator.tageVergangenSeitLieferung), getVerfallDatum()));
    }

    public Boolean verfallDatumErreicht() {
        return getTageBisVerfall() <= 0;
    }

    public boolean vonAuslageEntfernen() {
        return ausliegend && (verfallDatumErreicht() || qualitaetsniveauUnterschritten());
    }

    public String toString() {
        return String.format("%-5s %-17s %-17s %-17s %-17s %-17s %-17s %-17s %-17s",
                id,
                bezeichnung,
                getQualitaetAktuell(),
                verfallDatum,
                getTageBisVerfall(),
                grundpreis,
                getPreisAktuell(),
                ausliegend,
                vonAuslageEntfernen());
    }

}
