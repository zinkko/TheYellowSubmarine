/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.mallit;

import java.util.LinkedList;
import java.util.Random;

/**
 * Pelikenttä
 * 
 * @author ilari
 */
public class Kartta {
    private final LinkedList<Aarre> aarteet;
    private final LinkedList<Kivi> kivet; // ts. esteet kartalla
    private int leveys, korkeus;
    private int alkuX, alkuY;
    private final int pinta = 15;
    private Random random;
    
    /**
     * pääkonstruktori
     */
    public Kartta(int leveys, int korkeus, int aarteidenMaara, int kivienMaara){
        this.aarteet = new LinkedList<>();
        this.kivet = new LinkedList<>();
        this.random = new Random();
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.generoiKivet(kivienMaara);
        this.generoiAarteet(aarteidenMaara);
    }
    
    /**
     * Luo kartta ilman kiviä, testeille lähinnä
     */
    public Kartta(int leveys, int korkeus, int aarteidenMaara){
        this(leveys,korkeus,aarteidenMaara,0);
    }
    /**
     * HUOM! vain testeille
     */
    public void lisaaKivi(Kivi kivi){
        this.kivet.add(kivi);
    }
    
    private void generoiKivet(int maara){
        if (maara==0){
            return;
        }
        Kivi kivi1 = new Kivi(leveys/4,korkeus/3,leveys/2,korkeus/4);
        Kivi kivi2 = new Kivi(leveys/10,korkeus/3*2,leveys/4,korkeus/5);
        Kivi kivi3 = new Kivi(leveys/2+leveys/8,korkeus/3*2,leveys/4,korkeus/5);
        this.kivet.add(kivi1);
        kivet.add(kivi2);
        kivet.add(kivi3);
    }
    
    private void generoiAarteet(int maara){
        for (int i=0;i<maara;i++){
            int arvo = random.nextInt(5)+5;
            generoiAarre(arvo);
        }
    }
    
    /**
     * luo yksi aarre satunnaiseen paikkaan
     * @param arvo kuinka monta "kolikkoa" pelaaja saa
     */
    private void generoiAarre(int arvo){
        int x = random.nextInt(leveys-10)+5;
        int y = random.nextInt(korkeus-10)+this.getPinta() + 10;
        Aarre uusiAarre = new Aarre(x,y,arvo);
        this.aarteet.add(uusiAarre);
    }
    
    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }
    
    public LinkedList<Aarre> getAarteet() {
        return aarteet;
    }
    
    public LinkedList<Kivi> getKivet(){
        return kivet;
    }
    
    public int getPinta(){
        return this.pinta;
    }
    
}
