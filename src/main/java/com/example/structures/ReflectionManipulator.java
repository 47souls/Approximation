package com.example.structures;

import com.example.calculator.Calculator2D;
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
		Point[] xyPoints = xyFigure.getEdgePoints();
		Point[] ksietaPoints = ksiEtaFigure.getEdgePoints();

		String functionName = GenericCalculator.MULTI_QUADRO_FUNCTION_NAME;
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
}
