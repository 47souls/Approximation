package com.example.calculator;

import java.util.List;
import java.util.function.BiFunction;

import com.example.point.Point;

public class Calculator2D extends GenericCalculator {

	private List<Point> xyPoints;
	private List<Point> ksietaPoints;
	
	
	private double[] xConstants;
	private double[] yConstants;

	public Calculator2D(List<Point> xyPoints, List<Point> ksietaPoints, String functionName, double e) {
		super(functionName, e);

		this.xyPoints = xyPoints;
		this.ksietaPoints = ksietaPoints;
	}
	
	public double radius(Point begin, Point end) {
		double[] beginCoordinates = begin.getCoordinates();
		double[] endCoordinates = end.getCoordinates();
		
		return Math.sqrt(Math.pow(beginCoordinates[0] - endCoordinates[0], 2) + Math.pow(beginCoordinates[1] - endCoordinates[1], 2));
	}

	private void calculateXYConstants() {
		int size = xyPoints.size();
		double[] xCoordinates = new double[size];
		double[] yCoordinates = new double[size];
		
		for (int i = 0; i < xyPoints.size(); i++) {
			double[] coordinates = xyPoints.get(i).getCoordinates();
			xCoordinates[i] = coordinates[0];
			yCoordinates[i] = coordinates[1];		
		}
		
		xConstants = calculateConstants(xCoordinates);
		yConstants = calculateConstants(yCoordinates);
	}
	
	protected double[] calculateConstants(double[] coordinatesToOperate) {
		int counter = 0;
		int numberOfLines = xyPoints.size();
		double[][] fi = new double[numberOfLines][numberOfLines];
		double[] constants = new double[numberOfLines];
		
		/* Calculating fi[][] using radial function */
		BiFunction<Double, Double, Double> radialFunction = radialFunctionsMap.get(functionName);
		
		for (int i = 0; i < numberOfLines; i++) {
			for (int j = 0; j < numberOfLines; j++) {
				fi[i][j] = radialFunction.apply(radius(ksietaPoints.get(i), ksietaPoints.get(j)), e);
				System.out.print(fi[i][j] + " ");
			}
			System.out.println();
		}
		
		/* Algorithm go ahead */
		while (counter < numberOfLines) {
			double temp = fi[counter][counter];
			double temp3 = coordinatesToOperate[counter];
			for (int i = counter; i < numberOfLines - 1; i++) {
				double temp2 = fi[i + 1][counter];
				for (int j = counter; j < numberOfLines - 1; j++) {
					fi[i + 1][j + 1] = fi[i + 1][j + 1] - fi[counter][j + 1] * temp2 / temp;
				}
				coordinatesToOperate[i + 1] = coordinatesToOperate[i + 1] - temp3 * temp2 / temp;
			}
			coordinatesToOperate[counter] = coordinatesToOperate[counter] / temp;
			for (int i = counter; i < numberOfLines; i++) {
				fi[counter][i] = fi[counter][i] / temp;
			}
			for (int i = counter + 1; i < numberOfLines; i++) {
				fi[i][counter] = 0;
			}
			counter++;
		}
		
		/* Algorithm go behind */
		constants[numberOfLines - 1] = coordinatesToOperate[numberOfLines - 1];
		
		for (int i = numberOfLines - 2; i >= 0; i--) {
			double temp = 0;
			for (int j = i + 1; j < numberOfLines; j++) {
				temp += fi[i][j] * constants[j];
			}
			constants[i] = coordinatesToOperate[i] - temp;
		}
	
		return constants;
	}
	
	public double approximateCoordinate(Point pointToApproximate, double[] constants) {
		double sum = 0;
		double fi = 0;
		
		for (int i = 0; i < constants.length; i++) {
			System.out.println("Constant(" + i + "): " + constants[i]);
			
			// Determining distance from Point(ksi*, eta*) to approximate to each defined point (ksi, eta)
			double radius = radius(pointToApproximate, ksietaPoints.get(i));
			
			BiFunction<Double, Double, Double> radialFunction = radialFunctionsMap.get(functionName);
			fi = radialFunction.apply(radius, e);
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
		
		return new Point(new double[] {xCoordinate, yCoordinate});
	}
}
