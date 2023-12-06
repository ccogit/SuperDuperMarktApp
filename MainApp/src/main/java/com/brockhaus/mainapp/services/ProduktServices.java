package com.brockhaus.mainapp.services;

import com.brockhaus.mainapp.config.InitialDataGenerator;
import com.brockhaus.mainapp.model.Produkt;
import com.brockhaus.mainapp.model.enums.ProduktTyp;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@NoArgsConstructor
public class ProduktServices implements ServicesInterface {

    WeinServices weinServices;
    KaeseServices kaeseServices;
    private final String NEW_LINE = System.lineSeparator();

    @Autowired
    public ProduktServices(WeinServices weinServices, KaeseServices kaeseServices) {
        this.weinServices = weinServices;
        this.kaeseServices = kaeseServices;
    }

    @Override
    public List<Produkt> getBestand() {
        return Stream.of(weinServices.getBestand(), kaeseServices.getBestand())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBestand() {
        kaeseServices.deleteBestand();
        weinServices.deleteBestand();
    }

    public Map<ProduktTyp, List<Produkt>> getAktuellenBestandGroupedByTypDetails() {
        return getBestand().stream()
                .collect(Collectors.groupingBy(Produkt::getProduktTyp));
    }

    public void printAktuellenBestandByTypDetailsSingleDay() {
        getAktuellenBestandGroupedByTypDetails().forEach((key, value) -> {
            System.out.println(NEW_LINE + "---" + key + "---" + NEW_LINE);
            System.out.printf("%-5s %-17s %-17s %-17s %-17s %-17s %-17s %-17s %-17s %n",
                    "ID",
                    "BEZEICHNUNG",
                    "AKT. QUALITAET",
                    "VERFALLDATUM",
                    "TAGE BIS VERFALL",
                    "GRUNDPREIS",
                    "AKT. PREIS",
                    "AUSLIEGEND",
                    "ZU ENTFERNEN");
            value.forEach(System.out::println);
        });
    }

    public void printAktuellenBestandByTypDetailsSequence() {
        IntStream.range(1, InitialDataGenerator.anzahlTageSimulation).forEach(tag -> {
                    System.out.println(NEW_LINE +
                            "Datum: " + InitialDataGenerator.lieferDatum.plusDays(InitialDataGenerator.tageVergangenSeitLieferung) +
                            " - Tage vergangen seit Lieferung: " + InitialDataGenerator.tageVergangenSeitLieferung);
                    printAktuellenBestandByTypDetailsSingleDay();
                    Stream.iterate(1, i -> i < 145, i -> ++i).forEach(i -> System.out.print("_"));
                    System.out.println(NEW_LINE);
                    InitialDataGenerator.tageVergangenSeitLieferung++;
                }
        );
    }


}
