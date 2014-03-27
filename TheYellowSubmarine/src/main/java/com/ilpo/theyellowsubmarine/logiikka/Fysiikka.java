/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;

/**
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
    
    public void seuraava(){
        paivitaRajat(); // jos veneen nopeus on muuttunut
        xSuunta();
        ySuunta();
    }
    
    private void xSuunta(){
        if (rajaX == -1) return; // nopeus == 0, turha tehdä mtn
        tickX++;
        if (tickX >= rajaX) {
            vene.liikuVaakatasossa();
            tickX = 0;
        }
        
    }
    
    private void ySuunta(){
        if (rajaY == -1) return; // nopeus == 0
        tickY++;
        if (tickY >= rajaY){
            vene.liikuPystytasossa();
            tickY = 0;
        }
    }
    
    private void paivitaRajat(){
        int x = Math.abs(vene.getNopeusX());
        int y = Math.abs(vene.getNopeusY());
        
        rajaX = laskeRaja(x);
        rajaY = laskeRaja(y);
    }
    
    private int laskeRaja(int luku){
        if (luku==0) return -1;
        
        return Fysiikka.AIKA_YKSIKKO/luku;
    }
}
