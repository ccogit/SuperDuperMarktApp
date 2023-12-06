package com.brockhaus.mainapp.services;

import com.brockhaus.mainapp.clients.WeinServiceClient;
import com.brockhaus.mainapp.model.Produkt;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class WeinServices implements ServicesInterface {

    WeinServiceClient weinServiceClient;
    private final String NEW_LINE = System.lineSeparator();

    @Autowired
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
