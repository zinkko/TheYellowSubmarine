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
public class SukellusveneTest {
    
    private Sukellusvene vene;
    private int alkuX, alkuY;
    public SukellusveneTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        alkuX = 10;
        alkuY = 10;
        this.vene = new Sukellusvene(alkuX,alkuY,5);
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
    public void happiKuluuOikein(){
        int happea = vene.getHappiTaso();
        vene.liiku();
        vene.liiku();
        assertEquals(vene.getHappiTaso(), happea -2);
    }
    
    @Test
    public void hengissaMetodiToimii1(){
        vene.liiku();
        vene.liiku();
        assertTrue(vene.hengissa());
    }
    
    @Test
    public void hengissaMetodiToimii2(){
        for (int i=0;i<6;i++) vene.liiku();
        assertFalse(vene.hengissa());
    }
    
    @Test
    public void alussaVeneEiLiiku(){
        vene.liiku();
        vene.liiku();
        assertEquals(vene.getX(),alkuX);
        assertEquals(vene.getY(),alkuY);
    }
    
    @Test
    public void veneLiikkuuNopeudenMukaan(){
        int nx = 2; int ny = 3;
        vene.setNopeus(nx, ny);
        vene.liiku();
        vene.liiku();
        assertEquals(vene.getX(), alkuX + 2*nx);
        assertEquals(vene.getY(), alkuY + 2*ny);
    }
    
    @Test
    public void kiihdytysToimii(){
        int x = 4; int y = -2;
        vene.kiihdyta(x, y);
        vene.liiku();
        assertEquals(vene.getX(), alkuX + x);
        assertEquals(vene.getY(), alkuY + y);
    }
}
