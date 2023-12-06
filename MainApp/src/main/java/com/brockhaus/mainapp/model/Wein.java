package com.brockhaus.mainapp.model;

import com.brockhaus.mainapp.model.enums.ProduktTyp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
public class Wein extends Produkt implements Serializable {
    @Override
    public ProduktTyp getProduktTyp() {
        return ProduktTyp.WEIN;
    }

    @Override
    public Double getPreisAktuell() {
        return null;
    }

    @Override
    public Integer getQualitaetAktuell() {
        return null;
//        return getStartQualitaet() + getTageSeitLieferung() / 10;
    }

    @Override
    public Integer getQualitaetspunkteUeberSollNiveau() {
        return null;
    }

    @Override
    public Boolean qualitaetsniveauUnterschritten() {
        return null;
    }

    @Override
    public Integer getTageBisVerfall() {
        return null;
    }

    @Override
    public Boolean verfallDatumErreicht() {
        return null;
    }


}
