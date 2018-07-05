package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Linia extends Autobusy {
    protected Integer numer;
    protected ArrayList kurs_przystanki = new ArrayList();
    private ArrayList kursy = new ArrayList();

    protected Linia(Integer numer) {
        if (Global.debug) System.out.println("Konstruktor: Linia (nr:" + numer + ")");
        this.numer = numer;
        wczytajKursy();
    }

    // wczytuje z pliku kursy linii
    // (format nazwy pliku: "kurs-NUMER.csv")
    protected void wczytajKursy() {
        //System.out.println(">"+numer);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        int indeks = 0;
        String nazwaPlikuCsv = Global.data_dir + "kurs-" + numer + ".csv";

        try {
            br = new BufferedReader(new FileReader(nazwaPlikuCsv));

            if ((line = br.readLine()) != null) {
                String[] przystanki_txt = line.split(cvsSplitBy);

                for (String value : przystanki_txt) {
                    kurs_przystanki.add(Integer.parseInt(value.trim()));
                }
            }

            while ((line = br.readLine()) != null) {
                //System.out.println("Dodaje godziny kursowania:");
                Kurs kurs = new Kurs();
                kurs.dodaj(line);
                //kurs.wyswietl();
                kursy.add(kurs);
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

    // wyświetla numer linii z niniejszego obiektu
    protected void wyswietlNazwe() {
        System.out.println(" Linia nr " + numer);
    }

    // pobiera numer linii z niniejszego obiektu
    protected Integer pobierzNazwe(){
        return numer;
    }

    // wyświetla trasę linii (jako parametr: obiekt "Przystanki" z nazwami przystanków)
    protected void wyswietlTrase(Przystanki przystanki) {
        System.out.println("Kolejne przystanki na linii: " + numer);
        for (int i = 0; i < kurs_przystanki.size(); i++) {
            System.out.print("("
                    +kurs_przystanki.get(i)
                    + ") - "
                    + przystanki.nazwaPrzystanku((Integer) kurs_przystanki.get(i))
                    + "\n"
            );
        }
    }

    // wyświetla rozkład linii (jako parametr: obiekt "Przystanki" z nazwami przystanków)
    protected void wyswietlRozklad(Przystanki przystanki){
        System.out.println("Rozkład jazdy dla linii nr "+numer);
        for(Integer i=0;i<kurs_przystanki.size();i++){
            przystanki.pokazPrzystanekTrim((Integer) kurs_przystanki.get(i));
            //for(Kurs kurs:kursy){
            for(Integer j=0;j<kursy.size();j++){
                Kurs kurs = (Kurs) kursy.get(j);
                System.out.print(kurs.pobierz_godzine(i)+"  ");
            }
            System.out.println("");
        }
        //System.out.println("");

    }

    // wyświetla rozkład przystanku (obiekt "Przystanki")
    protected void wyswietlRozkladPrzystanku(Przystanki przystanki,Integer numer){
        //System.out.print(numer+" ");
        Integer indeks_w_rozkladzie = kurs_przystanki.indexOf(numer);
        //System.out.print(indeks_w_rozkladzie+" ");
        for(Integer i=0;i<kursy.size();i++){
            Kurs kurs=(Kurs) kursy.get(i);
            System.out.print(kurs.pobierz_godzine(indeks_w_rozkladzie)+"  ");
        }
    }
}
