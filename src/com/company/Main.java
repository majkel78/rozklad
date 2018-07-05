package com.company;

/*
    Projekt zaliczeniowy:
    ROZKŁAD JAZDY
    (nr 11)

    Autorzy:
        - asp.sztab. Michał Jarzyński
        - asp.sztab. Grzegorz Jarzyński
        - mł.asp. Piotr Pobierżyn
        SSINF_2017 grupa 2
 */

public class Main {

    public static void main(String[] args) {
        Autobusy autobusy = new Autobusy(); // inicjalizacja obiektu Autobusy

        /*
            metody klasy Autobusy:
            wczytajPrzystanki() - wczytuje z pliku listę przystanków
            wczytajLinie() - wczytuje z pliku wykaz linii autobusowych
            wyswietlPrzystanki() - wyświetla nazwy wszystkich przystanków wraz z indeksami
            wyswietlPrzystanki(indeks_przystanku) - wyświetla nazwę przystanku o zadanym indeksie
            wyswietlLinie() - wyświetla numery wszystkich linii autobusowych
            wyswietlTraseLinii(nr_linii) - wyświetla trasę (przystanki) linii autobusowej o podanym numerze
            wyswietlRozkladLinii(nr_linii) - wyświetla rozkład jazdy danej linii autobusowej
            wyswietlRozkladPrzystanku(indeks_przystanku) - wyświetla rozkład jazdy przystanku o podanym indeksie
            wyswietlMenu() - wyświetla menu operacji dla użytkownika
         */

        autobusy.wczytajPrzystanki();
        autobusy.wczytajLinie();

        while (autobusy.wyswietlMenu() == true) ;
    }
}
