package com.brockhaus.mainapp.model;

import com.brockhaus.mainapp.Starter;
import com.brockhaus.mainapp.model.enums.ProduktTyp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
//@ToString(callSuper = true)
public class Kaese extends Produkt {

    @Override
    public ProduktTyp getProduktTyp() {
        return ProduktTyp.KAESE;
    }

    @Override
    public Double getPreisAktuell() {
        return Math.round(100 * (getGrundpreis() + 0.1 * getQualitaetAktuell())) / 100.00;
    }

    @Override
    public int getQualitaetAktuell() {
        return getStartQualitaet() - Starter.tageVergangenSeitLieferung;
    }

    @Override
    public int getPunktabweichungVonSollNiveau() {
        return getQualitaetAktuell() - 30;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
