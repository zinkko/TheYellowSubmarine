/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine;

/**
 * Pelin vaikeustaso
 * @author ilari
 */
public enum Vaikeustaso {
    HELPPO(4000), KESKIVAIKEA(3000), VAIKEA(2000);
    
    public final int happimaara;
    
    private Vaikeustaso(int happea){
        this.happimaara = happea;
    }
}
