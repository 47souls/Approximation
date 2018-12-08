package com.example.calculator;

import java.util.function.BiFunction;

import com.example.helper.LinearEquationsSolver;
import com.example.point.Point;

/**
 * Covers approximation of graphics on XoY graph
 * 
 */
public class Calculator1D extends GenericCalculator {

	private Point[] xyPoints;
	private double[] xCoordinates;
	private double[] yCoordinates;

	private double[] constants;

	public Calculator1D(Point[] xyPoints, String functionName, double e) {
		super(functionName, e);

		this.xyPoints = xyPoints;

		int length = xyPoints.length;
		double[] xCoordinates = new double[length];
		double[] yCoordinates = new double[length];

		for (int i = 0; i < xyPoints.length; i++) {
			xCoordinates[i] = xyPoints[i].getCoordinates()[0];
			yCoordinates[i] = xyPoints[i].getCoordinates()[0];
		}

		this.xCoordinates = xCoordinates;
		this.yCoordinates = yCoordinates;
	}

	public Point[] getXyPoints() {
		return xyPoints;
	}

	public void setXyPoints(Point[] xyPoints) {
		this.xyPoints = xyPoints;
	}

	public double[] getxCoordinates() {
		return xCoordinates;
	}

	public void setxCoordinates(double[] xCoordinates) {
		this.xCoordinates = xCoordinates;
	}

	public double[] getyCoordinates() {
		return yCoordinates;
	}

	public void setyCoordinates(double[] yCoordinates) {
		this.yCoordinates = yCoordinates;
	}

	public BiFunction<Point, Point, Double> radius = (p1, p2) -> {
		double begin = p1.getCoordinates()[0];
		double end = p2.getCoordinates()[0];

		return Math.abs(begin - end);
	};

	public double[] calculateConstants() {
		int numberOfLines = xyPoints.length;
		double[][] fi = new double[numberOfLines][numberOfLines];
		constants = new double[numberOfLines];

		/* Calculating fi[][] using radial function */
		BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Double>> radialFunction = radialFunctionsMap
				.get(functionName);

		for (int i = 0; i < numberOfLines; i++) {
			for (int j = 0; j < numberOfLines; j++) {
				fi[i][j] = radialFunction.apply(xyPoints[i], xyPoints[j]).apply(radius, e);
				System.out.print(fi[i][j] + " ");
			}
			System.out.println();
		}
		constants = LinearEquationsSolver.solve(getxCoordinates().clone(), fi);

		return constants;
	}

	public Point approximate(Point point) {
		double sum = 0;
		double fi = 0;

		if (constants == null) {
			calculateConstants();
		}

		for (int i = 0; i < xyPoints.length; i++) {
			System.out.println("Constant(" + i + "): " + constants[i]);

			Point iPoint = xyPoints[i];

			BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Double>> radialFunction = radialFunctionsMap
					.get(functionName);
			fi = radialFunction.apply(point, iPoint).apply(radius, e);
			sum += constants[i] * fi;
		}

		System.out.println("Sum : " + sum);
		return new Point(new double[] { point.getCoordinates()[0], sum });
	}

}
