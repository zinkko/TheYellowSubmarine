/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.kayttoliittyma;

import com.ilpo.theyellowsubmarine.Sovellus;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author ilari
 */
public class Kayttoliittyma implements Runnable{
    
    private final JFrame frame;
    private final Piirtaja piirtaja;
    private final Sovellus app;
    private JPanel cards;
    private static final String MENU = "menu";
    private static final String PELI = "game";
    
    public Kayttoliittyma(Sovellus app){
        this.app = app;
        this.frame = new JFrame();
        this.piirtaja = new Piirtaja();
    }
        
    @Override
    public void run(){
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(600,600));

        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
        
    }
    
    public void maalaa(){
        this.piirtaja.repaint();
    }
    
    private void luoKomponentit(Container c){
        // menun näkymä
        JPanel menu = teeMenu();
        
        this.frame.addKeyListener(new PeliKuuntelija(this));
        this.frame.setFocusable(true);
        
        //kortit
        this.cards = new JPanel(new CardLayout());
        cards.add(this.piirtaja,PELI);
        cards.add(menu, MENU);
        
        c.add(this.cards);
        ((CardLayout) cards.getLayout()).show(cards, MENU);
    }
    
    private JPanel teeMenu(){
        JPanel menu = new JPanel(new GridLayout(2,1));
        JTextField nimi  = new JTextField("The Yellow Submarine!");
        nimi.setEditable(false);
        nimi.setFont(new Font("Sans-Serif",Font.BOLD, 24));
        JButton alku = new JButton("aloita!");
        alku.addActionListener(new SovellusKuuntelija(app,this, alku));
        menu.add(nimi);
        menu.add(alku);
        
        return menu;
    }
    
    public void vaihda(boolean peli){
        if (peli){
            ((CardLayout) cards.getLayout()).show(cards, PELI);
            
        }else{
            ((CardLayout) cards.getLayout()).show(cards, MENU);
        }
    }
    
    public Piirtaja getPiirtaja(){
        return this.piirtaja;
    }
    
    public void move(int direction){
        this.app.move(direction);
    }
}
