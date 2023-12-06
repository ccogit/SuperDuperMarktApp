package com.brockhaus.mainapp.services;

import com.brockhaus.mainapp.clients.WeinServiceClient;
import com.brockhaus.mainapp.model.Produkt;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeinServices implements ServicesInterface {

    private final WeinServiceClient weinServiceClient;

    @Lazy
    public WeinServices(WeinServiceClient weinServiceClient) {
        this.weinServiceClient = weinServiceClient;
    }

    @Override
    public List<Produkt> getBestand() {
        return new ArrayList<>(weinServiceClient.getWeine());
    }

    @Override
    public void deleteBestand() {
        weinServiceClient.deleteWeinAll();
    }


}
