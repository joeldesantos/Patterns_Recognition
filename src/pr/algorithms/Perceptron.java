/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.algorithms;

import java.util.ArrayList;
import java.util.List;
import pr.ui.Graph;
import pr.util.CallTrainForFilesInFolder;
import pr.util.LoadCoordinatesFromFile;
import pr.util.Utils;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public class Perceptron {
    
    private double learningRate;
    private double theta;
    public double[] weights;
    private int epochLimit;

    public Perceptron (int epochLimit) {
        this.epochLimit = epochLimit;
        initPerceptron(2);
    }   
    
    public Perceptron (double learningRate, double[] weights, double theta, int epochLimit) {
        this.learningRate = learningRate;
        this.theta = theta;
        this.weights = weights;
        this.epochLimit = epochLimit;
    }
    
    public static void main (String[] args) {
        Perceptron p = new Perceptron(100);
//        p.initPerceptron(4);
//        LoadCoordinatesFromFile lcff = new LoadCoordinatesFromFile("c:/_data/class1.txt", "c:/_data/class2.txt");
////        LoadCoordinatesFromFile lcff = new LoadCoordinatesFromFile("c:/_data/simple_class1.txt", "c:/_data/simple_class2.txt");
//        List lw1 = p.train(lcff.getClass1(), 0);
//        List lw2 = p.train(lcff.getClass2(), 1);
//        
//        List<List> testData = lcff.loadValuesFromFile2("c:/_data/test1_class1.txt");
////        List<List> testData = lcff.loadValuesFromFile("c:/_data/simple_test1_class1.txt");
//        int output = 0;
//        int accumulated = 0;
//        output = calculateOutput(p.theta, p.weights, testData.get(0));
//        System.out.println("output = " + output);
//        if (output > 0) {
//            System.out.println("Clase 1");
//        } else {
//            System.out.println("Clase 2");
//        }
////        System.out.println("Point (15:30) is of class "+calculateOutput(p.theta, p.weights, 15, 30));
////        System.out.println("Point (-15:0) is of class "+calculateOutput(p.theta, p.weights, -15, 0));
////        
////        Graph.displayView(p.weights[0], p.weights[1]);

        p.initPerceptron(600);
//        p.initPerceptron(100);
        CallTrainForFilesInFolder trainer = new CallTrainForFilesInFolder();
        trainer.train3("c:/_data/class1b", p, 0);
        trainer.train3("c:/_data/class2b", p, 1);
//        trainer.train3("c:/_data/class1c", p, 0);
//        trainer.train3("c:/_data/class2c", p, 1);

//        int classType = trainer.test3("c:/_data/test1_class1.txt", p, p.theta);
        int classType = trainer.test3("c:/_data/test_class1b.txt", p, p.theta);
//        int classType = trainer.test3("c:/_data/test_c_class1.txt", p, p.theta);
        System.out.println("Clase = "+(classType==0?1:2));
//        classType = trainer.test3("c:/_data/test1_class2.txt", p, p.theta);
        classType = trainer.test3("c:/_data/test_class2b.txt", p, p.theta);
//        classType = trainer.test3("c:/_data/test_c_class2.txt", p, p.theta);
        System.out.println("Clase = "+(classType==0?1:2));
        
    }
    
    private void initPerceptron(int numberOfFeatures){
        this.learningRate = 0.1;
        this.theta = 1;
        this.weights = new double[numberOfFeatures+1];
        for (int i = 0; i < numberOfFeatures + 1; i++) {
            this.weights[i] = Utils.randomNumber(0, 1);
        }
    }
    
    public List train (List<List> data, int dataClass) {
        List calculatedWeights = new ArrayList();
        
        int epochs = 0;
        int output = 0;
        double globalError = 0;
        double localError;
        do {
            epochs++;
            globalError = 0;
            for (int i = 0; i < data.size(); i++) {
                // calculate predicted class
                output = calculateOutput(theta,weights, data.get(i));
                // difference between predicted and actual class values
                localError = dataClass - output;
                //update weights and bias
                weights[0] += learningRate * localError;
                for (int a = 1; a < weights.length; a++) {
                    weights[a] += learningRate * localError * (double)data.get(i).get(a-1);
                }

                //summation of squared error (error value for all instances)
                globalError += (localError*localError);
                
//                for (int a = 0; a < weights.length; a++) {
//                    System.out.print("w["+a+"] = "+weights[a]+", ");
//                }
                System.out.println("global Error = "+globalError);
//                calculatedWeights.add(weights);
            }
        } while (globalError != 0 && epochs < this.epochLimit);
        for (int a = 0; a < weights.length; a++) {
            System.out.print("w["+a+"] = "+weights[a]+", ");
        }
        System.out.println("epochs = "+epochs);
        
        return calculatedWeights;
    }
    
    public List train2 (List<String> data, int dataClass) {
        List calculatedWeights = new ArrayList();
        
        int epochs = 0;
        int output = 0;
        double globalError = 0;
        double localError;
        do {
            epochs++;
            globalError = 0;
            // calculate predicted class
            output = calculateOutput2(theta,weights, data);
            // difference between predicted and actual class values
            localError = dataClass - output;
            //update weights and bias
            weights[0] += learningRate * localError;
            int wi = 1;
            String line = null;
            for (int j = 0; j < data.size(); j++) {
                line = data.get(j);
                for (int i = 0; i < line.length(); i++) {
                    weights[wi] += learningRate * localError * ((line.charAt(i) == '1') ? 1 : 0);
                }
            }
            //summation of squared error (error value for all instances)
            globalError += (localError*localError);

            for (int a = 0; a < weights.length; a++) {
                System.out.print("w["+a+"] = "+weights[a]+", ");
            }
            System.out.println("global Error = "+globalError);
//                calculatedWeights.add(weights);
        } while (globalError != 0 && epochs < this.epochLimit);
        System.out.println("epochs = "+epochs);
        
        return calculatedWeights;
    }
    
    public List train3 (String data, int dataClass) {
        List calculatedWeights = new ArrayList();
        
        int epochs = 0;
        int output;
        char[] chars;
        double globalError = 0;
        double localError;
        do {
            epochs++;
            globalError = 0;
            // calculate predicted class
            chars = data.toCharArray();
            output = calculateOutput3(theta,weights, chars);
            // difference between predicted and actual class values
            localError = dataClass - output;
            //update bias (w[0]) and weights
            weights[0] += learningRate * localError;
            for (int i = 0, w = 1; w < weights.length; i++, w++) {
                weights[w] += learningRate * localError * Integer.parseInt(""+chars[i]);
            }

            //summation of squared error (error value for all instances)
            globalError += (localError*localError);

//                for (int a = 0; a < weights.length; a++) {
//                    System.out.print("w["+a+"] = "+weights[a]+", ");
//                }
            System.out.println("Class = "+dataClass+", epochs = "+epochs+", global Error = "+globalError);
//                calculatedWeights.add(weights);
        } while (globalError != 0 && epochs < this.epochLimit);
//        for (int a = 0; a < weights.length; a++) {
//            System.out.print("w["+a+"] = "+weights[a]+", ");
//        }
//        System.out.println("epochs = "+epochs);
        
        return calculatedWeights;
    }
    
    public Float[] run (int n) {
        Float[] weigths = new Float[3];
        
        int error = 0;
        int w = 0;
        Boolean done = Boolean.FALSE;
        while (!done) {
            done = Boolean.TRUE;
            for (int j = 1; j < n; j++) {
                //y^i - Pw(x^i)
                error = 0;
                if (error != 0) {
                    done = Boolean.FALSE;
                    //w + step * error * x^i
                    w = 0;
                }
            }
        }
        
        return weigths;
    }

        /**
     * returns either 1 or 0 using a threshold function
     * theta is 0range
     * @param theta an integer value for the threshold
     * @param weights[] the array of weights
     * @param x the x input value
     * @param y the y input value
     * @return 1 or 0
     */ 
    static int calculateOutput(double theta, double weights[], double x, double y)
    {
        // Bias is the last value of the wights array
            double sum = x * weights[0] + y * weights[1]+ weights[2];
            return (sum >= theta) ? 1 : 0;
    }
    
    public static int calculateOutput(double theta, double weights[], List<Double> values) {
            double sum = weights[0];
            for (int i = 1; i < weights.length; i++) {
                sum += values.get(i-1) * weights[i];
            }
            return (sum >= theta) ? 1 : 0;
    }

    public static int calculateOutput2(double theta, double weights[], List<String> values) {
            double sum = weights[0];
            int wi = 1;
            String line = null;
            for (int j = 0; j < values.size(); j++) {
                line = values.get(j);
                for (int i = 0; i < line.length(); i++) {
                    sum += ((line.charAt(i) == '1') ? 1 : 0) * weights[wi++];
                }
            }
            return (sum >= theta) ? 1 : 0;
    }

    public static int calculateOutput3(double theta, double weights[], char[] data) {
            double sum = weights[0];
//            System.out.println("data.length = "+data.length);
            for (int i = 0, w = 1; w < weights.length; i++, w++) {
                sum += Integer.parseInt(""+data[i]) * weights[w];
            }
            return (sum >= theta) ? 1 : 0;
    }

}
