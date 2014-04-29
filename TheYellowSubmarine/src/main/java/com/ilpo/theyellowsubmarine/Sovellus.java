package com.ilpo.theyellowsubmarine;

import com.ilpo.theyellowsubmarine.kayttoliittyma.Kayttoliittyma;
import com.ilpo.theyellowsubmarine.logiikka.Sovelluslogiikka;
import com.ilpo.theyellowsubmarine.logiikka.Tulostenkeraaja;
import javax.swing.SwingUtilities;

/**
 * Sovelluksen pääluokka. Luo toteutuksesta vastaavat oliot (Käyttöliittymä ja Sovelluslogiikka)
 * ja käynnistää ne.
 * @author ilari
 */
public class Sovellus{

    public static void main( String[] args ) {
        int ikkunanPituus = 700;
        int ikkunanLeveys = 1000;
        Tulostenkeraaja tulokset = new Tulostenkeraaja();
        Kayttoliittyma kali = new Kayttoliittyma(ikkunanLeveys,ikkunanPituus, tulokset);
        Sovelluslogiikka sovlog = new Sovelluslogiikka(kali);
        kali.setSovelluslogiikka(sovlog);
        SwingUtilities.invokeLater(kali);
    }
 
}
