/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.util;

import pr.algorithms.Perceptron;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public class CallTrainForFilesInFolder {
    
    public static void main (String[] args) {
//        CallTrainForFilesInFolder gi = new CallTrainForFilesInFolder();
//        gi.run("C:/_data/class1", "c:/_data/class1.txt");
//        gi.run("C:/_data/class2", "c:/_data/class2.txt");
    }
    
    public CallTrainForFilesInFolder () {
    }
    
    public void train (String dirPath, Perceptron perceptron, int classType) {
//        String txtFileName = "c:/_data/class1.txt";
        File dir = new File(dirPath);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                try {
                    List<String> lines = Files.readAllLines(Paths.get(child.getAbsolutePath()));
                    
                    perceptron.train2(lines, classType);
                } catch (IOException ex) {
                    Logger.getLogger(CallTrainForFilesInFolder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
    }
        public void train3 (String filePath, Perceptron perceptron, int classType) {
//        String txtFileName = "c:/_data/class1.txt";
        File dir = new File(filePath);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                try {
                    List<String> lines = Files.readAllLines(Paths.get(child.getAbsolutePath()));
                    String data = fileLinesToString(lines);
                    System.out.println("filePath = "+child.getAbsolutePath()+", data = "+data);
                    perceptron.train3(data, classType);
                } catch (IOException ex) {
                    Logger.getLogger(CallTrainForFilesInFolder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
    }
    
    public int test (String txtFileName, Perceptron perceptron) {
        int classType = -1;
        try {
            PrintWriter writer = new PrintWriter(txtFileName, "UTF-8");
            
            List<String> lines = Files.readAllLines(Paths.get(txtFileName));
            
            classType = perceptron.calculateOutput2(1, perceptron.weights, lines);

        } catch (IOException ex) {
            Logger.getLogger(TransformTextToCoordinates.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return classType;
    }
    
    public int test3 (String txtFileName, Perceptron perceptron, double theta) {
        int classType = -1;
        try {
            List<String> lines = Files.readAllLines(Paths.get(txtFileName));
            
            String data = fileLinesToString(lines);
            char[] chars = data.toCharArray();
            
            classType = perceptron.calculateOutput3(theta, perceptron.weights, chars);

        } catch (IOException ex) {
            Logger.getLogger(TransformTextToCoordinates.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return classType;
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
