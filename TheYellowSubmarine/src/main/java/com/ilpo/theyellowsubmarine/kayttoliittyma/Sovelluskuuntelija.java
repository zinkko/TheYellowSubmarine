/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.kayttoliittyma;

import com.ilpo.theyellowsubmarine.Vaikeustaso;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ilari
 */
public class Sovelluskuuntelija implements ActionListener{
    
    private final Kayttoliittyma kali;
    
    public static final String ALKU = "start";
    public static final String STATS = "tulokset";
    public static final String EASY = "helppo";
    public static final String MEDIUM = "normaali";
    public static final String HARD = "mlgpro";
    
    public Sovelluskuuntelija(Kayttoliittyma kali){
        this.kali = kali;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        switch (e.getActionCommand()) {
            case ALKU:
                kali.siirryPeliin();
                break;
            case STATS:
                kali.naytaTulokset();
                break;
            default:
                kali.asetaVaikeusTaso(this.getVaikeus(e.getActionCommand()));
                break;
        }
    }
    
    private Vaikeustaso getVaikeus(String lahde){
        switch(lahde){
            case EASY:
                return Vaikeustaso.HELPPO;
            case MEDIUM:
                return Vaikeustaso.KESKIVAIKEA;
            case HARD:
                return Vaikeustaso.VAIKEA;
            default:
                return Vaikeustaso.HELPPO;
        }
    }
}
