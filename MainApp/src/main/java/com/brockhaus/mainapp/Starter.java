package com.brockhaus.mainapp;

import com.brockhaus.mainapp.Utils.PrinterUtils;
import com.brockhaus.mainapp.Utils.ReportsGenerator;
import com.brockhaus.mainapp.Utils.ScannerUtils;
import com.brockhaus.mainapp.creators.Creator;
import com.brockhaus.mainapp.creators.KaeseCreator;
import com.brockhaus.mainapp.creators.WeinCreator;
import com.brockhaus.mainapp.services.ProduktServices;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.IntStream;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class Starter implements CommandLineRunner {

    private final WeinCreator weinCreator;
    private final KaeseCreator kaeseCreator;
    private final ProduktServices produktServices;
    private final PrinterUtils printerUtils;
    private final ScannerUtils scannerUtils;
    private final ReportsGenerator reportsGenerator;

    public static LocalDate datumAktuell;

    /* CONFIG SZENARIO */
    public static final int anzahlEinheitenWein = 10;
    public static final int anzahlEinheitenKaese = 15;
    public static final int anzahlTageSimulation = 25;
    public static LocalDate lieferDatum = LocalDate.of(2023, 12, 5);
    public static int tageVergangenSeitLieferung = 0;
    private boolean runProgram = true;

    /* CSV */
    public static String fileName = "C:\\Data\\Lebenslauf_Transcript\\Stellenausschreibungen\\BrockhausVersicherungen\\SuperDuperMarktApp\\MainApp\\src\\main\\resources\\Produkte.csv";
    public static String produktTyp = "Kaese";

    @Override
    public void run(String... args) throws IOException {
        datumAktuell = lieferDatum;
        produktServices.deleteBestand();
        befuelleRegale(weinCreator, anzahlEinheitenWein); // Befuelle Regale mit 30 Wein-Einheiten
        befuelleRegale(kaeseCreator, anzahlEinheitenKaese);  // Befuelle Regale mit 25 Kaese-Einheiten
        leseProdukteViaCSV(produktTyp.equals("Wein") ? weinCreator : kaeseCreator);
        getUserInput();
    }

    public void befuelleRegale(Creator creator, Integer anzahlEinheiten) {
        IntStream.range(1, anzahlEinheiten).forEach(i -> creator.erzeugeProdukt());
    }

    public void leseProdukteViaCSV(Creator creator) throws IOException {
        ScannerUtils.getProdukteFromCsv().forEach(creator::erzeugeProdukt);
    }

    public void getUserInput() {
        while (runProgram) {
            PrinterUtils.menuDialog();
            ScannerUtils.readLine();
            processInput();
        }
    }

    private void processInput() {
        if ("e".equalsIgnoreCase(ScannerUtils.input) || "exit".equalsIgnoreCase(ScannerUtils.input)) {
            stopProgram();
        } else {
            ReportsGenerator.selectedReportTyp = switch (ScannerUtils.input) {
                case "l", "lang" -> "Langbericht";
                case "k", "kurz" -> "Kurzbericht";
                case "v", "verfallen" -> "VerfallenListe";
                default -> "Long Report";
            };
            reportsGenerator.printBericht();
        }
    }

    private void stopProgram() {
        runProgram = false;
    }

    public static void updateAktuellesDatum() {
        datumAktuell = lieferDatum.plusDays(tageVergangenSeitLieferung);
    }
}
