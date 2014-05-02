/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import static com.ilpo.theyellowsubmarine.logiikka.Tulostenkeraaja.HAVIOT;
import static com.ilpo.theyellowsubmarine.logiikka.Tulostenkeraaja.VOITOT;
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
public class TulostenkeraajaTest {
    
    private Tulostenkeraaja stats;
    
    public TulostenkeraajaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.stats = new Tulostenkeraaja();
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
    public void tietoMuuttuu(){
        int alku = stats.getTulokset().get(VOITOT);
        int arvo = 5;
        stats.muutaTietoa(VOITOT, arvo);
        int tulos = stats.getTulokset().get(VOITOT);
        assertEquals(tulos, alku + arvo);
    }
    
    @Test
    public void tietoMuuttuu2(){
        int alku = stats.getTulokset().get(HAVIOT);
        int arvo = 5;
        stats.muutaTietoa(HAVIOT, arvo);
        int tulos = stats.getTulokset().get(HAVIOT);
        assertEquals(tulos, alku + arvo);
    }
    
    @Test
    public void vainOikeaArvoMuuttuu(){
        int alku = stats.getTulokset().get(HAVIOT);
        int arvo = 5;
        stats.muutaTietoa(VOITOT, arvo);
        int tulos = stats.getTulokset().get(HAVIOT);
        assertEquals(tulos, alku);
    }
    
    @Test
    public void tietoAsettuu(){
        int arvo = 5;
        stats.asetaTieto(VOITOT, 17);
        stats.asetaTieto(VOITOT, arvo);
        int tulos = stats.getTulokset().get(VOITOT);
        assertEquals(tulos, arvo);
    }
    
    @Test
    public void tietoAsettuu2(){
        int arvo = 5;
        stats.asetaTieto(HAVIOT, 8);
        stats.asetaTieto(HAVIOT, arvo);
        int tulos = stats.getTulokset().get(HAVIOT);
      
    }
    
    
    
}
