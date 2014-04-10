/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import com.ilpo.theyellowsubmarine.Suunta;
import com.ilpo.theyellowsubmarine.kayttoliittyma.Kayttoliittyma;
import com.ilpo.theyellowsubmarine.mallit.Aarre;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Kivi;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Pelin toimintaa ohjaava luokka.
 * Jokaisella pelillä on oma Logiikka-olionsa
 * @author ilari
 */
public class Pelilogiikka implements Runnable{
    private final Kartta kartta;
    private final Sukellusvene vene;
    private final Fysiikka fysiikka;
    private final Kayttoliittyma kali;
    private final Sovelluslogiikka sovlog;
    
    /**
     * 
     * @param kali käyttöliittymä
     * @param sovlog sovelluslogiikka
     * @param kartta kartta jolla pelataan
     * @param vene pelaajan vene
     */
    public Pelilogiikka(Kayttoliittyma kali,Sovelluslogiikka sovlog, Kartta kartta, Sukellusvene vene){
        this.kartta = kartta;
        this.vene = vene;
        this.kali = kali;
        this.sovlog = sovlog;
        this.fysiikka = new Fysiikka(this.vene);
    }
    
    /**
     * pelin "kello"
     */
    @Override
    public void run(){
        boolean peliJatkuu;
        while (true){
            try {
                peliJatkuu = suorita();
                kali.maalaa();
                if (!peliJatkuu){
                    sovlog.lopetaPeli();
                    break;
                }
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pelilogiikka.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * logiikan toiminnan päämetodi. Run kutsuu tätä tasaisesti
     * (public koska testit :D)
     * @return false jos peli on ohi.
     */
    public boolean suorita(){ // TODO: rename this
        //vene.liiku();
        this.fysiikka.seuraava();
        this.pidaPelaajaKartalla();
        this.tarkistaAarteet();
        this.tarkistaKivet();
        if (this.vene.getY()>kartta.getPinta()){
            this.vene.kulutaHappi();
        }
        return vene.hengissa();
    }
    /**
     * muuttaa veneen liikettä. metodin kutsu muuttaa veneen nopeutta yhdellä
     * k.o. suuntaan
     * @param suunta suunta johon sukellusvene lähtee kiihdyttämään 
     */
    public void liiku(Suunta suunta){ 
        int dx = 0;
        int dy = 0;
        switch (suunta){
            case OIKEA:
                dx = 1;
                break;
            case VASEN:
                dx = -1;
                break;
            case ALAS:
                dy = 1;
                break;
            case YLOS:
                dy = -1;
                break;
        }
        this.vene.kiihdyta(dx, dy);
    }
    
    /**
     * Pidä pelaaja kartalla
     */
    private void pidaPelaajaKartalla(){
        if (vene.getX()<0){
            vene.setX(0);
            vene.pysahdyX();
        }
        if (vene.getX() > kartta.getLeveys()){
            vene.setX(kartta.getLeveys());
            vene.pysahdyX();
        }
        if (vene.getY()<kartta.getPinta()){
            vene.setY(kartta.getPinta());
            vene.pysahdyY();
        } 
        if (vene.getY()> kartta.getKorkeus()){
            vene.setY(kartta.getKorkeus());
            vene.pysahdyY();
        }
    }
    
    /**
     * kerää aarteet jotka ovat tarpeeksi lähellä
     */
    private void tarkistaAarteet(){
        Iterator<Aarre> aarteet = kartta.getAarteet().iterator();
        Aarre aarre;
        while (aarteet.hasNext()){
            aarre = aarteet.next();
            if (aarre.voidaanKerata(vene)){
                this.vene.lisaaRahaa(aarre.getArvo());
                aarteet.remove();
            }
        }
    }
    
    /**
     * tarkista ettei pelaaja kulje kivien läpi
     */
    private void tarkistaKivet(){
        for (Kivi kivi: kartta.getKivet()){
            if (kivi.tormaa(vene)){
                vene.liikuVaakatasossa(false); // arvaus
                if (kivi.tormaa(vene)){
                    vene.liikuVaakatasossa(true); // ei auttanut :(
                    vene.liikuPystytasossa(false);
                    vene.pysahdyY();
                }else{
                    vene.pysahdyX();
                }
            }
        }
    }
    
    public int getPelaajanRahat(){
        return this.vene.getRahat();
    }

    public Kartta getKartta() {
        return kartta;
    }

    public Sukellusvene getVene() {
        return vene;
    }
    
}
