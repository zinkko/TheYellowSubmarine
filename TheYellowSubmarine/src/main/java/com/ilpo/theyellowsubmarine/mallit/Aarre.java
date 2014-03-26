/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.mallit;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author ilari
 */
public class Aarre {
    int x,y;
    int sade = 5;
    int arvo;
    
    public Aarre(int x, int y, int arvo){
        this.x=x;
        this.y=y;
        this.arvo=arvo;
    }
    
    /**
     * 
     * @param vene
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
