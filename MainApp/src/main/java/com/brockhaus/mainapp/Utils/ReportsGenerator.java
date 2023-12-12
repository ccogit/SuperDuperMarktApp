package com.brockhaus.mainapp.Utils;

import com.brockhaus.mainapp.services.ProduktServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static com.brockhaus.mainapp.Starter.*;

@RequiredArgsConstructor
@Service
public class ReportsGenerator {

    private final ProduktServices produktServices;
    private final static String NEW_LINE = System.lineSeparator();

    public static String selectedReportTyp;

    public void printBericht() {
        printReportHeader();
        printSequenceOfDays();
        tageVergangenSeitLieferung = 0;
    }

    public int printSequenceOfDays() {
        printReportDayHeader();
        switch (selectedReportTyp) {
            case "Langbericht" -> {
                printLongReportSingleDay();
            }
            case "Kurzbericht" -> {
                printShortReportHeader();
                printShortReportSingleDay();
            }
            case "VerfallenListe" -> {
                printRemoveListSingleDay();
            }
        }
        printDaySeparatorLine();
        tageVergangenSeitLieferung++;
        return tageVergangenSeitLieferung <= anzahlTageSimulation ? printSequenceOfDays() : -1;
    }

    /* SINGLE REPORT LINES */
    public void printLongReportSingleDay() {
        produktServices.getAktuellenBestandGroupedByTypDetails().forEach((k, v) -> {
            System.out.println(NEW_LINE + "---" + k + "---");
            v.forEach((b, l) -> {
                String headline = b ? "Von Auslage zu entfernende Produkte" : "Gute Produkte";
                System.out.println(NEW_LINE + "-" + headline + "-" + NEW_LINE);
                System.out.printf("%-5s %-17s %-17s %-17s %-17s %-17s %-17s %-17s %-17s %-17s %n",
                        "ID",
                        "BEZEICHNUNG",
                        "AKT. QUALITAET",
                        "REST-QUALITAET",
                        "VERFALLDATUM",
                        "TAGE BIS VERFALL",
                        "GRUNDPREIS",
                        "AKT. PREIS",
                        "AUSLIEGEND",
                        "ZU ENTFERNEN");
                l.forEach(System.out::println);
            });
        });
    }

    public void printShortReportSingleDay() {
        produktServices.getStatistic().forEach((k, v) -> System.out.printf("%-10s %-200s %n", k, v));
    }

    public void printRemoveListSingleDay() {
        produktServices.getRemoveList().forEach((k, v) -> {
            System.out.println(NEW_LINE + "---" + k + "---" + NEW_LINE);
            v.forEach(System.out::println);
        });
    }

    /* REPORT HEADERS */
    public static void printReportHeader() {
        printReportSeparatorLine();
        System.out.println(selectedReportTyp.toUpperCase());
    }

    public static void printReportDayHeader() {
        System.out.println(NEW_LINE + "Datum: " + getAktuellesDatum() + " (" + tageVergangenSeitLieferung + ")");
    }

    public static void printShortReportHeader() {
        System.out.println(NEW_LINE +
                String.format("%-10s %-18s %-18s %-27s %-17s %-30s",
                        "Typ",
                        "# in Auslage",
                        "# zu entfernen",
                        "Avg. Tage bis Verfall",
                        "Avg. Qualitaet",
                        "Verkaufswert"));
    }

    /* SEPARATORS */
    public static void printDaySeparatorLine() {
        Stream.iterate(1, i -> i < 170, i -> ++i).forEach(i -> System.out.print("_"));
        System.out.println(NEW_LINE);
    }

    public static void printReportSeparatorLine() {
        Stream.iterate(1, i -> i < 145, i -> ++i).forEach(i -> System.out.print("="));
        System.out.println(NEW_LINE);
    }

}
