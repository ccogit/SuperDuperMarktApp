package com.brockhaus.mainapp.creators;

import com.brockhaus.mainapp.model.Produkt;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public abstract class Creator {

    @Autowired
    Faker faker;

    final Random random = new Random();

    public abstract Produkt erzeugeProdukt();

    public abstract Produkt konfiguriereProdukt(Produkt produkt);

    public abstract void speicherProdukt(Produkt produkt);

}
