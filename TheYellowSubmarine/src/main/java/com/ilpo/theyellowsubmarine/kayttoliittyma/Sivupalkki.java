/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.kayttoliittyma;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ilari
 */
public class Sivupalkki{
    private final int maxHappi;
    private final int leveys,korkeus,x,y;
    
    public Sivupalkki(int x, int y, int leveys, int korkeus, int maxHappi){
        this.maxHappi = maxHappi;
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.x = x;
        this.y = y;
    }
    
    public void piirra(Graphics g, int happi, int rahat){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, leveys, korkeus);
        g.setColor(Color.RED);
        piirraHappiPalkki(g, happi);
        g.setColor(Color.BLACK);
        g.drawLine(x,y+korkeus/2,x+leveys,y+korkeus/2);
        piirraRahat(g, rahat);
    }
    
    private void piirraHappiPalkki(Graphics g, int happi){
        int px, py, lev, kork;
        // padding = 10 molemmilla puolilla
        lev = 10;
         // pad
        kork = laskeKorkeus(happi);
        px = x + leveys/2 - lev/2; // eli happipalkki tulee koko palkin keskelle
        py = y + korkeus/2-kork-10;
                
        g.setColor(Color.MAGENTA.darker());
        g.fillRect(px, py, lev, kork);
    }
    
    private int laskeKorkeus(int happi){
        int maxKorkeus = korkeus/2-20;
        return maxKorkeus - (maxHappi-happi)*maxKorkeus/maxHappi;
    }
    
    private void piirraRahat(Graphics g, int rahat){
        g.setColor(Color.YELLOW.darker());
        g.setFont(new Font("Consolas", Font.BOLD, 24));
        char[] merkit = (""+rahat).toCharArray();
        g.drawChars(merkit, 0, merkit.length, x + leveys/4, y + korkeus*3/4);
    }
    
    
}
