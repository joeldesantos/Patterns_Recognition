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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public class GenerateInputs {
    
    public static void main (String[] args) {
        GenerateInputs gi = new GenerateInputs();
        gi.run();
    }
    
    public GenerateInputs () {
    }
    
    private void run () {
        File dir = new File("C:/_process");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                try {
                    System.out.println(child.getAbsolutePath());
                    String txtFileName = child.getAbsolutePath().replaceAll(".bmp", ".txt");
                    int[][] a2d = seeBMPImage(child);
                    PrintWriter writer = new PrintWriter(txtFileName, "UTF-8");
                    for (int i = 0; i < a2d.length; i ++){
                        for (int j = 0; j < a2d[i].length; j++) {
//                            System.out.print(""+a2d[i][j]);
                            writer.print(""+a2d[i][j]);
                        }
//                        System.out.print("\n");
                        writer.print("\n");
                    }
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(GenerateInputs.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
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
