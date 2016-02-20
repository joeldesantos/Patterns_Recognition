/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.pojo;

import java.util.List;
import java.util.Vector;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public class PerceptronData {
    
    private int maxIterations;
    private double tetha;
    private double learningRate;
//    private double[] weights;
    private List<List> weights;
    private List<List> calculatedWeights;
//    private List<double[]> errors;
    private List<List> errors;
    
}
