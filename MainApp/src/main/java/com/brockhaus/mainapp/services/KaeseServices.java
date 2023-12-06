package com.brockhaus.mainapp.services;

import com.brockhaus.mainapp.clients.KaeseServiceClient;
import com.brockhaus.mainapp.model.Produkt;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class KaeseServices implements ServicesInterface {

    KaeseServiceClient kaeseServiceClient;
    private final String NEW_LINE = System.lineSeparator();

    @Autowired
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
