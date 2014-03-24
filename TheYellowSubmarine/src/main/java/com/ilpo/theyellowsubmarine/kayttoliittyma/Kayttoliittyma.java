/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.kayttoliittyma;

import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author ilari
 */
public class Kayttoliittyma implements Runnable{
    
    private final JFrame frame;
    private Piirtaja piirtaja;
    
    public Kayttoliittyma(Kartta k, Sukellusvene v){
        this.frame = new JFrame();
        this.piirtaja = new Piirtaja(k,v);
    }
    
    @Override
    public void run(){
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(500,500));
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container c){
        c.add(this.piirtaja);
        this.piirtaja.addKeyListener(new PeliKuuntelija(this));
        this.piirtaja.setFocusable(true);
    }
}
