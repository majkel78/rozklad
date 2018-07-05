package com.company;

public class Global {
    public static boolean debug = false;            // jeśli "true" wtedy wyświetlane są informacje diagnostyczne zawarte w programie
    public static String data_dir = "data/";        // nazwa katalogu, gdzie znajdują się pliki z danymi

    // funkcja wypisująca informację jeśli nastąpił błąd otwarcia któregoś z plików z danymi
    public static void errorDisplayInfo() {
        System.out.println("====="
                + "\n Program wymaga aby w katalogu \""+data_dir+"\" znajdowały się pliki:"
                + "\n - przystanki.csv"
                + "\n - linie.csv"
                + "\n - kurs-[numer_linii].csv"
                + "\n====="
                + "\n");
    }
}
