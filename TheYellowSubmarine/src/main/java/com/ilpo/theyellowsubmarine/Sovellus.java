package com.ilpo.theyellowsubmarine;

import com.ilpo.theyellowsubmarine.kayttoliittyma.Kayttoliittyma;
import com.ilpo.theyellowsubmarine.kayttoliittyma.Piirtaja;
import com.ilpo.theyellowsubmarine.logiikka.Pelilogiikka;
import com.ilpo.theyellowsubmarine.logiikka.Sovelluslogiikka;
import javax.swing.SwingUtilities;

/**
 * Sovelluksen pääluokka.
 * @author ilari
 */
public class Sovellus{
    private final Kayttoliittyma kayttoliittyma;
    private final Sovelluslogiikka kontrolleri;
    private Pelilogiikka logiikka;
    private Thread peliSaie;
    
    public Sovellus() {
        
        //Kartta k = new Kartta(500,500,10,1);
        //Sukellusvene v = new Sukellusvene(100,k.getPinta(),30);
        this.kontrolleri = new Sovelluslogiikka(this);
        this.kayttoliittyma = new Kayttoliittyma(this);
    }
    
    public void uusiPeli(){
        this.logiikka = kontrolleri.uusiPeli();
        Piirtaja p = this.kayttoliittyma.getPiirtaja();
        p.setKartta(logiikka.getKartta());
        p.setVene(logiikka.getVene());
    }
    
    public void aloitaPeli() {
        if (logiikka == null){
            System.out.println("Va i helvete? (spelet var börjas för sen)");
        }
        if (peliSaie != null){
            peliSaie.interrupt();
        }
        peliSaie = new Thread(this.logiikka);
        peliSaie.setDaemon(true);
        peliSaie.start();
    }
    
    public void lopetaPeli(){
        if (peliSaie==null) return;
        peliSaie.interrupt();
    }
    
    public static void main( String[] args ) {
        Sovellus sovellus = new Sovellus(); 
        
        SwingUtilities.invokeLater(sovellus.getKayttoliittyma());
    }
    
    public void move(int direction){
        this.logiikka.liiku(direction);
    }
    
    public void havio(){
        this.lopetaPeli();
        this.kayttoliittyma.vaihda(false);
    }
    
    public void maalaa(){
        this.kayttoliittyma.maalaa();
    }

    public Kayttoliittyma getKayttoliittyma() {
        return kayttoliittyma;
    }

    public Pelilogiikka getLogiikka() {
        return logiikka;
    }

}
