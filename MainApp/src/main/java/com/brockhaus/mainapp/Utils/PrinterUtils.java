package com.brockhaus.mainapp.Utils;

import com.brockhaus.mainapp.services.ProduktServices;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.brockhaus.mainapp.Starter.*;

@Service
public class PrinterUtils {

    final ProduktServices produktServices;
    private final String NEW_LINE = System.lineSeparator();

    public PrinterUtils(ProduktServices produktServices) {
        this.produktServices = produktServices;
    }

    public static void menuDialog() {
        System.out.println();
        System.out.println("Menu");
        System.out.println("====");
        System.out.println("l: long - individual products per day");
        System.out.println("s: short -  summarizing statistics per day");
        System.out.println("r: remove - list of products to be removed");
        System.out.println("e: exit - stops application");
        System.out.println("====");
        System.out.print("> ");
    }

    public void reportDayHeader() {
        System.out.println(NEW_LINE +
                "Datum: " + lieferDatum.plusDays(tageVergangenSeitLieferung) +
                " - Tage vergangen seit Lieferung: " + tageVergangenSeitLieferung);
    }

    public void printDaySeparatorLine() {
        Stream.iterate(1, i -> i < 145, i -> ++i).forEach(i -> System.out.print("_"));
        System.out.println(NEW_LINE);
    }

    public void printReportSeparatorLine() {
        Stream.iterate(1, i -> i < 145, i -> ++i).forEach(i -> System.out.print("="));
        System.out.println(NEW_LINE);
    }


    public void printLongReportOneDay() {
        produktServices.getAktuellenBestandGroupedByTypDetails().forEach((k, v) -> {
            System.out.println(NEW_LINE + "---" + k + "---" + NEW_LINE);
            System.out.printf("%-5s %-17s %-17s %-17s %-17s %-17s %-17s %-17s %-17s %n",
                    "ID",
                    "BEZEICHNUNG",
                    "AKT. QUALITAET",
                    "VERFALLDATUM",
                    "TAGE BIS VERFALL",
                    "GRUNDPREIS",
                    "AKT. PREIS",
                    "AUSLIEGEND",
                    "ZU ENTFERNEN");
            v.forEach(System.out::println);
        });
    }

    public void printSequenceOfDays(String selectedReportTyp) {
        printReportSeparatorLine();
        System.out.println(selectedReportTyp.toUpperCase());
        IntStream.range(1, anzahlTageSimulation).forEach(tag -> {
                    reportDayHeader();
                    if (selectedReportTyp.equals("Long Report")) printLongReportOneDay();
                    if (selectedReportTyp.equals("Short Report")) printShortReportSingleDay();
                    if (selectedReportTyp.equals("Remove List")) printRemoveListSingleDay();
                    printDaySeparatorLine();
                    tageVergangenSeitLieferung++;
                }
        );
        tageVergangenSeitLieferung = 0;
    }


    public void printShortReportSingleDay() {
        produktServices.getStatistic().forEach((k, v) -> {
            System.out.println(NEW_LINE + "---" + k + "---" + NEW_LINE);
            System.out.println(v);
        });
    }

    public void printRemoveListSingleDay() {
        produktServices.getRemoveList().forEach((k, v) -> {
            System.out.println(NEW_LINE + "---" + k + "---" + NEW_LINE);
            v.forEach(System.out::println);
        });
    }

}
