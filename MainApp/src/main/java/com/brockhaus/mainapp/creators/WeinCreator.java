package com.brockhaus.mainapp.creators;

import com.brockhaus.mainapp.clients.WeinServiceClient;
import com.brockhaus.mainapp.model.Produkt;
import com.brockhaus.mainapp.model.Wein;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class WeinCreator extends Creator {

    @Autowired
    WeinServiceClient weinServiceClient;

    @Override
    public void erzeugeProdukt() {
        weinServiceClient.saveWein(Wein.builder()
                .bezeichnung(faker.name().lastName())
                .startQualitaet(random.nextInt(0, 30))
                .verfallDatum(LocalDate.of(9999, 12, 31))
                .grundpreis(Math.round(100 * random.nextDouble(10, 85)) / 100.00)
                .build());
    }

    @Override
    public void erzeugeProdukt(Produkt produkt) {
        weinServiceClient.saveWein((Wein) produkt);
    }


}
