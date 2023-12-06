package com.brockhaus.mainapp.services;

import com.brockhaus.mainapp.model.DailyStatistic;
import com.brockhaus.mainapp.model.Produkt;
import com.brockhaus.mainapp.model.enums.ProduktTyp;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Service
@NoArgsConstructor
public class ProduktServices implements ServicesInterface {

    WeinServices weinServices;
    KaeseServices kaeseServices;

    @Autowired
    public ProduktServices(WeinServices weinServices, KaeseServices kaeseServices) {
        this.weinServices = weinServices;
        this.kaeseServices = kaeseServices;
    }

    @Override
    public List<Produkt> getBestand() {
        return Stream.of(weinServices.getBestand(), kaeseServices.getBestand())
                .flatMap(Collection::stream)
                .collect(toList());
    }

    @Override
    public void deleteBestand() {
        kaeseServices.deleteBestand();
        weinServices.deleteBestand();
    }

    public Map<ProduktTyp, List<Produkt>> getAktuellenBestandGroupedByTypDetails() {
        return getBestand().stream()
                .collect(groupingBy(Produkt::getProduktTyp));
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
                                OptionalDouble.of(Math.round(100 * list.stream().mapToDouble(Produkt::getQualitaetAktuell).average().orElse(-1)) / 100.00)
                        ))));
    }

}
