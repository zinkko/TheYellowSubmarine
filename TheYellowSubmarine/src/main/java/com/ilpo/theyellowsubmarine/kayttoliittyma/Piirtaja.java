/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.kayttoliittyma;

import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;
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
    
    public Piirtaja(Kartta kartta, Sukellusvene vene){
        this.kartta = kartta;
        this.vene = vene;
    }
    
    public Piirtaja(){
        this.palken = new Sivupalkki(500, 0, 100,500, 3000); // poista taikaluvut
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
            kartta.piirra(g);
        }
        if (vene != null){
            vene.piirra(g);
            this.palken.piirra(g, vene.getHappiTaso(), vene.getRahat());
        }
        
    }
}
