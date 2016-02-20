/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.algorithms;

import java.util.ArrayList;
import java.util.List;
import pr.ui.Graph;
import pr.util.LoadCoordinatesFromFile;
import pr.util.Utils;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public class AdelineOld {
    
    private double learningRate;
    private int theta;
    private double[] weights;
    private int epochLimit;

    public AdelineOld (int epochLimit) {
        this.epochLimit = epochLimit;
        initPerceptron(2);
    }   
    
    public AdelineOld (double learningRate, double[] weights, int theta, int epochLimit) {
        this.learningRate = learningRate;
        this.theta = theta;
        this.weights = weights;
        this.epochLimit = epochLimit;
    }
    
    public static void main (String[] args) {
        AdelineOld p = new AdelineOld(10000);
//        LoadCoordinatesFromFile lcff = new LoadCoordinatesFromFile();
        LoadCoordinatesFromFile lcff = new LoadCoordinatesFromFile("c:/_data/simple_class1.txt", "c:/_data/simple_class2.txt");
        List lw1 = p.train(lcff.getClass1(), 0);
        List lw2 = p.train(lcff.getClass2(), 1);
        
//        List<List> testData = lcff.loadValuesFromFile("c:/_data/test1_class1.txt");
        List<List> testData = lcff.loadValuesFromFile("c:/_data/simple_test1_class1.txt");
        int output = 0;
        int accumulated = 0;
        for (int i = 0; i < testData.get(0).size(); i++){
            output = calculateOutput(p.theta, p.weights, (Integer)testData.get(0).get(0), (Integer)testData.get(1).get(0));
            accumulated += output;
        }
        System.out.println("accumulated = " + accumulated);
        System.out.println("size = "+testData.get(0).size());
        if (accumulated > testData.get(0).size() * 0.6) {
            System.out.println("Match");
        }
        System.out.println("Point (15:30) is of class "+calculateOutput(p.theta, p.weights, 15, 30));
        System.out.println("Point (-15:0) is of class "+calculateOutput(p.theta, p.weights, -15, 0));
        
        Graph.displayView(p.weights[0], p.weights[1]);
    }
    
    private void initPerceptron(int numberOfFeatures){
        this.learningRate = 0.1;
        this.theta = 0;
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
            for (int i = 0; i < data.get(0).size(); i++) {
                int x = (Integer)data.get(0).get(i);
                int y = (Integer)data.get(1).get(i);
                // calculate predicted class
                output = calculateOutput(theta,weights, x, y);
                // difference between predicted and actual class values
                localError = dataClass - output;
                //update weights and bias
                weights[0] += learningRate * localError * (sigmoid(x)* (1 - sigmoid(x))) * x;
                weights[1] += learningRate * localError * (sigmoid(y)* (1 - sigmoid(y))) * y;
                weights[2] += learningRate * localError;
                //summation of squared error (error value for all instances)
                globalError += (localError*localError);
                
                for (int a = 0; a < weights.length; a++) {
                    System.out.print("w["+a+"] = "+weights[a]+", ");
                }
                System.out.println("global Error = "+globalError);
//                calculatedWeights.add(weights);
            }
        } while (epochs < this.epochLimit);
        System.out.println("epochs = "+epochs);
        
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
    static int calculateOutput(int theta, double weights[], double x, double y)
    {
            double sum = x * weights[0] + y * weights[1]+ weights[2];
            return (sum >= theta) ? 1 : 0;
    }
    
    private double sigmoid (double x) {
        return 1/(1+Math.exp(-x));
    }
}
