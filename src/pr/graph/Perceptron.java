/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.graph;

import pr.util.Utils;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public class Perceptron {
  //[full] The Perceptron stores its weights and learning constants.
  float[] weights;
  float c = 0.01f;
  //[end]

  Perceptron(int n) {
    weights = new float[n];
    //[full] Weights start off random.
    for (int i = 0; i < weights.length; i++) {
      weights[i] = (float)Utils.randomNumber(-1,1);
    }
    //[end]
  }

  //[full] Return an output based on inputs.
  int feedforward(float[] inputs) {
    float sum = 0;
    for (int i = 0; i < weights.length; i++) {
      sum += inputs[i]*weights[i];
    }
    return activate(sum);
  }
  //[end]

  //[full] Output is a +1 or -1.
  int activate(float sum) {
    if (sum > 0) return 1;
    else return -1;
  }
  //[end]

  //[full] Train the network against known data.
  void train(float[] inputs, int desired) {
    int guess = feedforward(inputs);
    float error = desired - guess;
    for (int i = 0; i < weights.length; i++) {
      weights[i] += c * error * inputs[i];
    }
  }
  //[end]
}