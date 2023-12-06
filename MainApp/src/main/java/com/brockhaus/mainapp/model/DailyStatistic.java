package com.brockhaus.mainapp.model;

import java.util.OptionalDouble;

public record DailyStatistic(
        long numberProductsTotal,
        long numberProductsToBeRemoved,
        OptionalDouble averageDaysUntilVerfall,
        OptionalDouble averageQuality) {

    @Override
    public String toString() {
        return printFormattedLine("Bestand (Anzahl Produkte) : ", String.valueOf(numberProductsTotal)) +
                printFormattedLine("Anzahl zu entfernende Produkte: ", String.valueOf(numberProductsToBeRemoved)) +
                printFormattedLine("⌀ Anzahl Tage bis Produkt-Verfall: ", String.valueOf(averageDaysUntilVerfall.orElse(-1))) +
                printFormattedLine("⌀ Qualitaet: ", String.valueOf(averageQuality.orElse(-1)));
    }

    public static String printFormattedLine(String left, String right) {
        return String.format("%-40s%-10s%n", left, right);
    }
}
