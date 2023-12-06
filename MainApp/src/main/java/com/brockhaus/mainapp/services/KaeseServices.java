package com.brockhaus.mainapp.services;

import com.brockhaus.mainapp.clients.KaeseServiceClient;
import com.brockhaus.mainapp.model.Produkt;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KaeseServices implements ServicesInterface {

    private final KaeseServiceClient kaeseServiceClient;

    @Lazy
    public KaeseServices(KaeseServiceClient kaeseServiceClient) {
        this.kaeseServiceClient = kaeseServiceClient;
    }

    @Override
    public List<Produkt> getBestand() {
        return new ArrayList<>(kaeseServiceClient.getKaese());
    }

    @Override
    public void deleteBestand() {
        kaeseServiceClient.deleteKaeseAll();
    }


}
