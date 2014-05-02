/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilpo.theyellowsubmarine.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luokka pelin tulosten tilastointiin. Kerää ja tallettaa tietoja esimerkiksi
 * voitetuiste sekä hävityistä peleistä. Muistaa tiedot aiemmilta peleiltä oman
 * tiedoston avulla (tulokset.txt)
 *
 * @author ilari
 */
public class Tulostenkeraaja {

    private final File tiedosto;
    private final HashMap<String, Integer> tiedot;

    public static final String PELIT = "pelit";
    public static final String VOITOT = "voitot";
    public static final String HAVIOT = "häviöt";
    public static final String RAHAT_H = "suurin kerätty rahamäärä (helppo)";
    public static final String RAHAT_M = "suurin kerätty rahamäärä (keskivaikea)";
    public static final String RAHAT_V = "suurin kerätty rahamäärä (vaikea)";

    /**
     * konsruktori lukee tiedoston ja alustaa HashMapin jossa se pitää tietoja
     * ohjelman suorituksen aikana.
     */
    public Tulostenkeraaja() {

        tiedosto = new File("src/main/resources/tulokset.txt");
        tiedot = lueTulokset();
    }

    /**
     * lue tiedostosta tilastot ja luo niistä hajautustaulu
     *
     * @return aiemmat tilastot sisältävä mappi
     */
    private HashMap<String, Integer> lueTulokset() {
        HashMap<String, Integer> tulosKartta = new HashMap<>();
        try {
            Scanner lukija = new Scanner(tiedosto);
            while (lukija.hasNextLine()) {
                String[] avainJaArvo = lukija.nextLine().split(":");
                int arvo = Integer.parseInt(avainJaArvo[0]);
                tulosKartta.put(avainJaArvo[1], arvo); // tiedostossa arvo:avain
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tulostenkeraaja.class.getName()).log(Level.SEVERE, null, ex);
            return luoTyhjaKartta();
        }

        return tulosKartta;
    }
    
    private HashMap<String,Integer> luoTyhjaKartta(){
        HashMap<String,Integer> mappi = new HashMap<>();
        mappi.put(VOITOT,0);
        mappi.put(HAVIOT,0);
        mappi.put(PELIT,0);
        mappi.put(RAHAT_H,0);
        mappi.put(RAHAT_M,0);
        mappi.put(RAHAT_V,0);
        return mappi;
    }

    public HashMap<String, Integer> getTulokset() {
        return this.tiedot;
    }

    /**
     * muuta joitain tietoa kasvattamalla sitä arvon verran.
     *
     * @param avain tieto jota muutetaan
     * @param arvo kuinka paljon kasvatetaan
     */
    public void muutaTietoa(String avain, int arvo) {
        int uusi = arvo + tiedot.get(avain);
        tiedot.put(avain, uusi);
    }

    /**
     * korvaa vanha tieto uudella
     *
     * @param avain tieto
     * @param arvo uusi arvo
     */
    public void asetaTieto(String avain, int arvo) {
        tiedot.put(avain, arvo);
    }

    /**
     * valitsee tilaston arvoksi suuremman aiemmasta ja uudesta arvosta
     *
     *
     * @param avain tieto
     * @param arvo arvo joka vaihdetaan jos vanha arvo pienempi
     */
    public void asetaTietoJosSuurempi(String avain, int arvo) {
        int uusi = Math.max(arvo, tiedot.get(avain));
        tiedot.put(avain, uusi);
    }

    /**
     * kirjoita tilaston nykyinen tilanne tiedostoon
     */
    public void kirjoitaTulokset() {

        try (PrintWriter kirjuri = new PrintWriter(tiedosto)) {
            for (String avain : this.tiedot.keySet()) {
                String kirjoitettavaTieto = tiedot.get(avain) + ":" + avain + "\n";
                kirjuri.print(kirjoitettavaTieto);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tulostenkeraaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
