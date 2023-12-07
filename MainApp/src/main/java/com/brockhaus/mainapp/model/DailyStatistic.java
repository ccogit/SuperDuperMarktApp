package com.brockhaus.mainapp.model;

import java.util.OptionalDouble;

public record DailyStatistic(
        long numberProductsTotal,
        long numberProductsToBeRemoved,
        OptionalDouble averageDaysUntilVerfall,
        OptionalDouble averageQuality,
        double totalVerkaufswert) {

    @Override
    public String toString() {
        return String.format("%-22s %-24s %-30s %-14s %-30s",
                (numberProductsTotal),
                (numberProductsToBeRemoved),
                averageDaysUntilVerfall.orElse(-1),
                averageQuality.orElse(-1),
                totalVerkaufswert);
    }

}
