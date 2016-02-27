/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.algorithms;

import java.util.ArrayList;
import java.util.List;
import pr.util.Utils;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public class Adaline implements Algorithm{
    
    private double learningRate;
    private double theta;
    public double[] weights;
    private List<double[]> weightsLog;
    private List<Double> errorLog;
    private int epochLimit;
    private boolean trained = false;

    public Adaline () {
    }   
    
    public void initAdeline(int numberOfFeatures, double learningRate, double theta, int epochLimit){
        this.learningRate = learningRate;
        this.theta = theta;
        this.epochLimit = epochLimit;
        this.weights = new double[numberOfFeatures + 1];
        this.weightsLog = new ArrayList<>();
        this.errorLog = new ArrayList<>();
        for (int i = 0; i < weights.length; i++) {
            this.weights[i] = Utils.randomNumber(0, 1);
        }
    }
 
    /*
    private List train (String data, int dataClass) {
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
            output = calculateOutput(theta,weights, chars);
            // difference between predicted and actual class values
            localError = dataClass - output;
            //update bias (w[0]) and weights
            weights[0] += learningRate * localError;
            for (int i = 0, w = 1; w < weights.length; i++, w++) {
                int x = Integer.parseInt(""+chars[i]);
                weights[w] += learningRate * localError * (sigmoid(x)* (1 - sigmoid(x))) * x;
            }

            //summation of squared error (error value for all instances)
            globalError += (localError*localError);

            System.out.println("Class = "+dataClass+", epochs = "+epochs+", global Error = "+globalError);

            weightsLog.add(weights);
            errorLog.add(globalError);
        } while (epochs < this.epochLimit);

        this.trained = true;
        
        return calculatedWeights;
    }
    */
    
    private double train (String data, int dataClass) {
        int output;
        char[] chars;
        double localError;
        // calculate predicted class
        chars = data.toCharArray();
        output = calculateOutput(theta,weights, chars);
        // difference between predicted and actual class values
        localError = dataClass - output;
        //update bias (w[0]) and weights
        weights[0] += learningRate * localError;
        for (int i = 0, w = 1; w < weights.length; i++, w++) {
            int x = Integer.parseInt(""+chars[i]);
            weights[w] += learningRate * localError * (sigmoid(x)* (1 - sigmoid(x))) * x;
        }

        this.trained = true;
        
        return localError;
    }

    public static int calculateOutput(double theta, double weights[], char[] data) {
        double sum = weights[0];
//            System.out.println("data.length = "+data.length);
        for (int i = 0, w = 1; w < weights.length; i++, w++) {
            sum += Integer.parseInt(""+data[i]) * weights[w];
        }
        return (sum >= theta) ? 1 : 0;
    }
    
    private double sigmoid (int x) {
        return 1/(1+Math.exp(-x));
    }

    @Override
    public void train(List<Integer> classes, List<String> data) {
        if (classes != null && data != null) {
            if (classes.size() != data.size()) {
                throw new IllegalArgumentException("List sizes does not match.");
            }            
            int epochs = 0;
            double globalError = 0;
            double localError = 0;
            do {
                epochs++;
                globalError = 0;
                for (int i = 0; i < data.size(); i ++) {
                    localError = train(data.get(i), classes.get(i));
                    
                    //summation of squared error (error value for all instances)
                    globalError += (localError*localError);
                }

                System.out.println("epochs = "+epochs+", global Error = "+globalError);

                weightsLog.add(weights);
                errorLog.add(globalError);
            } while (epochs < this.epochLimit);

        }
    }

    @Override
    public int test(String data) {
//        Logger.getLogger(PerceptronV2.class.getName()).log(Level.FINEST, "data = "+data);
        System.out.println("data = "+data);
        return calculateOutput(theta, weights, data.toCharArray());
    }

    @Override
    public boolean isTrained() {
        return this.trained;
    }

    @Override
    public String getWeights() {
        String out = Utils.getWeightsHeader(weights.length);
        out += Utils.getWeights(weights);
        
        return out;
    }

    @Override
    public String getWeightsLog() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(Utils.getWeightsHeader(weights.length));
        
        for (double[] w : weightsLog) {
            sb.append(Utils.getWeights(w));
        }
        
        return sb.toString();
    }

    @Override
    public List<Double> getErrorLog() {
        return this.errorLog;
    }

    @Override
    public double getBias() {
        return weights[0];
    }

}
