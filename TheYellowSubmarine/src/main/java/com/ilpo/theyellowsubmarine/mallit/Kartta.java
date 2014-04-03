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
    
    public Kartta(int leveys, int korkeus, int aarteidenMaara, int kivienMaara){
        this.aarteet = new LinkedList<>();
        this.kivet = new LinkedList<>();
        this.random = new Random();
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.generoiKivet(kivienMaara);
        this.generoiAarteet(aarteidenMaara);
    }
    
    public Kartta(int leveys, int korkeus, int aarteidenMaara){
        this(leveys,korkeus,aarteidenMaara,0);
    }
    
    private void generoiKivet(int maara){
        if (maara==0){
            return;
        }
        Kivi kivi1 = new Kivi(leveys/4,korkeus/3,leveys/2,korkeus/4);
        Kivi kivi2 = new Kivi(leveys/10,korkeus/3*2,leveys/3,korkeus/4);
        Kivi kivi3 = new Kivi(leveys/2+leveys/8,korkeus/3*2,leveys/3,korkeus/4);
        this.kivet.add(kivi1);
        kivet.add(kivi2);
        kivet.add(kivi3);
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
    
    public LinkedList<Kivi> getKivet(){
        return kivet;
    }
    
}
