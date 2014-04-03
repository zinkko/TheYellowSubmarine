/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import com.ilpo.theyellowsubmarine.Sovellus;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Kivi;
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
    private int kartanLeveys;
    private int kartanKorkeus;
    private Kivi kivi;
    
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
        this.kartanKorkeus = 300;
        this.kartanLeveys = 300;
        this.kartta=  new Kartta(this.kartanLeveys,this.kartanKorkeus,0);
        this.vene= new Sukellusvene(5,5,1);
        logiikka = new Pelilogiikka(new Sovellus(), kartta,vene);
        this.kivi = new Kivi(50,50,50,50);
        //kartta.lisaaKivi(kivi);
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
        vene.aseta(this.kartanLeveys-5,this.kartanKorkeus-5);
        vene.kiihdyta(10, 10);
        vene.liiku();
        vene.liiku();
        logiikka.suorita();
        oletaPaikka(this.kartanLeveys,this.kartanKorkeus);
    }
    
    @Test
    public void eiMeneKivenLapiOikealta(){
        kartta.lisaaKivi(kivi);
        vene.aseta(100 + vene.getLeveys(),75);
        vene.kiihdyta(-10, 0);
        for (int i=0;i<20;i++) logiikka.suorita();
        assertEquals(101,vene.getVasenReuna());
    }
    
    @Test
    public void eiMeneKivenLapiVasemmalta(){
        kartta.lisaaKivi(kivi);
        vene.aseta(50 - vene.getLeveys(),75);
        vene.kiihdyta(10, 0);
        for (int i=0;i<20;i++) logiikka.suorita();
        assertEquals(49,vene.getOikeaReuna()); 
    }
    @Test
    public void eiMeneKivenLapiYlhaalta(){
        kartta.lisaaKivi(kivi);
        vene.aseta(75, 50 - vene.getKorkeus());
        vene.kiihdyta(0,10);
        for (int i=0;i<30;i++) logiikka.suorita();
        assertEquals(49,vene.getAlareuna());
    }
    @Test
    public void eiMeneKivenLapiAlhaalta(){
        kartta.lisaaKivi(kivi);
        vene.aseta(75, 100 + vene.getKorkeus());
        vene.kiihdyta(0,-10);
        for (int i=0;i<20;i++) logiikka.suorita();
        assertEquals(101,vene.getYlareuna());
    }
    
    @Test
    public void viestitKaliltaValittyy1(){
        logiikka.liiku(0);
        oletaNopeus(1,0);
    }
    @Test
    public void viestitKaliltaValittyy2(){
       logiikka.liiku(1); 
       oletaNopeus(-1,0);
    }
    @Test
    public void viestitKaliltaValittyy3(){
        logiikka.liiku(2);
        oletaNopeus(0,1);
    }
    @Test
    public void viestitKaliltaValittyy4(){
        logiikka.liiku(3);
        oletaNopeus(0,-1);
    }
    
      
    private void oletaPaikka(int x, int y){
        assertEquals(x, vene.getX());
        assertEquals(y, vene.getY()); 
    }
    
    private void oletaNopeus(int x, int y){
        assertEquals(x, vene.getNopeusX());
        assertEquals(y, vene.getNopeusY());
    }
    
    
}
