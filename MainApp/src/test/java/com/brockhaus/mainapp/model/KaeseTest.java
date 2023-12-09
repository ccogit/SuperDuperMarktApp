package com.brockhaus.mainapp.model;

import com.brockhaus.mainapp.Starter;
import com.brockhaus.mainapp.clients.KaeseServiceClient;
import com.brockhaus.mainapp.creators.KaeseCreator;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class KaeseTest {

    @Autowired
    Starter starter;
    @Autowired
    KaeseCreator kaeseCreator;
    @Autowired
    KaeseServiceClient kaeseServiceClient;
    @Autowired
    Faker faker;

    final Random random = new Random();

    @Test
    void getQualitaetAktuell() {
        Kaese kaese = Kaese.builder().build();
        assertEquals(kaese, kaeseCreator.erzeugeProdukt());
    }
}