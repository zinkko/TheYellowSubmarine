/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.kayttoliittyma;

import com.ilpo.theyellowsubmarine.logiikka.Pelilogiikka;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;
import java.awt.Container;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author ilari
 */
public class Kayttoliittyma implements Runnable{
    
    private final JFrame frame;
    private Piirtaja piirtaja;
    private Pelilogiikka logiikka;
    
    public Kayttoliittyma(Pelilogiikka logiikka, Kartta k, Sukellusvene v){
        this.logiikka = logiikka;
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
        
        mainloop();
    }
    
    private void mainloop(){
        while (true){
            this.logiikka.teeJtn();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
    private void luoKomponentit(Container c){
        c.add(this.piirtaja);
        this.piirtaja.addKeyListener(new PeliKuuntelija(this));
        this.piirtaja.setFocusable(true);
    }
    
    public void move(int direction){
        this.logiikka.move(direction);
    }
}
