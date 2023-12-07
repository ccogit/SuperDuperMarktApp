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
    public void erzeugeProdukt() {
        kaeseServiceClient.saveKaese(Kaese.builder()
                .bezeichnung(faker.name().lastName())
                .startQualitaet(random.nextInt(30, 75))
                .verfallDatum(LocalDate.now().plusDays(random.nextInt(50, 100)))
                .grundpreis(Math.round(100 * random.nextDouble(2, 25)) / 100.00)
                .build());
    }

    @Override
    public void erzeugeProdukt(Produkt produkt) {
        kaeseServiceClient.saveKaese((Kaese) produkt);
    }
}
