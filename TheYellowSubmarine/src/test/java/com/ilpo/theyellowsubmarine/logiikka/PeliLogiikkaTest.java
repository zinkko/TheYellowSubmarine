/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import com.ilpo.theyellowsubmarine.Sovellus;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
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
public class PeliLogiikkaTest {
    
    private Pelilogiikka logiikka;
    private Sukellusvene vene;
    private Kartta kartta;
    
    public PeliLogiikkaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.kartta=  new Kartta(10,10,0);
        this.vene= new Sukellusvene(5,5,1);
        logiikka = new Pelilogiikka(new Sovellus(), kartta,vene);
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
    public void pelaajaPysyyKartalla(){
        vene.kiihdyta(-10, -10);
        vene.liiku();
        vene.liiku();
        logiikka.suorita();
        oletaPaikka(0,0);

    }
    
    @Test
    public void pelaajaPysyyKartalla2(){
        vene.kiihdyta(10, 10);
        vene.liiku();
        vene.liiku();
        logiikka.suorita();
        oletaPaikka(10,10);
    }
    
    
    
    private void oletaPaikka(int x, int y){
        assertEquals(x, vene.getX());
        assertEquals(y, vene.getY()); 
    }
}
