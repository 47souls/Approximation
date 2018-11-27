package com.example.structures;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.example.calculator.Calculator2D;
import com.example.calculator.FunctionHelper;
import com.example.calculator.GenericCalculator;
import com.example.figures.ConvexPolygon;
import com.example.point.Point;

/**
 * Class represents generic reflection manipulation utility
 * 
 * Goal is to be able to reflect any kind of dimension figure to any kind of
 * another figure with same dimension
 * 
 * 
 */
public class ReflectionManipulator {

	public static void reflectNet(ConvexPolygon xyFigure, ConvexPolygon ksiEtaFigure) {

		// TODO
		// 1. Get xyPoints from 1 figure and get ksiEtaPoints from 2 figure
		// to put them into calculator
		// 2. How to pass radial function name here?
		// 3. Default e? Or provide too

		// Here we are getting point and should check if their length is same
		// so that each set of 2 figures has same amount of points
		Point[] xyPoints = xyFigure.getGivenPoints();
		Point[] ksietaPoints = ksiEtaFigure.getGivenPoints();

		adjustPointsIfNecessary(xyPoints, ksietaPoints);

		String functionName = GenericCalculator.MULTI_QUADRO;
		double e = 0.1;

		Calculator2D calculator2d = new Calculator2D(xyPoints, ksietaPoints, functionName, e);

		Point[][] ksiEtaNetPoints = ksiEtaFigure.getNetPoints();

		int rows = ksiEtaNetPoints.length;
		int columns = ksiEtaNetPoints[0].length;

		Point[][] xyNetPoints = new Point[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				xyNetPoints[i][j] = calculator2d.approximate(ksiEtaNetPoints[i][j]);
			}
		}

		xyFigure.setNetPoints(xyNetPoints);
	}

	private static void adjustPointsIfNecessary(Point[] points1, Point[] points2) {
		int points1Length = points1.length;
		int points2Length  = points2.length;
		Random randomValue = new Random();
		
		if (points1 != null && points2 != null && points1Length != 0
				&& points2Length != 0) {
			Point[] greaterArray;
			Point[] lowerArray;
			
			if (points1Length < points2Length) {
				greaterArray = points2;
				lowerArray = points1;
			} else if (points1Length > points2Length) {
				greaterArray = points1;
				lowerArray = points2;
			} else return;
			
			int difference = greaterArray.length - lowerArray.length;
			List<Point> adjustedGivenPointsForLowerArray = new ArrayList<>();
			adjustedGivenPointsForLowerArray.addAll(Arrays.asList(lowerArray));
			
			while (difference > 0) {				
				int point1Index = randomValue.nextInt(lowerArray.length);
				Point point1 = lowerArray[point1Index];
				
				int point2Index = randomValue.nextInt(lowerArray.length);
				
				while (point2Index == point1Index) {
					point2Index = randomValue.nextInt(lowerArray.length);
				}
				
				Point point2 = lowerArray[point2Index];
						
				// Generate point between them using line equation
				double middleX = (point1.getCoordinates()[0] + point2.getCoordinates()[0]) / 2;
				Point betweenThem = FunctionHelper.getPointFunction.apply(point1, point2).apply(middleX);
				
				adjustedGivenPointsForLowerArray.add(betweenThem);
				
				difference--;
			}
			
			// 3. Set adjusted points
			figure1.setGivenPoints(adjustedGivenPointsForLowerArray.toArray(new Point[adjustedGivenPointsForLowerArray.size()]));
		}

	}
}
