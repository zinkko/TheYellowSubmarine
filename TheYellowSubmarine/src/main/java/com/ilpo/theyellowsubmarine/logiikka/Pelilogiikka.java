/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilpo.theyellowsubmarine.logiikka;

import com.ilpo.theyellowsubmarine.Suunta;
import com.ilpo.theyellowsubmarine.Vaikeustaso;
import com.ilpo.theyellowsubmarine.kayttoliittyma.Kayttoliittyma;
import com.ilpo.theyellowsubmarine.mallit.Aarre;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Kivi;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.ilpo.theyellowsubmarine.logiikka.Tulostenkeraaja.*;

/**
 * Pelin toimintaa ohjaava luokka. Jokaisella pelillä on oma Logiikka-olionsa
 *
 * @author ilari
 */
public class Pelilogiikka implements Runnable {

    private final Kartta kartta;
    private final Sukellusvene vene;
    private final Fysiikka fysiikka;
    private final Kayttoliittyma kali;
    private final Sovelluslogiikka sovelluslogiikka;
    private final Vaikeustaso vaikeus;
    private Tulostenkeraaja tulostenHoitaja;

    /**
     *
     * @param kali käyttöliittymä
     * @param sovlog sovelluslogiikka
     * @param kartta kartta jolla pelataan
     * @param vene pelaajan vene
     * @param stats tulostenkeraaja joka tallettaa pelin tulokset lopussa
     * @param vaikeus pelin vaikeustaso
     */
    public Pelilogiikka(Kayttoliittyma kali, Sovelluslogiikka sovlog, Kartta kartta,
            Sukellusvene vene, Tulostenkeraaja stats, Vaikeustaso vaikeus) {
        this.kartta = kartta;
        this.vene = vene;
        this.kali = kali;
        this.sovelluslogiikka = sovlog;
        this.fysiikka = new Fysiikka(this.vene);
        this.tulostenHoitaja = stats;
        this.vaikeus = vaikeus;
    }

    /**
     * pelin "kello"
     */
    @Override
    public void run() {
        boolean peliJatkuu;
        while (true) {
            try {
                suorita();
                kali.maalaa();
                if (peliHavitty() || peliVoitettu()) {
                    lopetaPeli();
                    break;
                }
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pelilogiikka.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * hoida pelin lopussa tulosten kirjaus ja ilmoita sovelluslogiikalle
     */
    private void lopetaPeli() {
        keraaTulokset();
        this.tulostenHoitaja.kirjoitaTulokset();
        sovelluslogiikka.lopetaPeli(peliVoitettu());
    }

    public void keraaTulokset() {
        if (peliVoitettu()) {
            this.tulostenHoitaja.muutaTietoa(VOITOT, 1);
        } else {
            this.tulostenHoitaja.muutaTietoa(HAVIOT, 1);
        }
        this.tulostenHoitaja.muutaTietoa(PELIT, 1);

        String tietue = RAHAT_H;
        switch (vaikeus) {
            case HELPPO:
                tietue = RAHAT_H;
                break;
            case KESKIVAIKEA:
                tietue = RAHAT_M;
                break;
            case VAIKEA:
                tietue = RAHAT_V;
                break;
        }
        this.tulostenHoitaja.asetaTieto(tietue, vene.getRahat());
    }

    /**
     * logiikan toiminnan päämetodi. Run kutsuu tätä tasaisesti (public koska
     * testit :D)
     *
     */
    public void suorita() { // TODO: rename this
        //vene.liiku();
        this.fysiikka.seuraava();
        this.pidaPelaajaKartalla();
        this.tarkistaAarteet();
        this.tarkistaKivet();
        if (this.vene.getY() > kartta.getPinta()) {
            this.vene.kulutaHappi();
        }
        //return vene.hengissa() && kartta.maalissa(vene.getX(), vene.getY());
    }

    /**
     * onko pelaaja voittanut
     * @return true joss peli on voitettu 
     */
    public boolean peliVoitettu() {
        return kartta.maalissa(vene.getX(), vene.getY());
    }

    /**
     * onko pelaaja hävinnyt
     * @return true joss peli on hävitty
     */
    public boolean peliHavitty() {
        return !vene.hengissa();
    }

    /**
     * muuttaa veneen liikettä. metodin kutsu muuttaa veneen nopeutta yhdellä
     * k.o. suuntaan
     *
     * @param suunta suunta johon sukellusvene lähtee kiihdyttämään
     */
    public void liiku(Suunta suunta) {
        this.vene.kiihdyta(suunta.x, suunta.y);
    }

    /**
     * Pidä pelaaja kartalla
     */
    private void pidaPelaajaKartalla() {
        if (vene.getX() < 0) {
            vene.setX(0);
            vene.pysahdyX();
        }
        if (vene.getX() > kartta.getLeveys()) {
            vene.setX(kartta.getLeveys());
            vene.pysahdyX();
        }
        if (vene.getY() < kartta.getPinta()) {
            vene.setY(kartta.getPinta());
            vene.pysahdyY();
        }
        if (vene.getY() > kartta.getKorkeus()) {
            vene.setY(kartta.getKorkeus());
            vene.pysahdyY();
        }
    }

    /**
     * kerää aarteet jotka ovat tarpeeksi lähellä
     */
    private void tarkistaAarteet() {
        Iterator<Aarre> aarteet = kartta.getAarteet().iterator();
        Aarre aarre;
        while (aarteet.hasNext()) {
            aarre = aarteet.next();
            if (aarre.voidaanKerata(vene)) {
                this.vene.lisaaRahaa(aarre.getArvo());
                aarteet.remove();
            }
        }
    }

    /**
     * tarkista ettei pelaaja kulje kivien läpi
     */
    private void tarkistaKivet() {
        for (Kivi kivi : kartta.getKivet()) {
            if (kivi.tormaa(vene)) {
                vene.liikuVaakatasossa(false); // arvaus
                if (kivi.tormaa(vene)) {
                    vene.liikuVaakatasossa(true); // ei auttanut :(
                    vene.liikuPystytasossa(false);
                    vene.pysahdyY();
                } else {
                    vene.pysahdyX();
                }
            }
        }
    }

    public int getPelaajanRahat() {
        return this.vene.getRahat();
    }

    public Kartta getKartta() {
        return kartta;
    }

    public Sukellusvene getVene() {
        return vene;
    }

}
