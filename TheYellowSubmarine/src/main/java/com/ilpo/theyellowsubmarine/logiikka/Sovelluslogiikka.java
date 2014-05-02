/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import com.ilpo.theyellowsubmarine.Vaikeustaso;
import com.ilpo.theyellowsubmarine.kayttoliittyma.Kayttoliittyma;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;

/**
 * Hoitaa sovelluksen toimintaa, aloittaa pelit, lopettaa pelit, hallitsee siirtymistä
 * valikon ja pelinäkymän välillä.
 * @author ilari
 */
public class Sovelluslogiikka {
    private final Kayttoliittyma kayttoliittyma;
    private Thread peliSaie;
    private final Tulostenkeraaja stats;
    
    public Sovelluslogiikka(Kayttoliittyma kali, Tulostenkeraaja tulokset){
        this.kayttoliittyma = kali;
        this.stats = tulokset;
        Pelilogiikka peli = this.luoUusiPeli(Vaikeustaso.HELPPO);
        this.kayttoliittyma.setLogiikka(peli);
    }
       
    /**
     * keskeytä meneillään oleva peli
     * @param voitto onko peli päättynyt voittoon vai häviöön
     */
    public void lopetaPeli(boolean voitto){
        if (peliSaie==null) return;
        peliSaie.interrupt();
        kayttoliittyma.siirryValikkoon(voitto);
    }
    
    /**
     * aloita uusi peli
     * @param vaikeus pelin vaikeustaso
     */
    public void aloitaPeli(Vaikeustaso vaikeus) {
        Pelilogiikka logiikka = luoUusiPeli(vaikeus);
        kayttoliittyma.setLogiikka(logiikka);
        kayttoliittyma.alustaPiirtaja(logiikka.getKartta(), logiikka.getVene(), vaikeus);
        
        if (peliSaie!=null) peliSaie.interrupt();
        peliSaie = new Thread(logiikka);
        peliSaie.setDaemon(true);
        peliSaie.start();
    }
    
    /**
     * luo uusi Pelilogiikka-olio uutta peliä varten
     * @return uuden pelin logiikka
     */
    private Pelilogiikka luoUusiPeli(Vaikeustaso vaikeus){
        int leveys = this.kayttoliittyma.getKartanLeveys();
        int pituus = this.kayttoliittyma.getKartanPituus();
        Kartta kartta = new Kartta(leveys,pituus,20,1);
        Sukellusvene vene = new Sukellusvene(kartta.getLeveys()/2, kartta.getPinta(),vaikeus.happimaara);
        return new Pelilogiikka(kayttoliittyma,this, kartta, vene, stats,vaikeus);
    }
    
    public boolean peliKaynnissa(){
        return this.peliSaie.isAlive();
    }
}
