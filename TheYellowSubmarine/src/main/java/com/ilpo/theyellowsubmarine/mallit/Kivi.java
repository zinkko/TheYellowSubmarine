/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.mallit;

/**
 * Kivi jonka läpi pelaaja ei pääse
 * 
 * @author ilari
 */
public class Kivi {
    int x,y,leveys,korkeus;
    
    /**
     * 
     * @param x kiven paikka (nurkka)
     * @param y kiven paikka (nurkka)
     * @param leveys
     * @param korkeus 
     */
    
    public Kivi(int x, int y, int leveys, int korkeus){
        this.x = x;
        this.y = y;
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    
    /**
     * törmäystarkistus
     * 
     * @param vene, vene jota tarkastellaan
     * @return true, jos ja vain jos vene ja kivi tormäävät
     */
    public boolean tormaa(Sukellusvene vene){
        int vx = vene.getVasenReuna(); int vy = vene.getYlareuna();
        boolean xSuuntaViittaaTormaykseen = 
                (vene.getVasenReuna() <= x&&x <= vene.getOikeaReuna() ) || (x <= vx&&vx <= x+leveys);
        boolean ySuuntaViittaaTormaykseen = 
                (vene.getYlareuna() <= y&&y <= vene.getAlareuna() ) || (y <= vy&&vy <= y + korkeus);
        return xSuuntaViittaaTormaykseen && ySuuntaViittaaTormaykseen;
    }
    
    /**
     * 
     * törmäystarkistus
     * 
     * @param aarre aarre jota tarkastellaan
     * @return true jos ja vain jos aarre ja ovat liian lähellä toisiaan
     */
    public boolean tormaa(Aarre aarre){
        int ax = aarre.getX(); int ay = aarre.getY(); int sade = aarre.getSade();
        boolean xSuuntaViittaaTormaykseen = x-sade <= ax && ax <= x+leveys+sade;
        boolean ySuuntaViittaaTormaykseen = y-sade <= ay && ay <= y+leveys+sade;
        return xSuuntaViittaaTormaykseen && ySuuntaViittaaTormaykseen;
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
        return x + leveys;
    }
    
    
}
