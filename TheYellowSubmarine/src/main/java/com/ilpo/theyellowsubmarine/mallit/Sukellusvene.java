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

    private int x, y, leveys, korkeus;
    private int happitaso; // jos happi loppuu, käy huonosti
    private int nopeusX, nopeusY; // pts/aikayksikkö
    private int maxNopeus = 10;

    public Sukellusvene(int x, int y, int happiTaso) {
        this.x = x;
        this.y = y;
        this.leveys = 40;
        this.korkeus = 20;
        this.happitaso = happiTaso;
    }
    /**
     * @deprecated 
     */
    public void liiku() {
        this.x += nopeusX;
        this.y += nopeusY;
        if (happitaso>0) happitaso--;
    }
    
    public void siirra(int[] paikka){
        this.x = paikka[0];
        this.y = paikka[1];
    }
    
    public void kulutaHappi(){
        if (happitaso>0) happitaso--;
    }
    
    public void liikuVaakatasossa(boolean eteen){
        int lisays;
        if (eteen) {
            lisays=1;
        }else{
            lisays = -1;
        }
        if (this.nopeusX>0){
            this.x+=lisays;
        }
        else{
            this.x-=lisays;
        }
    }
    
    public void liikuPystytasossa(boolean eteen){
        int lisays;
        if (eteen) {
            lisays=1;
        }else{
            lisays = -1;
        }
        if (this.nopeusY>0){
            this.y+=lisays;
        }
        else{
            this.y-=lisays;
        }
    }
    
    public void pysahdyX(){
        nopeusX=0;
    }
    
    public void pysahdyY(){
        nopeusY=0;
    }
    
    public void kiihdyta(int accX, int accY) {
        this.nopeusX += accX;
        this.nopeusY += accY;
        
        if (nopeusX>this.maxNopeus) nopeusX = maxNopeus;
        if (nopeusY>this.maxNopeus) nopeusY = maxNopeus;
    }

    public boolean hengissa() {
        return this.happitaso > 0;
    }

    public void piirra(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x - leveys/2, y - korkeus/2, leveys, korkeus);
    }
    
    public void setNopeus(int x, int y){
        this.nopeusX = x;
        this.nopeusY = y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
    
    public int getOikeaReuna(){
        return x + this.leveys/2;
    }
    
    public int getVasenReuna(){
        return x - this.leveys/2;
    }
    
    public int getAlareuna(){
        return y + this.korkeus/2;
    }
    
    public int getYlareuna(){
        return y - this.korkeus/2;
    }

    public int getHappiTaso() {
        return happitaso;
    }
    
    public int getNopeusX(){
        return nopeusX;
    }
    
    public int getNopeusY(){
        return nopeusY;
    }
}
