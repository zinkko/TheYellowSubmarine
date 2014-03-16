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
public class Sukellusvene {
    private int x, y,leveys,korkeus;
    private int happiTaso; // jos happi loppuu, käy huonosti
    
    public Sukellusvene(int x, int y, int happiTaso){
        this.x=x;
        this.y=y;
        this.leveys = 20;
        this.korkeus = 10;
        this.happiTaso = happiTaso;
    }

    public boolean hengissa(){
        return this.happiTaso>0;
    }
    
    public void piirra(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval(x-leveys/2, y-korkeus/2, leveys, korkeus);
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public int getHappiTaso() {
        return happiTaso;
    }
}
