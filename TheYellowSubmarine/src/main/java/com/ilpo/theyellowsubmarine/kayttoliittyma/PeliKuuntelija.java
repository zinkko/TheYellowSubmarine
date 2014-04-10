/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.kayttoliittyma;

import com.ilpo.theyellowsubmarine.Suunta;
import static com.ilpo.theyellowsubmarine.Suunta.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Kuuntelee pelaajan syötteitä kun hän ohjaa sukellusvenettä.
 * 
 * @author ilari
 */
public class PeliKuuntelija implements KeyListener{

    private Kayttoliittyma kali;
    
    public PeliKuuntelija(Kayttoliittyma kali){
        this.kali = kali;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        Suunta suunta = null;
        switch(code){
            case KeyEvent.VK_W:
                suunta = YLOS;
                break;
            case KeyEvent.VK_A:
                suunta = VASEN;
                break;
            case KeyEvent.VK_S:
                suunta = ALAS;
                break;
            case KeyEvent.VK_D:
                suunta = OIKEA;
                break;
        }
        if (suunta != null){
            kali.kiihdyta(suunta);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch(code){
            case KeyEvent.VK_W:
                break;
            case KeyEvent.VK_A:
                break;
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_D:
                break;
        }
    }
    
}
