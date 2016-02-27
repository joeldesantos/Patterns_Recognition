/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

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
    
    public static void getTrainingListsFromRawDataList (List<String> rawData, List<String> data, List<Integer> classes) {
        StringTokenizer tok;
        for (String line : rawData) {
            tok = new StringTokenizer(line,",");
            classes.add(new Integer(tok.nextToken().replaceAll("\"", "")));
            data.add(tok.nextToken().replaceAll("\"", ""));
        }
    }

    public static String getTestDataFromRawDataList (List<String> rawData) {
        StringBuilder sb = new StringBuilder();
        for (String line : rawData) {
            sb.append(line.replaceAll("\n", ""));
        }
        return sb.toString();
    }

    public static void showDialoj (String title, String text, Frame parent) {
        JTextPane p = new JTextPane();
        p.setText(text);
//        JScrollPane scroll = new JScrollPane();
//        scroll.add(p);
        JDialog dialog = new JDialog(parent, title);
        dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//        dialog.add(scroll);
        dialog.add(p);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
    
    public static void showDialoj (String title, JPanel p, Frame parent) {
//        JScrollPane scroll = new JScrollPane();
//        scroll.add(p);
        JDialog dialog = new JDialog(parent, title);
        dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//        dialog.add(scroll);
        dialog.add(p);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
    
    public static boolean isNullOrEmpty(String str) {
        boolean out = false;
        
        if (str == null || str == "") {
            out = true;
        }
        
        return out;
    }
    
    public static String getWeightsHeader(int weigthsLength) {
        String out;
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < weigthsLength; i++) {
            sb.append("\"w[");
            sb.append(""+i);
            sb.append("]\"");
            sb.append(",");
        }
        out = sb.toString();
        out = out.substring(0, out.length()-1);
        out += "\n";
        
        return out;
    }
    
    public static String getWeights(double[] weights) {
        String out;
        StringBuilder sb = new StringBuilder();

        DecimalFormat df = new DecimalFormat("#.####");
        for (int i = 0; i < weights.length; i++) {
            sb.append("\"");
            sb.append(""+weights[i]);
//            sb.append(df.format(weights[i]));
            sb.append("\"");
            sb.append(",");
        }
        out = sb.toString();
        out = out.substring(0, out.length()-1);
        out += "\n";
        
        return out;
    }
    
    public static int[][] abc () {
        int[][] data = new int[1][1];
        return data;
    }
}
