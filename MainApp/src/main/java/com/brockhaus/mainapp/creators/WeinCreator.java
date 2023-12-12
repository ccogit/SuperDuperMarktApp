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
        return new Wein();
    }

    @Override
    public void speicherProdukt(Produkt produkt) {
        weinServiceClient.saveWein((Wein) produkt);
    }

    @Override
    public Produkt konfiguriereProdukt(Produkt produkt) {
        produkt.setBezeichnung((faker.name().lastName()));
        produkt.setStartQualitaet(random.nextInt(0, 30));
        produkt.setVerfallDatum(LocalDate.of(9999, 12, 31));
        produkt.setGrundpreis(Math.round(100 * random.nextDouble(10, 85)) / 100.00);
        return produkt;
    }

}
