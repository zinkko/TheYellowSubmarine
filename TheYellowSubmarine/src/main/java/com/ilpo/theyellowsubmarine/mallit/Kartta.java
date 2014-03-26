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
    
    public Kartta(int leveys, int korkeus, int aarteidenMaara){
        this.aarteet = new LinkedList<>();
        this.kivet = new LinkedList<>();
        this.random = new Random();
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.generoiKivet();
        this.generoiAarteet(aarteidenMaara);
    }
    
    private void generoiKivet(){
        int x,y;
        for (int i=1;i<4;i++){
            x = random.nextInt(50) + i*100;
            y = random.nextInt(300) + 50;
            generoiKivi(x,y);
        }
    }
    
    private void generoiKivi(int x, int y){
        int lev = random.nextInt(20)+30;
        int kork = random.nextInt(20)+30;
        Kivi kivi = new Kivi(x,y,lev,kork);
        this.kivet.add(kivi);
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
        g.setColor(new Color(120,155,255));
        g.fillRect(0, 0, leveys, korkeus);
        
        for (Aarre a:this.aarteet){
            a.piirra(g);
        }
        
        for (Kivi k:this.kivet){
            k.piirra(g);
        }
    }

    public LinkedList<Aarre> getAarteet() {
        return aarteet;
    }
    
}
