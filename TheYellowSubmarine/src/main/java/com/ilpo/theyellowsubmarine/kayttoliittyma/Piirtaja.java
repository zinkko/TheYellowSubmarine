/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.kayttoliittyma;

import com.ilpo.theyellowsubmarine.mallit.Aarre;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Kivi;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Piirrä peliin liittyvät komponentit.
 * 
 * @author ilari
 */
public class Piirtaja extends JPanel{
    
    private Kartta kartta;
    private Sukellusvene vene;
    private Sivupalkki palken;
    

    public Piirtaja(int kartanleveys, int kartankorkeus){
        this.palken = new Sivupalkki(kartanleveys-100, 0, 100,kartankorkeus ,3000); // poista taikaluvut
    }

    public void setKartta(Kartta kartta) {
        this.kartta = kartta;
    }

    public void setVene(Sukellusvene vene) {
        this.vene = vene;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        if (kartta != null) {
            piirraKartta(g);
        }
        if (vene != null){
            piirraVene(g);
            this.palken.piirra(g, vene.getHappiTaso(), vene.getRahat());
        }
       // System.out.println(vene.getX()+" <> "+ vene.getY());
        
    }
    
    private void piirraKartta(Graphics g){
        int leveys = this.kartta.getLeveys();
        int korkeus = this.kartta.getKorkeus();
        int pinta=  this.kartta.getPinta();
        g.setColor(new Color(120,155,255));
        g.fillRect(0, 0, leveys, korkeus);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, leveys, pinta);
        for (Aarre a:this.kartta.getAarteet()){
            piirraAarre(g,a);
        }
        
        for (Kivi k:this.kartta.getKivet()){
            piirraKivi(g,k);
        }    
    }
    
    private void piirraVene(Graphics g){
        int leveys = this.vene.getLeveys();
        int korkeus = this.vene.getKorkeus();
        g.setColor(Color.YELLOW);
        g.fillOval(this.vene.getX() - leveys/2, vene.getY() - korkeus/2, leveys, korkeus);
    }
    
    private void piirraAarre(Graphics g, Aarre a){
        g.setColor(Color.GREEN);
        g.fillOval(a.getX()-a.getSade(),a.getY()-a.getSade(), 2*a.getSade(), 2*a.getSade());
    }
    
    private void piirraKivi(Graphics g, Kivi k){
        g.setColor(Color.GRAY);
        g.fillRect(k.getX(), k.getY(), k.getLeveys(), k.getKorkeus());
    }
}
