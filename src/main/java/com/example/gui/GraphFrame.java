package com.example.gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.example.figures.ConvexPolygon;
import com.example.point.Point;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.ChartBuilder;
import com.xeiam.xchart.StyleManager.ChartType;
import com.xeiam.xchart.XChartPanel;

@SuppressWarnings("serial")
public class GraphFrame extends JFrame {

	public GraphFrame(String title, ConvexPolygon figure, String bottomAxisName, String topAxisName) throws HeadlessException {
		super(title);
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// chart
	    JPanel chartPanel = initGraphPanel(figure, bottomAxisName, topAxisName);
	    add(chartPanel, BorderLayout.CENTER);
	    
	    pack();
	    setVisible(true);
	}
	
	private JPanel initGraphPanel(ConvexPolygon figure, String bottomAxisName, String topAxisName) {
		Point[] givenPoints = figure.getGivenPoints();
		int length = givenPoints.length;
		double[] xCoordinates = new double[length];
		double[] yCoordinates = new double[length];
		
		for (int i = 0; i < length; i++) {
			xCoordinates[i] = givenPoints[i].getCoordinates()[0];
			yCoordinates[i] = givenPoints[i].getCoordinates()[1];
		}
		
		Chart chart = new ChartBuilder().chartType(ChartType.Line).width(600).height(400).title("Line Chart").xAxisTitle(bottomAxisName).yAxisTitle(topAxisName).build();
		chart.addSeries(figure.getClass().getSimpleName(), xCoordinates, yCoordinates);


		return new XChartPanel(chart);
	}

}
