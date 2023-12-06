package com.brockhaus.weinservice.services;

import com.brockhaus.weinservice.model.Wein;
import com.brockhaus.weinservice.repositories.WeinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WeinService {

    private final WeinRepository weinRepository;

    public Wein getWeinById(Long id) {
        return weinRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Wein> getAllWein() {
        return weinRepository.findAll();
    }

    public Wein saveWein(Wein wein) {
        return weinRepository.save(wein);
    }

    public void deleteWeinById(Long id) {
        weinRepository.deleteById(id);
    }

    public Wein updateWein(Wein wein, Long id) {
        return weinRepository.findById(id).map(k -> {
            k.setBezeichnung(wein.getBezeichnung());
            k.setGrundpreis(wein.getGrundpreis());
            k.setLieferDatum(wein.getLieferDatum());
            k.setStartQualitaet(wein.getStartQualitaet());
            return weinRepository.save(k);
        }).orElseGet(() -> {
            wein.setId(id);
            return weinRepository.save(wein);
        });
    }

    public void deleteAllWein() {
        weinRepository.deleteAll(weinRepository.findAll());
    }
}
