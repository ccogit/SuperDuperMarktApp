package com.brockhaus.mainapp.Utils;

import com.brockhaus.mainapp.Starter;
import com.brockhaus.mainapp.model.Kaese;
import com.brockhaus.mainapp.model.Produkt;
import com.brockhaus.mainapp.model.Wein;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

@Service
public class ScannerUtils {


    private final static Scanner scanner = new Scanner(System.in);
    private static String input = "";

    public static void readLine() {
        input = scanner.nextLine().trim();
    }

    public static boolean isLongReport() {
        return "l".equalsIgnoreCase(input) || "long".equalsIgnoreCase(input);
    }

    public static boolean isShortReport() {
        return "s".equalsIgnoreCase(input) || "short".equalsIgnoreCase(input);
    }

    public static boolean isRemoveList() {
        return "r".equalsIgnoreCase(input) || "remove".equalsIgnoreCase(input);
    }

    public static boolean isExit() {
        return "e".equalsIgnoreCase(input) || "exit".equalsIgnoreCase(input);
    }

    public static List<Produkt> getProdukteFromCsv() throws FileNotFoundException {
        return new CsvToBeanBuilder<Produkt>(new FileReader(Starter.fileName))
                .withType(Starter.produktTyp.equals("Wein") ? Wein.class : Kaese.class)
                .build()
                .parse();

    }


}
