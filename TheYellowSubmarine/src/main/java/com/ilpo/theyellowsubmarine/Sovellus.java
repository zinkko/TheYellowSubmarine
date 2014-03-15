package com.ilpo.theyellowsubmarine;

import com.ilpo.theyellowsubmarine.kayttoliittyma.Kayttoliittyma;
import com.ilpo.theyellowsubmarine.logiikka.SovellusOhjain;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class Sovellus {
    private Kayttoliittyma kayttoliittyma;
    private SovellusOhjain kontrolleri;
            
    public static void main( String[] args ) {
        SwingUtilities.invokeLater(new Kayttoliittyma());
    }
}
