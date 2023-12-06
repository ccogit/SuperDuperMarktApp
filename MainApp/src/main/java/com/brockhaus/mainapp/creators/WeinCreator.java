package com.brockhaus.mainapp.creators;

import com.brockhaus.mainapp.model.Wein;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class WeinCreator extends Creator {

    @Override
    public void erzeugeProdukt() {
        weinServiceClient.saveWein(Wein.builder()
                .bezeichnung(faker.name().lastName())
                .startQualitaet(random.nextInt(0, 30))
                .verfallDatum(LocalDate.of(9999,12,31))
                .grundpreis(Math.round(100 * random.nextDouble(10, 85)) / 100.00)
                .build());
    }

}
