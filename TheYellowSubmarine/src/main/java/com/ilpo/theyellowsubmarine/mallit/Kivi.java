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
public class Kivi {
    int x,y,width,height;
    
    public Kivi(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean tormaa(Sukellusvene vene){
        int vx = vene.getVasenReuna(); int vy = vene.getYlareuna();
        boolean a = (vene.getVasenReuna() <= x&&x <= vene.getOikeaReuna() ) || (x <= vx&&vx <= x+width);
        boolean b = (vene.getYlareuna() <= y&&y <= vene.getAlareuna() ) || (y <= vy&&vy <= y + height);
        return a && b;
    }
    
    public boolean tormaa(Aarre aarre){
        int ax = aarre.getX(); int ay = aarre.getY(); int r = aarre.getSade();
        boolean a = x-r <= ax && ax <= x+width+r;
        boolean b = y-r <= ay && ay <= y+width+r;
        return a && b;
    }
    
    public void piirra(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public int getOikeaReuna(){
        return x + width;
    }
    
    
}
