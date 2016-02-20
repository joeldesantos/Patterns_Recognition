/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 * @author Dr Noureddin Sadawi
 */
public class Utils {
    
    /**
     * returns a random double value within a given range
     * @param min the minimum value of the required range (int)
     * @param max the maximum value of the required range (int)
     * @return a random double value between min and max
     */ 
    public static double randomNumber(int min, int max) {
//        DecimalFormat df = new DecimalFormat("#.####");
//        double d = min + Math.random() * (max - min);
//        String s = df.format(d);
//        double x = Double.parseDouble(s);
//        return x;
        return min + Math.random() * (max - min);
    }
    
    public static List<Integer> getCoordinates (String fact) {
        List<Integer> facts = new ArrayList();
        
        StringTokenizer tok = new StringTokenizer(fact, " (,)");
        
        while (tok.hasMoreTokens()) {
            facts.add(Integer.parseInt(tok.nextToken()));
        }
        
        return facts;
    }
    

    public static int[][] abc () {
        int[][] data = new int[1][1];
        return data;
    }
}
