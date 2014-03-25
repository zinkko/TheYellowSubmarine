package com.ilpo.theyellowsubmarine;

import com.ilpo.theyellowsubmarine.kayttoliittyma.Kayttoliittyma;
import com.ilpo.theyellowsubmarine.logiikka.Pelilogiikka;
import com.ilpo.theyellowsubmarine.logiikka.Sovelluslogiikka;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class Sovellus{
    private Kayttoliittyma kayttoliittyma;
    private Sovelluslogiikka kontrolleri;
    private Pelilogiikka logiikka;
    
    public Sovellus() {
        Kartta k = new Kartta(500,500,10);
        Sukellusvene v = new Sukellusvene(100,100,10000);
        
        logiikka = new Pelilogiikka(k,v);
        kayttoliittyma = new Kayttoliittyma(logiikka,k,v);
        
    }
    
    public static void main( String[] args ) {
        Sovellus sovellus = new Sovellus(); 
        
        SwingUtilities.invokeLater(sovellus.getKayttoliittyma());
    }
    
    public void move(int direction){
        this.logiikka.move(direction);
    }

    public Kayttoliittyma getKayttoliittyma() {
        return kayttoliittyma;
    }

    public Pelilogiikka getLogiikka() {
        return logiikka;
    }

}
