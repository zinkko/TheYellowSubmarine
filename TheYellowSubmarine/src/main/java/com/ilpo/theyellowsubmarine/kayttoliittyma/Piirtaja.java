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
 *
 * @author ilari
 */
public class Piirtaja extends JPanel{
    
    private Kartta kartta;
    private Sukellusvene vene;
    
    public Piirtaja(Kartta kartta, Sukellusvene vene){
        this.kartta = kartta;
        this.vene = vene;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        kartta.piirra(g);
        vene.piirra(g);
    }
}
