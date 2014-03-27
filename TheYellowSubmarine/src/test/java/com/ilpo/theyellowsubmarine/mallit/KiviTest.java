/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.mallit;

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
public class KiviTest {
    
    private Kivi kivi;
    
    public KiviTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kivi = new Kivi(10,10,50,50);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void tormaaOikeinJosVeneTormaa(){
        Sukellusvene vene = new Sukellusvene(20,40,1);
        assertTrue(kivi.tormaa(vene));
    }
    
    @Test
    public void tormaaOikeinJosVeneRajalla(){
        Sukellusvene vene = new Sukellusvene(10,40,1);
        assertTrue(kivi.tormaa(vene));
    }
    
    @Test
    public void tormaaOikeinJosVeneEiTormaa(){
        Sukellusvene vene = new Sukellusvene(8,70,1);
        assertFalse(kivi.tormaa(vene));
    }
    
    @Test
    public void tormaaOikeinJosAarreTormaa(){
        Aarre aarre = new Aarre(20,30,1);
        assertTrue(kivi.tormaa(aarre));
    }
    
    @Test
    public void tormaaOikeinJosAarreEiTormaa(){
        Aarre aarre = new Aarre(70,5,1);
        assertFalse(kivi.tormaa(aarre));
    }
    
    @Test
    public void tormaaOikeinJosAarreRajalla(){
        Aarre aarre = new Aarre(20,5,1);
        assertTrue(kivi.tormaa(aarre));
    }
}
