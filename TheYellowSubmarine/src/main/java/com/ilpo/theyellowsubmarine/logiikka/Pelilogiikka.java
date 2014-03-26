/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import com.ilpo.theyellowsubmarine.Sovellus;
import com.ilpo.theyellowsubmarine.mallit.Aarre;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ilari
 */
public class Pelilogiikka implements Runnable{
    private final Kartta kartta;
    private final Sukellusvene vene;
    private int pelaajanRahat = 0;
    private final Sovellus app;
    private final Fysiikka fysiikka;
    
    public Pelilogiikka(Sovellus app, Kartta kartta, Sukellusvene vene){
        this.kartta = kartta;
        this.vene = vene;
        this.app = app;
        this.fysiikka = new Fysiikka(vene);
    }
    
    @Override
    public void run(){
        while (true){
            try {
                teeJtn();
                app.maalaa();
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pelilogiikka.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * 
     * @return false jos peli on ohi.
     */
    public boolean teeJtn(){ // TODO: rename this
        //vene.liiku();
        this.fysiikka.seuraava();
        this.pidaPelaajaKartalla();
        this.tarkistaAarteet();
        return vene.hengissa();
    }
    /**
     * 
     * @param direction suunta johon sukellusvene lähtee kiihdyttämään 
     */
    public void move(int direction){ 
        int dx = 0;
        int dy = 0;
        switch (direction){
            case 0:
                dx = 1;
                break;
            case 1:
                dx = -1;
                break;
            case 2:
                dy = 1;
                break;
            case 3:
                dy = -1;
                break;
        }
        vene.kiihdyta(dx, dy);
    }
    
    private void pidaPelaajaKartalla(){
        if (vene.getX()<0) vene.setX(0);
        if (vene.getY()<0) vene.setY(0);
        if (vene.getX() > kartta.getLeveys()) vene.setX(kartta.getLeveys());
        if (vene.getY()> kartta.getKorkeus()) vene.setY(kartta.getKorkeus());
    }
    
    // TODO: convert to iterator!!!!
    private void tarkistaAarteet(){
        Iterator<Aarre> aarteet = kartta.getAarteet().iterator();
        Aarre aarre;
        while (aarteet.hasNext()){
            aarre = aarteet.next();
            if (aarre.voidaanKerata(vene)){
                this.pelaajanRahat += aarre.getArvo();
                aarteet.remove();
            }
        }
    }
    
}
