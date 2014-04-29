/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilpo.theyellowsubmarine.kayttoliittyma;

import static com.ilpo.theyellowsubmarine.kayttoliittyma.Sovelluskuuntelija.*;
import static com.ilpo.theyellowsubmarine.Vaikeustaso.*;
import com.ilpo.theyellowsubmarine.Suunta;
import com.ilpo.theyellowsubmarine.Vaikeustaso;
import com.ilpo.theyellowsubmarine.logiikka.Pelilogiikka;
import com.ilpo.theyellowsubmarine.logiikka.Sovelluslogiikka;
import com.ilpo.theyellowsubmarine.logiikka.Tulostenkeraaja;
import com.ilpo.theyellowsubmarine.mallit.Kartta;
import com.ilpo.theyellowsubmarine.mallit.Sukellusvene;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Pelin käyttöliittymän pääluokka. Luo piirtäjän ja kuuntelijat, sekä välittää
 * viestejä logiikkaluokkien ja käyttöliittymäluokkien välillä
 *
 * @author ilari
 */
public class Kayttoliittyma implements Runnable {

    private final JFrame frame;
    private final Piirtaja piirtaja;
    private JPanel cards;
    private Pelilogiikka logiikka;
    private Sovelluslogiikka sovlog;
    private static final String MENU = "menu";
    private static final String PELI = "game";
    private static final String TULOKSET = "stats";
    private final int pituus, leveys;
    private final Tulostenkeraaja tulokset;
    private Vaikeustaso vaikeus = HELPPO;

    public Kayttoliittyma(int leveys, int pituus, Tulostenkeraaja tulokset) {
        this.frame = new JFrame();
        this.piirtaja = new Piirtaja(leveys, pituus);
        this.leveys = leveys;
        this.pituus = pituus;
        this.tulokset = tulokset;
    }

    @Override
    public void run() {

        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(300, 500));

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }

    public void maalaa() {
        this.piirtaja.repaint();
    }

    private void luoKomponentit(Container c) {

        // menun näkymä
        JPanel menu = teeMenu();

        this.frame.addKeyListener(new PeliKuuntelija(this));
        this.frame.setFocusable(true);

        //kortit
        this.cards = new JPanel(new CardLayout());
        cards.add(this.piirtaja, PELI);
        cards.add(menu, MENU);

        c.add(this.cards);
        ((CardLayout) cards.getLayout()).show(cards, MENU);
    }

    /**
     * luo sovelluksen alkuvalikko
     *
     * @return JPanel joka sisältää valikon
     */
    private JPanel teeMenu() {
        JPanel menu = new JPanel(new GridLayout(4, 1));
        // nimi
        JTextField nimi = new JTextField("The Yellow Submarine!");
        nimi.setEditable(false);
        nimi.setFont(new Font("Sans-Serif", Font.BOLD, 18));

        Sovelluskuuntelija k = new Sovelluskuuntelija(this);

        JButton alku = new JButton("aloita!");
        alku.setActionCommand(ALKU);
        JButton stats = new JButton("tulokset");
        stats.setActionCommand(STATS);

        JPanel vaikeudet = teeVaikeusAsteValinta();

        // kuuntelija
        alku.addActionListener(k);
        stats.addActionListener(k);
        for (Component comp : vaikeudet.getComponents()) {
            if (comp.getClass() == JRadioButton.class) {
                ((JRadioButton) comp).addActionListener(k);
            }
        }

        // lisaa kaikki
        menu.add(nimi);
        menu.add(vaikeudet);
        menu.add(alku);
        menu.add(stats);

        return menu;
    }

    private JPanel teeVaikeusAsteValinta() {
        JPanel ret = new JPanel(new GridLayout(1, 3));

        JRadioButton helppo = new JRadioButton("helppo");
        JRadioButton semi = new JRadioButton("perus");
        JRadioButton pro = new JRadioButton("MLG-pro");

        helppo.setActionCommand(EASY);
        semi.setActionCommand(MEDIUM);
        pro.setActionCommand(HARD);

        ret.add(helppo);
        ret.add(semi);
        ret.add(pro);

        ButtonGroup vaikeus = new ButtonGroup();
        vaikeus.add(helppo);
        vaikeus.add(semi);
        vaikeus.add(pro);
        
        return ret;
    }

    /**
     * Näytä pelikenttä
     */
    public void siirryPeliin() {
        frame.setSize(new Dimension(this.leveys, this.pituus + 20));
        sovlog.aloitaPeli(vaikeus);
        ((CardLayout) cards.getLayout()).show(cards, PELI);
    }

    /**
     * Näytä päävalikko
     * @param voitto onko peli päättynyt voittoon
     */
    public void siirryValikkoon(boolean voitto) {
        if (voitto){
            JOptionPane.showMessageDialog(frame, "Voitit! :D");
        }else{
            JOptionPane.showMessageDialog(frame, "Hävisit pelin :(");
        }
        

        frame.setSize(new Dimension(300, 500));
        try {
            ((CardLayout) cards.getLayout()).show(cards, MENU);
        } catch (NullPointerException e) {
            // testit aiheuttaa tämän koska ne ei käynnistä run() metodia
        }
    }

    public void naytaTulokset() {
        Map<String, String> tiedot = tulokset.getTulokset();
        String teksti = "";
        for (String avain : tiedot.keySet()){
            teksti += avain +": "+tiedot.get(avain)+"\n";
        }
        JOptionPane.showMessageDialog(frame, teksti,"Tulokset", JOptionPane.INFORMATION_MESSAGE);
    }

    public void asetaVaikeusTaso(Vaikeustaso vaikeus) {
         this.vaikeus = vaikeus;
    }

    /**
     * Aseta piirtäjälle oikea kartta ja sukellusvene
     *
     * @param kartta
     * @param vene
     */
    public void alustaPiirtaja(Kartta kartta, Sukellusvene vene) {
        this.piirtaja.setKartta(kartta);
        this.piirtaja.setVene(vene);
    }

    /**
     * muuta veneen nopeutta halutulla tavalla
     *
     * @param suunta suunta johon kiihdytetään
     */
    public void kiihdyta(Suunta suunta) {
        this.logiikka.liiku(suunta);
    }

    /**
     * Pelilogiikka vaihtuu, joka pelille on oma.
     *
     * @param logiikka kyseistä peli-instanssia vastaava logiikka
     */
    public void setLogiikka(Pelilogiikka logiikka) {
        this.logiikka = logiikka;
    }

    /**
     * Sovelluslogiikka ei vaihdu
     *
     * @param sovlog sovelluslogiikka
     */
    public void setSovelluslogiikka(Sovelluslogiikka sovlog) {
        this.sovlog = sovlog;
    }

    public int getKartanLeveys() {
        return this.leveys - 100;
    }

    public int getKartanPituus() {
        return this.pituus;
    }

    /**
     * TESTEILLE!
     *
     * @return
     */
    public Pelilogiikka getLogiikka() {
        return this.logiikka;
    }

}
