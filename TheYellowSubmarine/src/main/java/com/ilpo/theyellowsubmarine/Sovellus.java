package com.ilpo.theyellowsubmarine;

import com.ilpo.theyellowsubmarine.kayttoliittyma.Kayttoliittyma;
import com.ilpo.theyellowsubmarine.logiikka.Sovelluslogiikka;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class Sovellus {
    private Kayttoliittyma kayttoliittyma;
    private Sovelluslogiikka kontrolleri;
            
    public static void main( String[] args ) {
        Kartta k = new Kartta(500,500,10);
        Sukellusvene v = new Sukellusvene(10,10,10000);
        
        SwingUtilities.invokeLater(new Kayttoliittyma(k,v));
    }
}
