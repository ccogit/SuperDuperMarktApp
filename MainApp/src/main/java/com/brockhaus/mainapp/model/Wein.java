package com.brockhaus.mainapp.model;

import com.brockhaus.mainapp.Starter;
import com.brockhaus.mainapp.clients.WeinServiceClient;
import com.brockhaus.mainapp.model.enums.ProduktTyp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Component
public class Wein extends Produkt implements Serializable {


    @Autowired
    WeinServiceClient weinServiceClient;

    @Override
    public ProduktTyp getProduktTyp() {
        return ProduktTyp.WEIN;
    }

    @Override
    public Double getPreisAktuell() {
    /*
        Qualitäts-Zuschlag bei Wein ändert sich nicht über die Zeit.
        Berechnung des konstanten Zuschlags ueber Startqualität.
    */
        return vonAuslageEntfernen() ? 0.0 :
                Math.round(100 * (getGrundpreis() + 0.1 * getStartQualitaet())) / 100.00;
    }

    @Override
    public int getQualitaetAktuell() {
        return Math.min(getStartQualitaet() + Starter.tageVergangenSeitLieferung / 10, 50);
    }

    @Override
    public int getPunktabweichungVonSollNiveau() {
        return getQualitaetAktuell();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

