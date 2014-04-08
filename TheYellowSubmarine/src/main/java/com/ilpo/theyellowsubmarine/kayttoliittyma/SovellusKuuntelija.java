/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.kayttoliittyma;

import com.ilpo.theyellowsubmarine.Sovellus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author ilari
 */
public class SovellusKuuntelija implements ActionListener{
    
    private JButton nappula;
    private Sovellus app;
    private Kayttoliittyma kali;
    
    public SovellusKuuntelija(Sovellus app,Kayttoliittyma kali,JButton nappula){
        this.nappula = nappula;
        this.app = app;
        this.kali = kali;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        app.uusiPeli();
        kali.vaihda(true);
        app.aloitaPeli();
    }
}
