/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilpo.theyellowsubmarine.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author ilari
 */
public class Tulostenkeraaja {

    private final File tiedosto;
    
    public Tulostenkeraaja(){
        String path = new File(".").getAbsolutePath();
        path = path.replace(".", "src/main/java/com/ilpo/theyellowsubmarine/logiikka/tulokset.txt");
        tiedosto = new File(path);
    }
    
    public HashMap<String, String> lueTulokset(){
        HashMap<String, String> ret = new HashMap<>();
        try{
            Scanner lukija = new Scanner(tiedosto);
            while(lukija.hasNextLine()){
                System.out.println("line!");
                String[] keyValuePair = lukija.nextLine().split("=");
                ret.put(keyValuePair[0], keyValuePair[1]);
            }
        }catch (FileNotFoundException ex){
            
        }
        
        return ret;
    }
}


