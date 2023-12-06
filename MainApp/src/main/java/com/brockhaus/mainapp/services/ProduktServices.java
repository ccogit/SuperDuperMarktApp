package com.brockhaus.mainapp.services;

import com.brockhaus.mainapp.model.Produkt;
import com.brockhaus.mainapp.model.enums.ProduktTyp;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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

    public Map<ProduktTyp, List<Produkt>> getAktuellenBestandGroupedByProdukttyp() {
        return getBestand().stream()
                .collect(Collectors.groupingBy(Produkt::getProduktTyp));
    }

    public void printMapOfProduktTypAndProdukten(Map<ProduktTyp, List<Produkt>> map) {
        map.forEach((key, value) -> {
            System.out.println(NEW_LINE + NEW_LINE + key + NEW_LINE);
            value.forEach(System.out::println);
        });
    }
}
