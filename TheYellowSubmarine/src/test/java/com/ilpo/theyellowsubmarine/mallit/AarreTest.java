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
public class AarreTest {
    
    private Sukellusvene vene;
    
    public AarreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.vene = new Sukellusvene(10,10,0);
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
    public void keraysToimiiKunVoidaanKerata(){
        Aarre a = new Aarre(5,5,5);
        assertTrue(a.voidaanKerata(vene));
    }
    
    @Test
    public void keraysToimiiKunEiVoiKerata(){
        Aarre aarre = new Aarre(50,50,50);
        assertFalse(aarre.voidaanKerata(vene));
    }
    
    @Test
    public void rajallaToimii(){
        Aarre a = new Aarre(10,vene.getAlareuna()+5,5);
    }
}
