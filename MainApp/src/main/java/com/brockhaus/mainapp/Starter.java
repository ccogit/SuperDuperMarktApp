package com.brockhaus.mainapp;

import com.brockhaus.mainapp.Utils.PrinterUtils;
import com.brockhaus.mainapp.Utils.ReportsGenerator;
import com.brockhaus.mainapp.Utils.ScannerUtils;
import com.brockhaus.mainapp.creators.Creator;
import com.brockhaus.mainapp.creators.KaeseCreator;
import com.brockhaus.mainapp.creators.WeinCreator;
import com.brockhaus.mainapp.model.Produkt;
import com.brockhaus.mainapp.model.enums.ProduktTyp;
import com.brockhaus.mainapp.services.ProduktServices;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.brockhaus.mainapp.model.enums.ProduktTyp.KAESE;

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
    public static final int anzahlEinheitenWein = 15;
    public static final int anzahlEinheitenKaese = 10;
    public static final int anzahlTageSimulation = 150;
    public static LocalDate lieferDatum = LocalDate.of(2023, 12, 5);
    public static int tageVergangenSeitLieferung = 0;
    private boolean runProgram = true;

    /* CSV */
    public static String fileName = "C:\\Data\\Lebenslauf_Transcript\\Stellenausschreibungen\\BrockhausVersicherungen\\SuperDuperMarktApp\\MainApp\\src\\main\\resources\\Produkte.csv";
    public static ProduktTyp typ = KAESE; // Typ der einzulesenden Produkte. Mögliche Werte: WEIN, KAESE

    @Override
    public void run(String... args) throws IOException, InterruptedException {
        produktServices.deleteBestand();
        befuelleRegale(weinCreator, anzahlEinheitenWein); // Regale mit Wein befuellen
        befuelleRegale(kaeseCreator, anzahlEinheitenKaese);  //  Regale mit Kaese befuellen
        System.out.println(System.lineSeparator() + "Via Faker erzeugte Einheiten: " + produktServices.getAnzahlEinheitenJeProduktTyp());
        leseProdukteViaCSV();
        getUserInput();
    }

    public void befuelleRegale(Creator creator, Integer anzahlEinheiten) {
        IntStream.range(1, anzahlEinheiten).forEach(i -> {
            creator.speicherProdukt(creator.konfiguriereProdukt(creator.erzeugeProdukt()));
        });
    }

    public void leseProdukteViaCSV() throws IOException {
        List<Produkt> produkteCsvImport = ScannerUtils.getProdukteFromCsv();
        produkteCsvImport.forEach(produkt -> {
            switch (produkt.getProduktTyp()) {
                case WEIN -> weinCreator.speicherProdukt(weinCreator.konfiguriereProdukt(weinCreator.erzeugeProdukt()));
                case KAESE ->
                        kaeseCreator.speicherProdukt(kaeseCreator.konfiguriereProdukt(kaeseCreator.erzeugeProdukt()));
            }
        });
        System.out.println("Via Csv erzeugte Einheiten: " + produkteCsvImport.stream().collect(Collectors.groupingBy(Produkt::getProduktTyp, Collectors.counting())));
    }

    public void getUserInput() throws InterruptedException {
        while (runProgram) {
            PrinterUtils.menuDialog();
            ScannerUtils.readLine();
            processInput();
        }
    }

    private void processInput() throws InterruptedException {
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

    private void stopProgram() throws InterruptedException {
        System.out.println("App wird geschlossen...");
        Thread.sleep(1000);
        IntStream.range(1, 200).forEach(value -> System.out.println());
        runProgram = false;
    }

    public static LocalDate getAktuellesDatum() {
        return lieferDatum.plusDays(tageVergangenSeitLieferung);
    }
}
