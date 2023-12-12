package com.brockhaus.mainapp.Utils;

import com.brockhaus.mainapp.Starter;
import com.brockhaus.mainapp.model.Kaese;
import com.brockhaus.mainapp.model.Produkt;
import com.brockhaus.mainapp.model.Wein;
import com.brockhaus.mainapp.model.enums.ProduktTyp;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import static com.brockhaus.mainapp.Starter.fileName;
import static java.lang.System.in;

@Service
@RequiredArgsConstructor
public class ScannerUtils {

    private final static Scanner scanner = new Scanner(in);
    public static String input = "";

    public static void readLine() {
        input = scanner.nextLine().trim();
    }

    public static List<Produkt> getProdukteFromCsv() throws FileNotFoundException {
        CsvToBean<Produkt> csvReader = new CsvToBeanBuilder(new BufferedReader(new FileReader(fileName)))
                .withType(Starter.typ.equals(ProduktTyp.WEIN) ? Wein.class : Kaese.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
        return csvReader.parse();

    }
}



