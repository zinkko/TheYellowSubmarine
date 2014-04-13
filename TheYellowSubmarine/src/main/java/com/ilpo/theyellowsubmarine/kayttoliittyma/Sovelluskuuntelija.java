/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.kayttoliittyma;

import com.ilpo.theyellowsubmarine.logiikka.Sovelluslogiikka;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author ilari
 */
public class Sovelluskuuntelija implements ActionListener{
    
    private final Kayttoliittyma kali;
    
    public Sovelluskuuntelija(Kayttoliittyma kali){
        this.kali = kali;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
    }
}
