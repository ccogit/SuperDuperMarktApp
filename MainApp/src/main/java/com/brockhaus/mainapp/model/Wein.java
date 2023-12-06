package com.brockhaus.mainapp.model;

import com.brockhaus.mainapp.Starter;
import com.brockhaus.mainapp.model.enums.ProduktTyp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Wein extends Produkt implements Serializable {
    @Override
    public ProduktTyp getProduktTyp() {
        return ProduktTyp.WEIN;
    }

    @Override
    public Double getPreisAktuell() {
        return Math.round(100 * (getGrundpreis() + 0.1 * getStartQualitaet())) / 100.00;
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

