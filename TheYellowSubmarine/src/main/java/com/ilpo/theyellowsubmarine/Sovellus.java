package com.ilpo.theyellowsubmarine;

import com.ilpo.theyellowsubmarine.kayttoliittyma.Kayttoliittyma;
import com.ilpo.theyellowsubmarine.logiikka.SovellusLogiikka;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class Sovellus {
    private Kayttoliittyma kayttoliittyma;
    private SovellusLogiikka kontrolleri;
            
    public static void main( String[] args ) {
        SwingUtilities.invokeLater(new Kayttoliittyma());
    }
}
