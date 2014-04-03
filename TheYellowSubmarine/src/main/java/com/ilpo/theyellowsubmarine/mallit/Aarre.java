/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.mallit;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Aarretta kuvaava malli. x ja y ovat keskipisteen koordinaatit
 * @author ilari
 */
public class Aarre {
    private final int x,y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * ei sade vaan säde :P
     * @return aarteen säde
     */
    public int getSade() {
        return sade;
    }
    private final int sade = 5;
    private final int arvo;
    
    public Aarre(int x, int y, int arvo){
        this.x=x;
        this.y=y;
        this.arvo=arvo;
    }
    
    /**
     * 
     * törmäystarkistus, onko sukellusvene tarpeeksi lähellä kerätäkseen tämän aarteen?
     * 
     * @param vene vene johon verrataan
     * @return true joss sukellusvene on tarpeeksi lähellä kerätäkseen aarteen
     */
    public boolean voidaanKerata(Sukellusvene vene){
        int dx = Math.abs(vene.getX() - x);
        int dy = Math.abs(vene.getY() - y);
        // a² + b² = c²
        int etaisyys = vene.getKorkeus()/2+ sade;
        return (dx*dx + dy*dy) < etaisyys*etaisyys;
    }
    
    public int getArvo(){
        return arvo;
    }
    
    public void piirra(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(x-sade,y-sade, 2*sade, 2*sade);
    }
}
