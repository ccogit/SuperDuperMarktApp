package com.brockhaus.mainapp.creators;

import com.brockhaus.mainapp.clients.KaeseServiceClient;
import com.brockhaus.mainapp.clients.WeinServiceClient;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@RequiredArgsConstructor
public abstract class Creator {

    @Autowired
    KaeseServiceClient kaeseServiceClient;
    @Autowired
    WeinServiceClient weinServiceClient;
    @Autowired
    Faker faker;

    final Random random = new Random();

    public abstract void erzeugeProdukt();
}
