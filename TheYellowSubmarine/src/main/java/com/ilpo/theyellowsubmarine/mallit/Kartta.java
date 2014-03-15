/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.mallit;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author ilari
 */
public class Kartta {
    private LinkedList<Aarre> aarteet;
    private LinkedList<Kivi> kivet; // ts. esteet kartalla
    private int pituus, leveys;
    
    public Kartta(int pituus, int leveys){
        this.aarteet = new LinkedList<>();
        this.kivet = new LinkedList<>();
    }
    
    public void piirra(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, pituus, leveys);
    }
    
}
