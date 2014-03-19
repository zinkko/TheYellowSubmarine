/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import com.ilpo.theyellowsubmarine.mallit.Aarre;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;

/**
 *
 * @author ilari
 */
public class Pelilogiikka {
    private Kartta kartta;
    private Sukellusvene vene;
    private int pelaajanRahat = 0;
    private int alkuX, alkuY;
    
    public Pelilogiikka(Kartta kartta, Sukellusvene vene){
        this.kartta = kartta;
        this.vene = vene;
    }
    
    /**
     * 
     * @return false jos peli on ohi.
     */
    public boolean teeJtn(){ // TODO: rename this
        vene.liiku();
        this.pidaPelaajaKartalla();
        this.tarkistaAarteet();
        return vene.hengissa();
    }
    
    private void pidaPelaajaKartalla(){
        if (vene.getX()<0) vene.setX(0);
        if (vene.getY()<0) vene.setY(0);
        if (vene.getX() > kartta.getLeveys()) vene.setX(kartta.getLeveys());
        if (vene.getY()> kartta.getKorkeus()) vene.setY(kartta.getKorkeus());
    }
    
    private void tarkistaAarteet(){
        for (Aarre aarre: kartta.getAarteet()){
            if (aarre.voidaanKerata(vene)){
                this.pelaajanRahat += aarre.getArvo();
                kartta.poista(aarre);
            }
        }
    }
    
}
