package com.brockhaus.mainapp.model;

import com.brockhaus.mainapp.Starter;
import com.brockhaus.mainapp.clients.KaeseServiceClient;
import com.brockhaus.mainapp.model.enums.ProduktTyp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Component
public class Kaese extends Produkt {

    @Autowired
    KaeseServiceClient kaeseServiceClient;

    @Override
    public ProduktTyp getProduktTyp() {
        return ProduktTyp.KAESE;
    }

    @Override
    public Double getPreisAktuell() {
        return vonAuslageEntfernen() ? 0.0 : Math.round(100 * (getGrundpreis() + 0.1 * getQualitaetAktuell())) / 100.00;
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
