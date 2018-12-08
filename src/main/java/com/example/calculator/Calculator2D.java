package com.example.calculator;

import java.util.function.BiFunction;

import com.example.helper.LinearEquationsSolver;
import com.example.point.Point;

public class Calculator2D extends GenericCalculator {

	private Point[] xyPoints;
	private Point[] ksietaPoints;

	private double[] xConstants;
	private double[] yConstants;

	public Calculator2D(Point[] xyPoints, Point[] ksietaPoints, String functionName, double e) {
		super(functionName, e);

		this.xyPoints = xyPoints;
		this.ksietaPoints = ksietaPoints;
	}

	public BiFunction<Point, Point, Double> radius = (p1, p2) -> {
		double[] beginCoordinates = p1.getCoordinates();
		double[] endCoordinates = p2.getCoordinates();

		return Math.sqrt(Math.pow(beginCoordinates[0] - endCoordinates[0], 2)
				+ Math.pow(beginCoordinates[1] - endCoordinates[1], 2));
	};

	public void calculateXYConstants() {
		int size = xyPoints.length;
		double[] xCoordinates = new double[size];
		double[] yCoordinates = new double[size];

		for (int i = 0; i < size; i++) {
			double[] coordinates = xyPoints[i].getCoordinates();
			xCoordinates[i] = coordinates[0];
			yCoordinates[i] = coordinates[1];
		}

		xConstants = calculateConstants(xCoordinates);
		yConstants = calculateConstants(yCoordinates);
	}

	protected double[] calculateConstants(double[] coordinatesToOperate) {
		int numberOfLines = xyPoints.length;
		double[][] fi = new double[numberOfLines][numberOfLines];
		double[] constants = new double[numberOfLines];

		/* Calculating fi[][] using radial function */
		BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Double>> radialFunction = radialFunctionsMap
				.get(functionName);

		for (int i = 0; i < numberOfLines; i++) {
			for (int j = 0; j < numberOfLines; j++) {
				fi[i][j] = radialFunction.apply(ksietaPoints[i], ksietaPoints[j]).apply(radius, e);
				System.out.print(fi[i][j] + " ");
			}
			System.out.println();
		}

		/* Algorithm go ahead */
		constants = LinearEquationsSolver.solve(coordinatesToOperate.clone(), fi);

		return constants;
	}

	public double approximateCoordinate(Point pointToApproximate, double[] constants) {
		double sum = 0;
		double fi = 0;

		for (int i = 0; i < constants.length; i++) {
			System.out.println("Constant(" + i + "): " + constants[i]);

			BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Double>> radialFunction = radialFunctionsMap
					.get(functionName);
			fi = radialFunction.apply(pointToApproximate, ksietaPoints[i]).apply(radius, e);
			sum += constants[i] * fi;
		}

		System.out.println("Sum : " + sum);

		return sum;
	}

	public Point approximate(Point point) {

		if (xConstants == null || yConstants == null) {
			calculateXYConstants();
		}

		double xCoordinate = approximateCoordinate(point, xConstants);
		double yCoordinate = approximateCoordinate(point, yConstants);

		return new Point(new double[] { xCoordinate, yCoordinate });
	}
}
