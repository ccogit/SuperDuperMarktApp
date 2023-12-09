package com.brockhaus.mainapp.creators;

import com.brockhaus.mainapp.clients.WeinServiceClient;
import com.brockhaus.mainapp.model.Produkt;
import com.brockhaus.mainapp.model.Wein;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class WeinCreator extends Creator {

    @Autowired
    WeinServiceClient weinServiceClient;

    @Override
    public Produkt erzeugeProdukt() {
        return Wein.builder()
                .bezeichnung(faker.name().lastName())
                .startQualitaet(random.nextInt(0, 30))
                .verfallDatum(LocalDate.of(9999, 12, 31))
                .grundpreis(Math.round(100 * random.nextDouble(10, 85)) / 100.00)
                .build();
    }

    @Override
    public void speicherProdukt(Produkt produkt) {
        weinServiceClient.saveWein((Wein) produkt);
    }

    @Override
    public Produkt konfiguriereProdukt(Produkt produkt) {
        produkt.setBezeichnung(erzeugeZufallsBezeichnung());
        produkt.setStartQualitaet(erzeugeZufallsStartQualitaet());
        produkt.setVerfallDatum(erzeugeZufallsVerfallDatum());
        produkt.setGrundpreis(erzeugeZufallsGrundpreis());
        return produkt;
    }

    @Override
    public int erzeugeZufallsStartQualitaet() {
        return random.nextInt(0, 30);
    }

    @Override
    public LocalDate erzeugeZufallsVerfallDatum() {
        return LocalDate.of(9999, 12, 31);
    }

    @Override
    public double erzeugeZufallsGrundpreis() {
        return Math.round(100 * random.nextDouble(10, 85)) / 100.00;
    }


}
