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
    private int x, y;
    private int happiTaso; // jos happi loppuu, käy huonosti
    
    public Sukellusvene(int x, int y, int happiTaso){
        this.x=x;
        this.y=y;
        this.happiTaso = happiTaso;
    }
    
    public boolean hengissa(){
        return this.happiTaso>0;
    }
    
    public void piirra(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval(x-10, y-5, 20, 10);
    }
}
