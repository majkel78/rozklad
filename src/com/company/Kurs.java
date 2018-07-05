package com.company;

public class Kurs {
    private String[] godziny;
    private String csvSplitBy = ";";

    protected Kurs(){
        if(Global.debug) System.out.println("Konstruktor: Kurs");
    }

    // dodaje do bieżącego obiektu kursy z podanej linii "line"
    protected void dodaj(String line){
        godziny = line.split(csvSplitBy);
    }

    // wyświetla wszystkie godziny odjazdów jedna za drugą (oddzielone spacjami)
    protected void wyswietl(){
        for(String value:godziny){
            System.out.print(value+" ");
        }
        System.out.println("");
    }

    // pobiera godiznę odjazdu o podanym indeksie "indeks"
    protected String pobierz_godzine(Integer indeks){
        return godziny[indeks];
    }
}
