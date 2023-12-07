package com.brockhaus.mainapp.Utils;

import com.brockhaus.mainapp.Starter;
import com.brockhaus.mainapp.model.Kaese;
import com.brockhaus.mainapp.model.Produkt;
import com.brockhaus.mainapp.model.Wein;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
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
        Reader reader = new BufferedReader(new FileReader(fileName));
        CsvToBean<Produkt> csvReader = new CsvToBeanBuilder(reader)
                .withType(Starter.produktTyp.equals("Wein") ? Wein.class : Kaese.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
        return csvReader.parse();
    }
}



