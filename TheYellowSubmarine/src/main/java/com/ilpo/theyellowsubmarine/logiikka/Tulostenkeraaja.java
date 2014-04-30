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
 * @author ilari
 */
public class Tulostenkeraaja {

    private final File tiedosto;
    private final HashMap<String, String> tiedot;
    
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
        String path = new File(".").getAbsolutePath();
        path = path.replace(".", "src/main/java/com/ilpo/theyellowsubmarine/logiikka/tulokset.txt");
        tiedosto = new File(path);
        tiedot = lueTulokset();
    }

    /**
     * lue tiedostosta tilastot ja luo niistä hajautustaulu
     * @return aiemmat tilastot sisältävä mappi
     */
    private HashMap<String, String> lueTulokset() {
        HashMap<String, String> tulosKartta = new HashMap<>();
        try {
            Scanner lukija = new Scanner(tiedosto);
            while (lukija.hasNextLine()) {
                String[] avainJaArvo = lukija.nextLine().split(":");
                tulosKartta.put(avainJaArvo[1], avainJaArvo[0]); // tiedostossa arvo:avain
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tulostenkeraaja.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tulosKartta;
    }
    
    public HashMap<String,String> getTulokset(){
        return this.tiedot;
    }
    
    /**
     * muuta joitain tietoa kasvattamalla sitä arvon verran.
     * 
     * @param avain tieto jota muutetaan
     * @param arvo kuinka paljon kasvatetaan
     */
    public void muutaTietoa(String avain, int arvo){
        int vanha = Integer.parseInt(tiedot.get(avain));
        tiedot.put(avain, ""+(vanha+arvo));
    }
    
    /**
     * korvaa vanha tieto uudella
     * @param avain tieto
     * @param arvo uusi arvo
     */
    public void asetaTieto(String avain, int arvo){
        tiedot.put(avain, ""+arvo);
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
