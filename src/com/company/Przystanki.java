package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Przystanki extends Komunikacja {
    private Map<Integer, String> przystanek = new TreeMap<>();  // lista asocjacyjna z nazwami przystanków

    protected Przystanki() {
        if (Global.debug) System.out.println("Konstruktor: Przystanki");
    }

    // pobiera nazwę przystanku
    protected String pobierzNazwe(Integer indeks) {
        return przystanek.get(indeks);
    }

    // wyświetla nazwę wszystkich przystanków
    protected void wyswietlPrzystanki() {
        for (Integer key : przystanek.keySet()) {
            System.out.println(" Przystanek " + key + ": " + nazwaPrzystanku(key));
        }
    }

    // wyświetla nazwę przystanku o indeksie "indeks" (poprzedzoną o komentarz)
    protected void wyswietlPrzystanki(Integer indeks) {
        System.out.println(" Przystanek " + indeks + ": " + przystanek.get(indeks));
    }

    // pobiera nazwę przystanku o indeksie "indeks"
    protected String nazwaPrzystanku(Integer indeks) {
        return przystanek.get(indeks);
    }

    // wyświetla indeksy wszystkich przystanków
    protected void wyswietlIndeksy(){
        for(Integer indeks:przystanek.keySet()){

        }
    }

    // wyświetla nazwę przystanku o indeksie "indeks"
    protected void pokazPrzystanek(Integer indeks){
        System.out.print(przystanek.get(indeks));
    }

    // pokazuje nazwę przystanku o indeksie "indeks", wyrównaną do określonej szerokości
    // na potrzeby prawidłowego wyświetlania rozkładu
    protected void pokazPrzystanekTrim(Integer indeks){
        System.out.format("%-24.24s ",przystanek.get(indeks));
    }

    // wczytuje dane z przystankami z pliku "nazwaPlikuCsv"
    protected void wczytaj(String nazwaPlikuCsv) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        int indeks = 0;

        try {
            br = new BufferedReader(new FileReader(nazwaPlikuCsv));

            boolean pominPierwszaLinie = true;
            int czytanaLinia = 0;
            while ((line = br.readLine()) != null) {
                if (pominPierwszaLinie) {
                    pominPierwszaLinie = false;
                    continue;
                }

                String[] slowo = line.split(cvsSplitBy);

                indeks = Integer.parseInt(slowo[0].trim());
                przystanek.put(indeks, slowo[1].trim());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR: blad odczytu pliku: " + nazwaPlikuCsv + " !!!");
            Global.errorDisplayInfo();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: inny blad odczytu pliku: " + nazwaPlikuCsv + " !!!");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // wyświetla rozkład przystanku o indeksie "indeks"
    protected void wyswietlRozkladPrzystanku(Integer indeks){
        System.out.println("Rozkład przystanku: "+nazwaPrzystanku(indeks));
    }

}
