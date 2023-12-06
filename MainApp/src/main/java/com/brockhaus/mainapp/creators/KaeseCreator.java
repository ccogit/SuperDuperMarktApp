package com.brockhaus.mainapp.creators;

import com.brockhaus.mainapp.model.Kaese;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class KaeseCreator extends Creator {

    @Override
    public void erzeugeProdukt() {
        kaeseServiceClient.saveKaese(Kaese.builder()
                .bezeichnung(faker.name().lastName())
                .startQualitaet(random.nextInt(30, 75))
                .verfallDatum(LocalDate.now().plusDays(random.nextInt(50, 100)))
                .grundpreis(Math.round(100 * random.nextDouble(2, 25)) / 100.00)
                .build());
    }
}
