package com.brockhaus.mainapp.creators;

import com.brockhaus.mainapp.clients.KaeseServiceClient;
import com.brockhaus.mainapp.model.Kaese;
import com.brockhaus.mainapp.model.Produkt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class KaeseCreator extends Creator {

    @Autowired
    KaeseServiceClient kaeseServiceClient;

    @Override
    public Produkt erzeugeProdukt() {
        return Kaese.builder().build();
    }

    @Override
    public Produkt konfiguriereProdukt(Produkt produkt) {
        produkt.setBezeichnung(faker.name().lastName());
        produkt.setStartQualitaet(random.nextInt(80, 130));
        produkt.setVerfallDatum(LocalDate.now().plusDays(random.nextInt(50, 100)));
        produkt.setGrundpreis(Math.round(100 * random.nextDouble(2, 25)) / 100.00);
        return produkt;
    }

    @Override
    public void speicherProdukt(Produkt produkt) {
        kaeseServiceClient.saveKaese((Kaese) produkt);
    }
}
