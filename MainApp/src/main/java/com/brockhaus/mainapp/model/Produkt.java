package com.brockhaus.mainapp.model;

import com.brockhaus.mainapp.Starter;
import com.brockhaus.mainapp.model.enums.ProduktTyp;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
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

    @CsvBindByName(column = "ID")
    private Long id;

    @CsvBindByName(column = "BEZEICHNUNG")
    private String bezeichnung;

    @CsvBindByName(column = "START_QUALITAET")
    private Integer startQualitaet;

    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName(column = "LIEFER_DATUM")
    @Builder.Default
    private LocalDate lieferDatum = Starter.lieferDatum;

    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName(column = "VERFALL_DATUM")
    private LocalDate verfallDatum;

    @CsvBindByName(column = "GRUNDPREIS")
    private Double grundpreis;

    @CsvBindByName(column = "AUSLIEGEND")
    @Builder.Default
    private Boolean ausliegend = true;

    @CsvBindByName(column = "PRODUKT_TYP")
    private ProduktTyp produktTyp;

    public abstract ProduktTyp getProduktTyp();

    public abstract Double getPreisAktuell();

    public abstract int getQualitaetAktuell();

    public abstract int getPunktabweichungVonSollNiveau();

    public Boolean qualitaetsniveauUnterschritten() {
        return getPunktabweichungVonSollNiveau() < 0;
    }

    public Integer getTageBisVerfall() {
        return Math.toIntExact(ChronoUnit.DAYS.between(getLieferDatum().plusDays(Starter.tageVergangenSeitLieferung), getVerfallDatum()));
    }

    public Boolean verfallDatumErreicht() {
        return getTageBisVerfall() <= 0;
    }

    public boolean vonAuslageEntfernen() {
        return ausliegend && (verfallDatumErreicht() || qualitaetsniveauUnterschritten());
    }

    public String toString() {
        return String.format("%-5s %-17s %-17s %-17s %-17s %-17s %-17s %-17s %-17s %-17s",
                id,
                bezeichnung,
                getQualitaetAktuell(),
                getPunktabweichungVonSollNiveau(),
                verfallDatum,
                getTageBisVerfall(),
                grundpreis,
                getPreisAktuell(),
                ausliegend,
                vonAuslageEntfernen());
    }

}
