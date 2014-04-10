package com.ilpo.theyellowsubmarine;

import com.ilpo.theyellowsubmarine.kayttoliittyma.Kayttoliittyma;
import com.ilpo.theyellowsubmarine.logiikka.Sovelluslogiikka;
import javax.swing.SwingUtilities;

/**
 * Sovelluksen pääluokka. Luo toteutuksesta vastaavat oliot (Käyttöliittymä ja Sovelluslogiikka)
 * ja käynnistää ne.
 * @author ilari
 */
public class Sovellus{

    public static void main( String[] args ) {
        Kayttoliittyma kali = new Kayttoliittyma();
        Sovelluslogiikka sovlog = new Sovelluslogiikka(kali);
        kali.setSovelluslogiikka(sovlog);
        SwingUtilities.invokeLater(kali);
    }
 
}
