package com.example.helper;

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
public class ReflectionHelper {

	public static void reflectNet(ConvexPolygon xyFigure, ConvexPolygon ksiEtaFigure) {
		Point[] xyPoints = xyFigure.getEdgePoints();
		Point[] ksietaPoints = ksiEtaFigure.getEdgePoints();

		String functionName = GenericCalculator.MULTI_QUADRO_FUNCTION_NAME;
		double e = 0.1;

		Calculator2D calculator2d = new Calculator2D(xyPoints, ksietaPoints, functionName, e);

		Point[][] ksiEtaNetPoints = ksiEtaFigure.getNetPoints();

		Point[][] xyNetPoints = reflectTwoDimensionalArray(calculator2d, ksiEtaNetPoints);

		xyFigure.setNetPoints(xyNetPoints);
	}
	
	public static Point[] reflectOneDimensionalArray(Calculator2D calculator, Point[] oneDimensionalArray) {
		int size = oneDimensionalArray.length;

		Point[] reflectedOneDimensionalArray = new Point[size];

		for (int i = 0; i < size; i++) {
			reflectedOneDimensionalArray[i] = calculator.approximate(oneDimensionalArray[i]);
		}
		
		return reflectedOneDimensionalArray;
	}
	
	public static Point[][] reflectTwoDimensionalArray(Calculator2D calculator, Point[][] twoDimensionalArray) {
		int rows = twoDimensionalArray.length;
		int columns = twoDimensionalArray[0].length;

		Point[][] reflectedTwoDimensionalArray = new Point[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				reflectedTwoDimensionalArray[i][j] = calculator.approximate(twoDimensionalArray[i][j]);
			}
		}
		
		return reflectedTwoDimensionalArray;
	}
	
	
}
