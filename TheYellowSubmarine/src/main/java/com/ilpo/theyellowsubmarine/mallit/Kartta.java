/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.mallit;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author ilari
 */
public class Kartta {
    private final LinkedList<Aarre> aarteet;
    private final LinkedList<Kivi> kivet; // ts. esteet kartalla
    private int leveys, korkeus;
    private int alkuX, alkuY;
    private Random random;
    
    public Kartta(int pituus, int leveys){
        this.aarteet = new LinkedList<>();
        this.kivet = new LinkedList<>();
        this.generoiAarteet(10);
    }
    
    
    
    private void generoiAarteet(int maara){
        for (int i=0;i<maara;i++){
            generoiAarre(5); // TODO: jännempi arvo aarteiden arvoille
        }
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }
    
    private void generoiAarre(int arvo){
        int x = random.nextInt(leveys-10)+5;
        int y = random.nextInt(korkeus-10)+5;
        Aarre uusiAarre = new Aarre(x,y,arvo);
        this.aarteet.add(uusiAarre);
    }
    
    public void piirra(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, leveys, korkeus);
    }

    public LinkedList<Aarre> getAarteet() {
        return aarteet;
    }
    
    public void poista(Aarre a){
        this.aarteet.remove(a);
    }
}
