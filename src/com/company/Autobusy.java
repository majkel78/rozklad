package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Autobusy extends Komunikacja {
    private Map<Integer, Linia> wykazLinii = new TreeMap<>();   // lista asocjacyjna aby można szybciej dotrzeć do danej linii
    private Przystanki przystanki = new Przystanki();           // obiekt Przystanki
    private String nazwa_pliku_przystanki = Global.data_dir+"przystanki.csv";   // nazwa pliku z przystankami
    private String nazwa_pliku_linie = Global.data_dir+"linie.csv";             // nazwa pliku z wykazem linii

    protected Autobusy() {
        if (Global.debug) System.out.println("Konstruktor: Autobusy");
    }

    // wczytuje przystanki z pliku
    protected void wczytajPrzystanki() {
        przystanki.wczytaj(nazwa_pliku_przystanki);
    }

    //
    protected void dodajPrzystanek(){
        Scanner kb = new Scanner(System.in);
        System.out.print("Istniejące indeksy: ");
        przystanki.wyswietlIndeksy();
        System.out.print("Wpisz indeks nowego przystanku: ");
        Integer nowy_numer = kb.nextInt();
    }

    // wczytuje linie z pliku
    protected void wczytajLinie() {
        BufferedReader br = null;
        String line = "";
        String nazwaPlikuCsv = nazwa_pliku_linie;
        int indeks = 0;

        try {
            br = new BufferedReader(new FileReader(nazwaPlikuCsv));

            while ((line = br.readLine()) != null) {
                Integer numer = Integer.parseInt(line.trim());
                if (numer > 0) {
                    Linia linia = new Linia(numer);
                    dodajLinie(numer, linia);
                }
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

    // dodaje obiekt "linia" o numerze "numer"
    protected void dodajLinie(Integer numer, Linia linia) {
        wykazLinii.put(numer, linia);
    }

    // dodaje linię użytkownika
    protected void dodajLinieUser(){
        Scanner kb = new Scanner(System.in);
        System.out.print("Wpisz numer nowej linii): ");
        Integer nowy_numer = kb.nextInt();
        if(nowy_numer>0){
            // sprawdzenie czy taka linia już istnieje
            if(wykazLinii.containsKey(nowy_numer)){
                System.out.println("Linia "+nowy_numer+" już istnieje!!!");
            } else{
                Linia linia = new Linia(nowy_numer);
                dodajLinie(nowy_numer,linia);
            }
        } else{
            System.out.println("Musisz podać numer większy od 0 !!!");
        }
    }

    // wyświetla wszystkie linie
    protected void wyswietlLinie() {
        for (Integer key : wykazLinii.keySet()) {
            wykazLinii.get(key).wyswietlNazwe();
        }
    }

    // wyświetla wszystkie przystanki
    protected void wyswietlPrzystanki() {
        System.out.println("Przystanki wraz ze swoimi indeksami:");
        przystanki.wyswietlPrzystanki();
    }

    // wyświetla przystanek o indeksie "indeks"
    protected void wyswietlPrzystanki(Integer indeks) {
        przystanki.wyswietlPrzystanki(indeks);
    }

    // wyświetla trasę linii o numerze "numer"
    protected void wyswietlTraseLinii(Integer numer) {
        if(wykazLinii.containsKey(numer)) {
            wykazLinii.get(numer).wyswietlTrase(przystanki);
        } else {
            System.out.println("Podana linia nie istnieje !!!");
        }
    }

    // wyświetla rozkład linii o numerze "numer"
    protected void wyswietlRozkladLinii(Integer numer) {
        if (wykazLinii.containsKey(numer)) {
            //System.out.println("Linia "+numer);
            wykazLinii.get(numer).wyswietlRozklad(przystanki);
        } else {
            System.out.println("Nie znaleziono linii o numerze " + numer + " !!!");
        }
    }

    // wyświetla rozkład przystanku o indeksie "indeks"
    protected void wyswietlRozkladPrzystanku(Integer indeks) {
        System.out.println("Przystanek: " + przystanki.nazwaPrzystanku(indeks));
        for (Linia linia : wykazLinii.values()) {
            if (linia.kurs_przystanki.contains(indeks)) {
                System.out.format("Linia %3.3s: ", linia.numer);
                linia.wyswietlRozkladPrzystanku(przystanki, indeks);
                System.out.println("");
            }
        }
    }

    // wyświetla menu z wykazem operacji dla użytkownika
    protected boolean wyswietlMenu() {
        boolean dalej = true;
        System.out.print("\n--- ROZKŁAD JAZDY - wybierz opcję ---"
                + "\n 1 - pokaż linie                    5 - pokaż rozkład przystanku"
                + "\n 2 - pokaż przystanki               Q - wyjście z programu"
                + "\n 3 - pokaż trasę linii              "
                + "\n 4 - pokaż rozkład danej linii"
                +"\n 6-dodaj_linie_user   7-dodaj przystanek"
                + "\n==> "
        );
        Scanner kb = new Scanner(System.in);
        //int wybor = kb.nextInt();
        String wybor = kb.nextLine().trim().toLowerCase();
        switch (wybor) {
            case "1":
                wyswietlLinie();
                break;
            case "2":
                wyswietlPrzystanki();
                break;
            case "3":
                System.out.print("Podaj numer linii: ");
                wyswietlTraseLinii((Integer) kb.nextInt());
                break;
            case "4":
                System.out.print("Podaj numer linii: ");
                wyswietlRozkladLinii((Integer) kb.nextInt());
                break;
            case "5":
                System.out.print("Podaj indeks przystanku (indeksy-opcja 2): ");
                wyswietlRozkladPrzystanku((Integer) kb.nextInt());
                break;
            case "6":
                dodajLinieUser();
                break;
            case "7":
                dodajPrzystanek();
                break;
            case "8":
                break;
            case "9":
                break;
            case "q":
                dalej = false;
                break;
            default:
        }

        return dalej;
    }
}
