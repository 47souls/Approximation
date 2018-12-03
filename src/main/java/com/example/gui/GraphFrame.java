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

	public GraphFrame(String title, ConvexPolygon figure, String bottomAxisName, String topAxisName)
			throws HeadlessException {
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

		// Adding figure borders to the plot
		Point[] edgePoints = figure.getEdgePoints();
		int length = edgePoints.length;
		double[] xCoordinates = new double[length];
		double[] yCoordinates = new double[length];

		for (int i = 0; i < length; i++) {
			xCoordinates[i] = edgePoints[i].getCoordinates()[0];
			yCoordinates[i] = edgePoints[i].getCoordinates()[1];
		}

		Chart chart = new ChartBuilder()
				.chartType(ChartType.Line)
				.width(600)
				.height(400)
				.title("Line Chart")
				.xAxisTitle(bottomAxisName)
				.yAxisTitle(topAxisName)
				.build();
		
		chart.getStyleManager();
		chart.addSeries(figure.getClass().getSimpleName(), xCoordinates, yCoordinates);

		// Adding figure net points to the plot
		Point[][] netPoints = figure.getNetPoints();
		int rows = netPoints.length;
		int columns = netPoints[0].length;

		// drawing horizontal lines on net
		for (int i = 0; i < rows; i++) {
			double[] xCoordinatesHorizontalNet = new double[columns];
			double[] yCoordinatesHorizontalNet = new double[columns];

			for (int j = 0; j < columns; j++) {
				xCoordinatesHorizontalNet[j] = netPoints[i][j].getCoordinates()[0];
				yCoordinatesHorizontalNet[j] = netPoints[i][j].getCoordinates()[1];
			}

			// TODO:
			// 1) How to add series of same color???
			// 2) How to remove series name from right part of screen
			chart.addSeries(i + " ", xCoordinatesHorizontalNet, yCoordinatesHorizontalNet);
		}

		// drawing vertical lines on net
		for (int j = 0; j < columns; j++) {
			double[] xCoordinatesVerticalNet = new double[rows];
			double[] yCoordinatesVerticalNet = new double[rows];

			for (int i = 0; i < rows; i++) {
				xCoordinatesVerticalNet[i] = netPoints[i][j].getCoordinates()[0];
				yCoordinatesVerticalNet[i] = netPoints[i][j].getCoordinates()[1];
			}

			// TODO:
			// 1) How to add series of same color???
			// 2) How to remove series name from right part of screen
			chart.addSeries(System.currentTimeMillis() + j + " ", xCoordinatesVerticalNet, yCoordinatesVerticalNet);
		}

		return new XChartPanel(chart);
	}

}
