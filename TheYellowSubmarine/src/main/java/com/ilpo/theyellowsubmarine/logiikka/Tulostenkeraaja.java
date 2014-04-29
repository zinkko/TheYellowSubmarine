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
 *
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

    public Tulostenkeraaja() {
        String path = new File(".").getAbsolutePath();
        path = path.replace(".", "src/main/java/com/ilpo/theyellowsubmarine/logiikka/tulokset.txt");
        tiedosto = new File(path);
        tiedot = lueTulokset();
    }

    private HashMap<String, String> lueTulokset() {
        HashMap<String, String> ret = new HashMap<>();
        try {
            Scanner lukija = new Scanner(tiedosto);
            while (lukija.hasNextLine()) {
                //System.out.println("line!");
                String[] keyValuePair = lukija.nextLine().split(":");
                ret.put(keyValuePair[1], keyValuePair[0]); // tiedostossa arvo:avain
            }
        } catch (FileNotFoundException ex) {

        }

        return ret;
    }
    
    public HashMap<String,String> getTulokset(){
        return this.tiedot;
    }
    
    public void muutaTietoa(String avain, int arvo){
        int vanha = Integer.parseInt(tiedot.get(avain));
        tiedot.put(avain, ""+(vanha+arvo));
    }
    
    public void asetaTieto(String avain, int arvo){
        tiedot.put(avain, ""+arvo);
    }

    public void kirjoitaTulokset() {
        try (PrintWriter pw = new PrintWriter(tiedosto)) {
            for (String avain : this.tiedot.keySet()) {
                String data = tiedot.get(avain) + ":" + avain + "\n";
                pw.print(data);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tulostenkeraaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
