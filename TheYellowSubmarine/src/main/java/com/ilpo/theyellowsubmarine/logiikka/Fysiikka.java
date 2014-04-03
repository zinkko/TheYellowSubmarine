/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;

/**
 * Fysiikan toiminnasta vastaava luokka
 * ohjaa veneen liikettä jotta se olisi mahdollisimman sulavaa
 * 
 * Tämän luokan seuraava-metodia kutsutaan tasaisesti. Pelaajan venettä liikutetaan joka
 * n. kutsu, riippuen sen nopeudesta, esim. nopeudella 5 vene liikkuu joka toinen (10/5) kutsu.
 * joka kutsulla päivitetään rajat joiden mukaan vene liikkuu. (kuinka monta kutsua joutuu odottamaan)
 * Rajan muuttaminen ei vaikuta laskurin (tickX/tickY) arvoon.
 * 
 * @author ilari
 */
public class Fysiikka {
    
    public static final int AIKA_YKSIKKO = 10;
    
    private final Sukellusvene vene;
    private int tickX, tickY;
    private int rajaX = -1;
    private int rajaY = -1;
    
    
    public Fysiikka(Sukellusvene vene){
        this.vene = vene;
    }
    
    /**
     * luokan päämetodi, liikuttaa venettä jos tarpeeksi aikaa on kulunut.
     * metodi olettaa että sitä kutsutaan tasaisesti
     */
    public void seuraava(){
        paivitaRajat(); // jos veneen nopeus on muuttunut
        xSuunta();
        ySuunta();
    }
    
    /**
     * päivitä veneen liikkeen tilaa. Liikuta venettä jos tarpeen
     * (pelkkä x-suunta)
     */
    private void xSuunta(){
        if (rajaX == -1) return; // nopeus == 0, turha tehdä mtn
        tickX++;
        if (tickX >= rajaX) {
            vene.liikuVaakatasossa(true);
            tickX = 0;
        }
        
    }
    
    /**
     * päivitä veneen liikkeen tilaa, nyt y-suunnassa
     */
    private void ySuunta(){
        if (rajaY == -1) return; // nopeus == 0
        tickY++;
        if (tickY >= rajaY){
            vene.liikuPystytasossa(true);
            tickY = 0;
        }
    }
    
    /**
     * reagoi veneen nopeuden muutoksiin.
     */
    private void paivitaRajat(){
        int x = Math.abs(vene.getNopeusX());
        int y = Math.abs(vene.getNopeusY());
        
        rajaX = laskeRaja(x);
        rajaY = laskeRaja(y);
    }
    
    /**
     * laske aika joka pitää odottaa etenemisien välillä.
     * 
     * @param luku veneen nopeus
     * @return kuinka monta iskua (seuraava():n kutsua) pitää odottaa kunnes liikutaan
     */
    private int laskeRaja(int luku){
        if (luku==0) return -1;
        
        return Fysiikka.AIKA_YKSIKKO/luku;
    }
}
