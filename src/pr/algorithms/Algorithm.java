/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.algorithms;

import java.util.List;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public interface Algorithm {
    
    public void train(List<Integer> classes, List<String> data);
//    public String test (String clase1, String clase2, String data);
    public int test(String data);
    public boolean isTrained();
    public String getWeights();
    public String getWeightsLog();
    
}
