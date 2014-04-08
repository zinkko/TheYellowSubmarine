/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import com.ilpo.theyellowsubmarine.Sovellus;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;

/**
 * Ei vielä implementoitu. Tänne sovelluksen logiikka, esim. menu
 * @author ilari
 */
public class Sovelluslogiikka {
    private final Sovellus app;
    
    public Sovelluslogiikka(Sovellus app){
        this.app = app;
    }
    
    public Pelilogiikka uusiPeli(){
        Kartta k = new Kartta(500,500,20,1);
        Sukellusvene v = new Sukellusvene(k.getLeveys()/2, k.getPinta(),3000);
        return new Pelilogiikka(app, k, v);
    }
}
