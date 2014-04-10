/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import com.ilpo.theyellowsubmarine.kayttoliittyma.Kayttoliittyma;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;

/**
 * Ei vielä implementoitu. Tänne sovelluksen logiikka, esim. menu
 * @author ilari
 */
public class Sovelluslogiikka {
    private final Kayttoliittyma kali;
    private Thread peliSaie;
    
    public Sovelluslogiikka(Kayttoliittyma kali){
        this.kali = kali;
        Pelilogiikka peli = this.luoUusiPeli();
        this.kali.setLogiikka(peli);
    }
       
    public void lopetaPeli(){
        if (peliSaie==null) return;
        peliSaie.interrupt();
        kali.siirrySovellukseen();
    }
    
    public void aloitaPeli() {
        Pelilogiikka logiikka = luoUusiPeli();
        kali.setLogiikka(logiikka);
        kali.alustaPiirtaja(logiikka.getKartta(), logiikka.getVene());
        
        if (peliSaie!=null) peliSaie.interrupt();
        peliSaie = new Thread(logiikka);
        peliSaie.setDaemon(true);
        peliSaie.start();
    }
    
    private Pelilogiikka luoUusiPeli(){
        Kartta k = new Kartta(500,500,20,1);
        Sukellusvene v = new Sukellusvene(k.getLeveys()/2, k.getPinta(),3000);
        return new Pelilogiikka(kali,this, k, v);
    }
}
