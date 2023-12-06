package com.brockhaus.mainapp.config;

import com.brockhaus.mainapp.creators.Creator;
import com.brockhaus.mainapp.creators.KaeseCreator;
import com.brockhaus.mainapp.creators.WeinCreator;
import com.brockhaus.mainapp.services.ProduktServices;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.IntStream;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class InitialDataGenerator implements CommandLineRunner {

    private final WeinCreator weinCreator;
    private final KaeseCreator kaeseCreator;
    private final ProduktServices produktServices;

    public static final int anzahlTageSimulation = 25;
    public static LocalDate lieferDatum = LocalDate.of(2023, 12, 5);
    public static int tageVergangenSeitLieferung = 0;

    @Override
    public void run(String... args) {
        produktServices.deleteBestand();
        befuelleRegale(weinCreator, 30); // Befuelle Regale mit 30 Wein-Einheiten
        befuelleRegale(kaeseCreator, 25);  // Befuelle Regale mit 25 Kaese-Einheiten
        produktServices.printAktuellenBestandByTypDetailsSequence();
    }

    public void befuelleRegale(Creator creator, Integer anzahlEinheiten) {
        IntStream.range(1, anzahlEinheiten).forEach(i -> creator.erzeugeProdukt());
    }
}
