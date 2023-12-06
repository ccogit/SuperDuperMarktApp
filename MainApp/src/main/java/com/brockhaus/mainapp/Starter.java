package com.brockhaus.mainapp;

import com.brockhaus.mainapp.Utils.PrinterUtils;
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

import java.io.FileNotFoundException;
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

    public static final int anzahlTageSimulation = 25;
    public static LocalDate lieferDatum = LocalDate.of(2023, 12, 5);
    public static int tageVergangenSeitLieferung = 0;

    /* CSV */
    public static String fileName = "C:\\Data\\Lebenslauf_Transcript\\Stellenausschreibungen\\BrockhausVersicherungen\\SuperDuperMarktApp\\MainApp\\src\\main\\resources\\Produkte.csv";
    public static String produktTyp = "Kaese";

    private boolean runProgram = true;

    @Override
    public void run(String... args) throws FileNotFoundException {
        produktServices.deleteBestand();
        befuelleRegale(weinCreator, 30); // Befuelle Regale mit 30 Wein-Einheiten
        befuelleRegale(kaeseCreator, 25);  // Befuelle Regale mit 25 Kaese-Einheiten
        leseProdukteViaCSV(produktTyp.equals("Wein") ? weinCreator : kaeseCreator);
        getUserInput();
    }

    public void befuelleRegale(Creator creator, Integer anzahlEinheiten) {
        IntStream.range(1, anzahlEinheiten).forEach(i -> creator.erzeugeProdukt());
    }

    public void leseProdukteViaCSV(Creator creator) throws FileNotFoundException {
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
        if (ScannerUtils.isLongReport()) printerUtils.printSequenceOfDays("Long Report");
        if (ScannerUtils.isShortReport()) printerUtils.printSequenceOfDays("Short Report");
        if (ScannerUtils.isRemoveList()) printerUtils.printSequenceOfDays("Remove List");
        if (ScannerUtils.isExit()) stopProgram();
    }

    private void stopProgram() {
        runProgram = false;
    }

}
