/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.graph;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public class Trainer {
 
    //A "Trainer" object stores the inputs and the correct answer.
  float[] inputs;
  int answer;
 
  Trainer(float x, float y, int a) {
    inputs = new float[3];
    inputs[0] = x;
    inputs[1] = y;
    //Note that the Trainer has the bias input built into its array.
    inputs[2] = 1;
    answer = a;
  }
}
