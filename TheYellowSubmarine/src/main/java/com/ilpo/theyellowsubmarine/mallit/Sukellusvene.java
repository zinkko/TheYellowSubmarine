/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilpo.theyellowsubmarine.mallit;

/**
 * pelaajan sukellusveneen toimintaa, lähinnä liikkumista kuvaava malli
 * x ja y ovat keskipisteen koordinaatteja.
 * 
 * @author ilari
 */
public class Sukellusvene {

    private int x, y;
    private final int leveys, korkeus;
    private int happitaso; // jos happi loppuu, käy huonosti
    private int nopeusX, nopeusY; // pts/aikayksikkö
    private final int maxNopeus = 10;
    private int rahat;

    /**
     * pääkonstruktori
     * 
     * @param x veneen paikka alussa
     * @param y veneen paikka lopussa
     * @param happiTaso hapen määrä veneellä (tähän tosi iso luku...)
     */
    public Sukellusvene(int x, int y, int happiTaso) {
        this.x = x;
        this.y = y;
        this.leveys = 40;
        this.korkeus = 20;
        this.rahat = 0;
        this.happitaso = happiTaso;
    }
    /**
     * testit käyttää tätä vielä...
     * 
     * @deprecated 
     */
    public void liiku() {
        this.x += nopeusX;
        this.y += nopeusY;
        if (happitaso>0) happitaso--;
    }
    
    /**
     * pienennä veneen happimäärää yhdellä
     */
    public void kulutaHappi(){
        if (happitaso>0){
            happitaso--;
        }
        //System.out.println(happitaso);
    }
    
    /**
     * liikuttaa venettä vaakasuunnassa yhden yksikön eteen tai taakse sen suunnan
     * perusteella
     * 
     * @param eteen, liikutetaanko eteen vai taakse 
     */
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
    
    /**
     * liikuttaa venettä pystysuunnassa yhden yksikön eteen tai taakse sen suunnan
     * perusteella
     * 
     * @param eteen, liikutetaanko eteen vai taakse
     */
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
    
    /**
     * pysäytä vaakasuuntainen liike
     */
    public void pysahdyX(){
        nopeusX=0;
    }
    
    /**
     * pysäytä pystysuuntainen liike
     */
    public void pysahdyY(){
        nopeusY=0;
    }
    
    /**
     * muuta veneen nopeutta
     * 
     * @param accX x-suunnan kiihtyvyys
     * @param accY y-suunnan kiihtysyys
     */
    public void kiihdyta(int accX, int accY) {
        this.nopeusX += accX;
        this.nopeusY += accY;
        
        if (nopeusX>this.maxNopeus){
            nopeusX = maxNopeus;
        }
        if (nopeusY>this.maxNopeus){
            nopeusY = maxNopeus;
        }
    }

    public boolean hengissa() {
        return this.happitaso > 0;
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
    
    public int getRahat(){
        return rahat;
    }
    
    public void lisaaRahaa(int raha){
        this.rahat += raha;
    }
    
    /**
     * HUOM! vain testeille
     * @param x
     */
    public void aseta(int x, int y){
        this.x=x;
        this.y = y;
    }
    
    /**
     * vain testeille HUOM
     * @param h 
     */
    public void setHappi(int h){
        this.happitaso = h;
    }
}
