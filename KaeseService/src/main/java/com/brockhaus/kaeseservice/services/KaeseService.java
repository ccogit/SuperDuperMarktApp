package com.brockhaus.kaeseservice.services;

import com.brockhaus.kaeseservice.model.Kaese;
import com.brockhaus.kaeseservice.repositories.KaeseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class KaeseService {

    private final KaeseRepository kaeseRepository;

    public Kaese getKaeseById(Long id) {
        return kaeseRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Kaese> getAllKaese() {
        return kaeseRepository.findAll();
    }

    public Kaese saveKaese(Kaese kaese) {
        return kaeseRepository.save(kaese);
    }

    public void deleteKaeseById(Long id) {
        kaeseRepository.deleteById(id);
    }

    public Kaese updateKaese(Kaese kaese, Long id) {
        return kaeseRepository.findById(id).map(k -> {
            k.setBezeichnung(kaese.getBezeichnung());
            k.setGrundpreis(kaese.getGrundpreis());
            k.setLieferDatum(kaese.getLieferDatum());
            k.setVerfallDatum(kaese.getVerfallDatum());
            k.setStartQualitaet(kaese.getStartQualitaet());
            return kaeseRepository.save(k);
        }).orElseGet(() -> {
            kaese.setId(id);
            return kaeseRepository.save(kaese);
        });
    }

    public void deleteAllKaese() {
        kaeseRepository.deleteAll(kaeseRepository.findAll());
    }
}
