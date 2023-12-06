package com.brockhaus.mainapp.services;

import com.brockhaus.mainapp.model.Produkt;

import java.util.List;

public interface ServicesInterface {
    public List<Produkt> getBestand();

    public void deleteBestand();
}
