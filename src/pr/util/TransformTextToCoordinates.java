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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public class TransformTextToCoordinates {
    
    public static void main (String[] args) {
        TransformTextToCoordinates gi = new TransformTextToCoordinates();
        gi.run("C:/_data/class1", "c:/_data/class1.txt");
        gi.run("C:/_data/class2", "c:/_data/class2.txt");
    }
    
    public TransformTextToCoordinates () {
    }
    
    private void run (String dirPath, String txtFileName) {
//        String txtFileName = "c:/_data/class1.txt";
        try {
            PrintWriter writer = new PrintWriter(txtFileName, "UTF-8");
            
            File dir = new File(dirPath);
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                String lineToWrite = "";
                int minSize = 0;
                for (File child : directoryListing) {
                    lineToWrite = "\""+child.getName()+"\",";
                    minSize = lineToWrite.length();
                    List<String> lines = Files.readAllLines(Paths.get(child.getAbsolutePath()));
                    
                    String line = null;
                    for (int i = 0, y = lines.size(); i < lines.size(); i++,y--) {
                        line = lines.get(i);
                        for (int j = 0; j < line.length(); j++) {
                            if (line.charAt(j) == '1') {
                                lineToWrite += "\"("+j+":"+y+")\",";
                            }
                        }
                    }
                    
                    if (lineToWrite.length() > minSize) {
                        lineToWrite = lineToWrite.substring(0, lineToWrite.length() - 1);
                        writer.println(lineToWrite);
                    }
                }
            } else {
                // Handle the case where dir is not really a directory.
                // Checking dir.isDirectory() above would not be sufficient
                // to avoid race conditions with another process that deletes
                // directories.
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(TransformTextToCoordinates.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private int[][] seeBMPImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);

        int[][] array2D = new int[image.getHeight()][image.getWidth()];

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                int color = image.getRGB(xPixel, yPixel);
                if (color==Color.BLACK.getRGB()) {
//                    array2D[xPixel][yPixel] = 1;
                    array2D[yPixel][xPixel] = 1;
                } else {
//                    array2D[xPixel][yPixel] = 0; // ?
                    array2D[yPixel][xPixel] = 0;
                }
            }
        }
        
        return array2D;
    }
    
}
