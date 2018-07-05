package com.company;

// klasa abstrakcyjna, z której dziedziczą pozostałe klasy w programie
public abstract class Komunikacja {
    Komunikacja(){
        if(Global.debug) System.out.println("Konstruktor: Komunikacja");
    }
}
