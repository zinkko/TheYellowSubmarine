/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fysiikkasimulaattori;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author ilari
 */
public class Kali implements Runnable{
    
    private JFrame frame;
    
    public Kali(){
        this.frame = new JFrame();
    }
    
    @Override
    public void run(){
        frame.setPreferredSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.setVisible(true);
        frame.pack();
    }
    
    private void luoKomponentit(Container c){
        
    }
}
