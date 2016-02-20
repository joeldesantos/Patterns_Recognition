/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public class LoadCoordinatesFromFile {
    
    List<List> class1 = null;
    List<List> class2 = null;
    
    public LoadCoordinatesFromFile() {
        class1 = loadValuesFromFile2("c:/_data/class1.txt");
        class2 = loadValuesFromFile2("c:/_data/class2.txt");
    }
    
    public LoadCoordinatesFromFile(String fileClass1, String fileClass2) {
        class1 = loadValuesFromFile2(fileClass1);
        class2 = loadValuesFromFile2(fileClass2);
    }
    
    public List<List> loadValuesFromFile (String txtFileName) {
        List<List> out = new ArrayList();
        List<Integer> x = new ArrayList();
        List<Integer> y = new ArrayList();
        try {
            List<String> lines = Files.readAllLines(Paths.get(txtFileName));
            
            for (String line : lines) {
                String sub = line.substring(1, line.length() - 1);
                
                StringTokenizer tok = new StringTokenizer(sub, "\",\"");
                if (tok.hasMoreTokens()) {
                    //Ignore first token because is file name
                    tok.nextToken();
                    
                    while (tok.hasMoreTokens()) {
                        String token = tok.nextToken();
//                        System.out.println("token = "+ token);
                        String sub2 = token.substring(1, token.length() - 1);

                        StringTokenizer tok2 = new StringTokenizer(sub2, ":");
                        if (tok2.hasMoreTokens()) {
                            x.add(Integer.parseInt(tok2.nextToken()));
                            y.add(Integer.parseInt(tok2.nextToken()));
                        }
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(LoadCoordinatesFromFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.add(x);
        out.add(y);
        return out;
    }

    public List<List> loadValuesFromFile2 (String txtFileName) {
        List<List> out = new ArrayList();
        List<Double> values = null;
        try {
            List<String> lines = Files.readAllLines(Paths.get(txtFileName));
            
            for (String line : lines) {
                String sub = line.substring(1, line.length() - 1);
                values = new ArrayList();
                
                StringTokenizer tok = new StringTokenizer(sub, "\",\"");
                if (tok.hasMoreTokens()) {
                    //Ignore first token because is file name
                    tok.nextToken();
                    
                    while (tok.hasMoreTokens()) {
                        values.add(Double.parseDouble(tok.nextToken()));
                    }
                }
                
                out.add(values);
            }

        } catch (IOException ex) {
            Logger.getLogger(LoadCoordinatesFromFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    public List<List> getClass1() {
        return class1;
    }

    public List<List> getClass2() {
        return class2;
    }
    
}
