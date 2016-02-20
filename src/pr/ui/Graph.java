/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public class Graph extends JPanel {
    
    private static final Color GRAPH_COLOR = Color.green;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    
    public static void main (String[] args) {
        displayView(-0.5659000000000001, 0.10340000000000016);
    }
    
    public static void displayView(double x, double y) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Graph graph = new Graph();
        jf.getContentPane().add(graph);
        jf.pack();
        jf.setSize(500, 500);
        jf.setLocationByPlatform(true);
        jf.setVisible(true);
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        graph.drawEmptyPlane();
        graph.drawSlope(x, y);
    }
    
    public void drawEmptyPlane() {
        Graphics2D gr = (Graphics2D)this.getGraphics();
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawLine((this.getWidth()-1)/2, 1, (this.getWidth()-1)/2, this.getHeight()-1, GRAPH_COLOR, GRAPH_STROKE);
        drawLine(1, (this.getHeight()-1)/2, this.getWidth()-1, (this.getHeight()-1)/2, GRAPH_COLOR, GRAPH_STROKE);
//        Graphics2D gr = (Graphics2D)this.getGraphics();
//        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        Stroke oldStroke = gr.getStroke();
//        gr.setColor(color);
//        gr.setStroke(stroke);
//        gr.drawLine((this.getWidth()-1)/2, 1, (this.getWidth()-1)/2, this.getHeight()-1);
//        gr.drawLine(1, (this.getHeight()-1)/2, this.getWidth()-1, (this.getHeight()-1)/2);
//        gr.setStroke(oldStroke);
    }
    
    public void drawSlope(double x, double y) {
        int width = this.getWidth();
        int height = this.getHeight();
        
        double firstX = 2*x+1;
        double lastX = 2*(x+width)+1;
        
        double firstY = 2*y+1;
        double lastY = 2*(y+height)+1;
        
//        drawLine(width/2, height/2, firstX, firstY, Color.RED, GRAPH_STROKE);
        drawLine(firstX, firstY, width, height, Color.RED, GRAPH_STROKE);
    }
    
    private void drawLine (double x1, double y1, double x2, double y2, Color color, Stroke stroke) {
        Graphics2D gr = (Graphics2D)this.getGraphics();
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Stroke oldStroke = gr.getStroke();
        gr.setColor(color);
        gr.setStroke(stroke);
        gr.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
        gr.setStroke(oldStroke);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        drawEmptyPlane();
    }
}
