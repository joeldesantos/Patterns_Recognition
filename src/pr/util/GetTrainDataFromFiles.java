/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.util;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
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
public class GetTrainDataFromFiles {
    
    public static void main (String[] args) {
        GetTrainDataFromFiles gi = new GetTrainDataFromFiles();
        gi.run("C:/_data/class1b", "c:/_data/Traindata1.txt", 0);
        gi.run("C:/_data/class2b", "c:/_data/Traindata1.txt", 1);
    }
    
    public GetTrainDataFromFiles () {
    }
    
    private void run (String dirPath, String txtFileName, int classType) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(
                    new File(txtFileName), 
                    true /* append = true */));
            StringBuilder sb = new StringBuilder();
            
            File dir = new File(dirPath);
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                String lineToWrite = "";
                int minSize = 0;
                for (File child : directoryListing) {
                    sb.delete(0, sb.length());
                    
                    List<String> lines = Files.readAllLines(Paths.get(child.getAbsolutePath()));
                    
                    sb.append("\"");
                    sb.append(""+classType);
                    sb.append("\",");
                    sb.append("\"");
                    sb.append(fileLinesToStringSpecial(lines));
                    sb.append("\"");
                    
                    writer.println(sb.toString());
                }
            } else {
                // Handle the case where dir is not really a directory.
                // Checking dir.isDirectory() above would not be sufficient
                // to avoid race conditions with another process that deletes
                // directories.
            }
        } catch (IOException ex) {
            Logger.getLogger(GetTrainDataFromFiles.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        
    }
    
    private String fileLinesToString(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        if (lines != null) {
            for (String line : lines) {
                sb.append(line.replaceAll("\n", ""));
            }
        }
        return sb.toString();
    }
    
    private String fileLinesToStringSpecial(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        if (lines != null) {
            int first, last;
            char[] chars;
            for (String line : lines) {
                first = line.indexOf("1");
                last = line.lastIndexOf("1");
                chars = line.replaceAll("\n", "").toCharArray();
                for (int i = first; i < last; i++) {
                    chars[i] = '1';
                }
                sb.append(new String(chars));
            }
        }
        return sb.toString();
    }
    
    
}
