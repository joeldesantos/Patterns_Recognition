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
public class Starter {
    // The Perceptron
    Perceptron ptron;
// 2,000 training points
    Trainer[] training = new Trainer[2000];
    int count = 0;

    public static void main(String[] args) {
    }

//[full] The formula for a line
    public static float f(float x) {
        return 2 * x + 1;
    }
//[end]

    public static void setup() {
//        size(640, 360);
//
//        ptron = new Perceptron(3);
//
//        // Make 2,000 training points.
//        for (int i = 0; i < training.length; i++) {
//            float x = random(-width / 2, width / 2);
//            float y = random(-height / 2, height / 2);
//            //[full] Is the correct answer 1 or -1?
//            int answer = 1;
//            if (y < f(x)) {
//                answer = -1;
//            }
//            //[end]
//            training[i] = new Trainer(x, y, answer);
//        }
    }

    public static void draw() {
//        background(255);
//        translate(width / 2, height / 2);
//
//        ptron.train(training[count].inputs, training[count].answer);
//        // For animation, we are training one point at a time.
//        count = (count + 1) % training.length;
//
//        for (int i = 0; i < count; i++) {
//            stroke(0);
//            int guess = ptron.feedforward(training[i].inputs);
//            //[full] Show the classification—no fill for -1, black for +1.
//            if (guess > 0) {
//                noFill();
//            } else {
//                fill(0);
//            }
//            //[end]
//            ellipse(training[i].inputs[0], training[i].inputs[1], 8, 8);
//        }
    }
}
