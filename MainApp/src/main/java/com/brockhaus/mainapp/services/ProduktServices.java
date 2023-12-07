package com.brockhaus.mainapp.services;

import com.brockhaus.mainapp.clients.KaeseServiceClient;
import com.brockhaus.mainapp.clients.WeinServiceClient;
import com.brockhaus.mainapp.model.DailyStatistic;
import com.brockhaus.mainapp.model.Produkt;
import com.brockhaus.mainapp.model.enums.ProduktTyp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class ProduktServices {

    private final KaeseServiceClient kaeseServiceClient;
    private final WeinServiceClient weinServiceClient;

    public List<Produkt> getBestand() {
        return Stream.of(weinServiceClient.getWeine(), kaeseServiceClient.getKaese())
                .flatMap(Collection::stream)
                .collect(toList());
    }

    public void deleteBestand() {
        weinServiceClient.deleteWeinAll();
        kaeseServiceClient.deleteKaeseAll();
    }

    public Map<ProduktTyp, Map<Boolean, List<Produkt>>> getAktuellenBestandGroupedByTypDetails() {
        return getBestand().stream()
                .collect(groupingBy(Produkt::getProduktTyp,
                        groupingBy(Produkt::vonAuslageEntfernen)));
    }

    public Map<ProduktTyp, List<Produkt>> getRemoveList() {
        return getBestand().stream()
                .filter(Produkt::vonAuslageEntfernen)
                .collect(groupingBy(Produkt::getProduktTyp));
    }

    public Map<ProduktTyp, DailyStatistic> getStatistic() {
        return getBestand().stream()
                .collect(groupingBy(Produkt::getProduktTyp, collectingAndThen(toList(), list ->
                        new DailyStatistic(
                                list.stream().map(Produkt::getId).count(),
                                list.stream().filter(Produkt::vonAuslageEntfernen).toList().size(),
                                OptionalDouble.of(Math.round(100 * list.stream().mapToInt(Produkt::getTageBisVerfall).average().orElse(-1)) / 100.00),
                                OptionalDouble.of(Math.round(100 * list.stream().mapToDouble(Produkt::getQualitaetAktuell).average().orElse(-1)) / 100.00),
                                Math.round(100 * list.stream().mapToDouble(Produkt::getPreisAktuell).sum()) / 100.00
                        ))));
    }

}
