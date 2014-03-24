/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.kayttoliittyma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Kuuntelee pelaajan syötteitä kun ohjaa sukellusvenettä.
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
        switch(code){
            case KeyEvent.VK_W:
                break;
            case KeyEvent.VK_A:
                break;
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_D:
                break;
            default:
                System.out.println("Hello world");
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
