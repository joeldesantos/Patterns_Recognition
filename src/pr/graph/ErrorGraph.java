/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

/**
 *
 * @author Joel de Santos <joeldesantos@gmail.com>
 */
public class ErrorGraph extends ApplicationFrame {
    
//    private doube min = 0;
//    private doube max = 0;

    public ErrorGraph(String title, List<Double> errorLog) {
        super(title);
        JPanel jpanel = createDemoPanel(errorLog);
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private static CategoryDataset createDataset(List<Double> errorLog) {
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        for (int i = 0; i < errorLog.size(); i++) {
            defaultcategorydataset.addValue(errorLog.get(i), "Global error", "Epoch");
        }
        return defaultcategorydataset;
    }

    private static JFreeChart createChart(CategoryDataset categorydataset) {
        JFreeChart jfreechart = ChartFactory.createLineChart("Error Graph", null, "Error Graph", categorydataset, PlotOrientation.VERTICAL, false, true, false);
//        jfreechart.addSubtitle(new TextTitle("Number of Classes By Release"));
//        TextTitle texttitle = new TextTitle("Source: Java In A Nutshell (5th Edition) by David Flanagan (O'Reilly)");
//        texttitle.setFont(new Font("SansSerif", 0, 10));
//        texttitle.setPosition(RectangleEdge.BOTTOM);
//        texttitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
//        jfreechart.addSubtitle(texttitle);
        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
        categoryplot.setRangePannable(true);
        categoryplot.setRangeGridlinesVisible(false);
//        java.net.URL url = (ErrorGraph.class).getClassLoader().getResource("OnBridge11small.png");
//        if (url != null) {
//            ImageIcon imageicon = new ImageIcon(url);
//            jfreechart.setBackgroundImage(imageicon.getImage());
//            categoryplot.setBackgroundPaint(null);
//        }
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartUtilities.applyCurrentTheme(jfreechart);
        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();
        lineandshaperenderer.setBaseShapesVisible(true);
        lineandshaperenderer.setDrawOutlines(true);
        lineandshaperenderer.setUseFillPaint(true);
        lineandshaperenderer.setBaseFillPaint(Color.white);
        lineandshaperenderer.setSeriesStroke(0, new BasicStroke(3F));
        lineandshaperenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
        lineandshaperenderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5D, -5D, 10D, 10D));
        return jfreechart;
    }

    public static JPanel createDemoPanel(List<Double> errorLog) {
        JFreeChart jfreechart = createChart(createDataset(errorLog));
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setMouseWheelEnabled(true);
        return chartpanel;
    }
}
