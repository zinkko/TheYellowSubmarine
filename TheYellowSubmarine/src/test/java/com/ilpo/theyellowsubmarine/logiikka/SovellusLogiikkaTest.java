/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import com.ilpo.theyellowsubmarine.Suunta;
import com.ilpo.theyellowsubmarine.Vaikeustaso;
import com.ilpo.theyellowsubmarine.kayttoliittyma.Kayttoliittyma;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilari
 */
public class SovellusLogiikkaTest {
    
    private Sovelluslogiikka sovlog;
    private Kayttoliittyma kali;
    
    public SovellusLogiikkaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kali = new Kayttoliittyma(0,0,null);
        sovlog = new Sovelluslogiikka(kali);
        kali.setSovelluslogiikka(sovlog);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void kaliSaaLogiikan(){
        assertNotNull(kali.getLogiikka());
    }
    
    @Test
    public void logiikkaVaihtuuPelinVaihtuessa(){
        Pelilogiikka eka = kali.getLogiikka();
        sovlog.lopetaPeli(true);
        sovlog.aloitaPeli(Vaikeustaso.HELPPO);
        sovlog.lopetaPeli(true); // keskeytä säie
        
        assertTrue(!eka.equals(kali.getLogiikka()));
    }
    
    
    @Test
    public void sukellusVeneTuleeOikeaanPaikkaan(){
        Sukellusvene v = kali.getLogiikka().getVene();
        assertEquals(v.getY(), kali.getLogiikka().getKartta().getPinta());
        assertEquals(v.getX(), kali.getLogiikka().getKartta().getLeveys()/2);
    }
    
}
