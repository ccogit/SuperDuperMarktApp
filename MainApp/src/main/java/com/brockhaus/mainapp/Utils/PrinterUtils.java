package com.brockhaus.mainapp.Utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrinterUtils {

    public static void menuDialog() {
        System.out.println();
        System.out.println("Menu");
        System.out.println("====");
        System.out.println("l: lang - Alle Produkte je Tag");
        System.out.println("k: kurz - Statistiken je Tag ");
        System.out.println("v: verfallen - Verfallene Produkte je Tag");
        System.out.println("e: exit - App beenden");
        System.out.println("====");
        System.out.print("> ");
    }
}
