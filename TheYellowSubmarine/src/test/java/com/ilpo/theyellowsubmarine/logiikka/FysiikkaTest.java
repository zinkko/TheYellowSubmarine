/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

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
public class FysiikkaTest {
    
    private Fysiikka fysiikka;
    private Sukellusvene vene;
    private int alkuX, alkuY;
    
    public FysiikkaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        alkuX = 10; alkuY = 10;
        vene = new Sukellusvene(alkuX,alkuY,1);
        fysiikka = new Fysiikka(vene);
        vene.kiihdyta(2, 2); // veneen nopeus on nyt 2 pistettä / aikyksikkö (molempiin suuntiinn)
        // nyt vene liikkuu yhden eteenpäin joka 5. kellonisku (aikayksikkö = 10 iskua)
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    public void veneEiLiikuHeti(){
        fysiikka.seuraava();
        fysiikka.seuraava();
        oletaSiirtyma(0,0);
    }
    
    @Test
    public void liikkuOikeanMaaran1(){
        nIskua(7);
        oletaSiirtyma(1,1);
    }
    
    @Test
    public void liikkuOikeanMaaran2(){
        nIskua(16);
        oletaSiirtyma(3,3);
    }
    
    @Test
    public void liikkuOikeanMaaranRaja(){
         nIskua(20);
         oletaSiirtyma(4,4);
    }
    @Test
    public void kiihdytysVaikuttaaHeti(){
        nIskua(2);
        vene.kiihdyta(1, 1);
        nIskua(2);
        oletaSiirtyma(1,1);
    }
    
    private void nIskua(int n){
        for (int i=0;i<n;i++) fysiikka.seuraava();
    }
    
    private void oletaSiirtyma(int dx, int dy){
        assertEquals(alkuX+dx, vene.getX());
        assertEquals(alkuY+dy, vene.getY());
    }
}
