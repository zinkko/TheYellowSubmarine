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
    int pituus = 5;
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
        int dx = Math.abs(x-vene.getX());
        int dy = Math.abs(y-vene.getY());
        
        int c2 = dx*dx + dy*dy; // a² + b²
        
        return c2 < pituus*pituus;
    }
    
    public int getArvo(){
        return arvo;
    }
    
    public void piirra(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(x, y, 2*pituus, 2*pituus);
    }
}
