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
public class GetFeaturesFromFiles {
    
    public static void main (String[] args) {
        GetFeaturesFromFiles gi = new GetFeaturesFromFiles();
        gi.run("C:/_data/class1", "c:/_data/class1.txt");
        gi.run("C:/_data/class2", "c:/_data/class2.txt");
    }
    
    public GetFeaturesFromFiles () {
    }
    
    private void run (String dirPath, String txtFileName) {
        try {
            PrintWriter writer = new PrintWriter(txtFileName, "UTF-8");
            StringBuilder sb = new StringBuilder();
            
            File dir = new File(dirPath);
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                String lineToWrite = "";
                int minSize = 0;
                for (File child : directoryListing) {
                    sb.delete(0, sb.length());
                    
                    List<String> lines = Files.readAllLines(Paths.get(child.getAbsolutePath()));
                    
                    int xCount = getXcount(lines);
                    int yCount = getYcount(lines);
                    Point point = getCenterOfMass(lines);
                    
                    sb.append("\"");
                    sb.append(""+child.getName());
                    sb.append("\",");
                    sb.append("\"");
                    sb.append(""+xCount);
                    sb.append("\",");
                    sb.append("\"");
                    sb.append(""+yCount);
                    sb.append("\",");
                    sb.append("\"");
                    sb.append(""+point.getX());
                    sb.append("\",");
                    sb.append("\"");
                    sb.append(""+point.getY());
                    sb.append("\"");
                    writer.println(sb.toString());
                }
            } else {
                // Handle the case where dir is not really a directory.
                // Checking dir.isDirectory() above would not be sufficient
                // to avoid race conditions with another process that deletes
                // directories.
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(GetFeaturesFromFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private int getXcount(List<String> lines) throws IOException {
        int xCount = 0;
        
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '1') {
                    xCount++;
//                    break;
                }
            }
        }
        
        return xCount;
    }
    
    private int getYcount(List<String> lines) throws IOException {
        int yCount = 0;
        
        int stopX = lines.get(0).length();
        for (int i = 0; i < stopX; i++) {
            for (int j = 0; j < lines.size(); j ++) {
                if (lines.get(j).charAt(i) == '1') {
                    yCount++;
//                    break;
                }
            }
        }
        
        return yCount;
    }
    
    private Point getCenterOfMass(List<String> lines) {
        List<Point> points = new ArrayList();
        for (int j = 0; j < lines.size(); j ++) {
            for (int i = 0; i < lines.get(j).length(); i++) {
                if (lines.get(j).charAt(i) == '1') {
                    points.add(new Point(i, j));
                }
            }
        }
        
        return centroid(points);
    }
    
    public Point centroid(List<Point> points)  {
        double centroidX = 0, centroidY = 0;

            for(Point point : points) {
                centroidX += point.getX();
                centroidY += point.getY();
            }
//        return new Point((int)(centroidX / points.size()), (int)(centroidY / points.size()));
        return new Point((int)centroidX, (int)centroidY);
    }
    
}
