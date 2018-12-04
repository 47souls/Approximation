package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.example.figures.ConvexPolygon;
import com.example.point.Point;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.ChartBuilder;
import com.xeiam.xchart.Series;
import com.xeiam.xchart.SeriesMarker;
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

		// Adding figure edge points to the plot
		Point[] edgePoints = figure.getEdgePoints();
		int length = edgePoints.length;
		double[] xCoordinates = new double[length + 1];
		double[] yCoordinates = new double[length + 1];

		for (int i = 0; i < length; i++) {
			xCoordinates[i] = edgePoints[i].getCoordinates()[0];
			yCoordinates[i] = edgePoints[i].getCoordinates()[1];
		}
		
		xCoordinates[length] = edgePoints[0].getCoordinates()[0];
		yCoordinates[length] = edgePoints[0].getCoordinates()[1];
		
		Chart chart = new ChartBuilder()
				.chartType(ChartType.Line)
				.width(600)
				.height(400)
				.title("Line Chart")
				.xAxisTitle(bottomAxisName)
				.yAxisTitle(topAxisName)
				.build();
				
		Series series = chart.addSeries(figure.getClass().getSimpleName(), xCoordinates, yCoordinates);
		series.setLineColor(Color.BLACK);
		
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
			// 1) How to remove series name from right part of screen
			Series horizontalSeries = chart.addSeries(i + " ", xCoordinatesHorizontalNet, yCoordinatesHorizontalNet);
			horizontalSeries.setLineColor(Color.decode("#C16200"));
			horizontalSeries.setMarker(SeriesMarker.TRIANGLE_UP);
			horizontalSeries.setMarkerColor(Color.decode("#87C45C"));
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
			// 2) How to remove series name from right part of screen.
			Series verticalSeries = chart.addSeries(System.currentTimeMillis() + j + " ", xCoordinatesVerticalNet, yCoordinatesVerticalNet);
			verticalSeries.setLineColor(Color.decode("#C16200"));
			verticalSeries.setMarker(SeriesMarker.TRIANGLE_UP);
			verticalSeries.setMarkerColor(Color.decode("#87C45C"));
		}

		return new XChartPanel(chart);
	}

}
