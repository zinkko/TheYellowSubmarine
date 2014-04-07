/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author ilari
 */
public class SovellusKuuntelija implements ActionListener{
    
    private JButton nappula;
    private Kayttoliittyma kali;
    
    public SovellusKuuntelija(Kayttoliittyma kali, JButton nappula){
        this.nappula = nappula;
        this.kali = kali;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        kali.vaihda(true);
    }
}
