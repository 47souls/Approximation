package com.example.structures;

import com.example.calculator.FunctionHelper;
import com.example.point.Point;

public class RectangleManipulator {
	
	public static void fillNet(Rectangle rectangle, double xAxisStep, double yAxisStep) {
		Point a = rectangle.getA();
		Point b = rectangle.getB();
		Point c = rectangle.getC();
		
		double xDistance = FunctionHelper.twoPointsDistanceFunction.apply(a, b);
		int xMarksNumber = (int) (xDistance / xAxisStep);
		
		double yDistance = FunctionHelper.twoPointsDistanceFunction.apply(b, c);
		int yMarksNumber = (int) (yDistance / yAxisStep);
		
		// Creating net points
		double[] aCoordinates = a.getCoordinates();
		double[] cCoordinates = c.getCoordinates();
		
		Point[][] netPoints = new Point[yMarksNumber + 1][xMarksNumber + 1];

		for (int i = 0; i <= yMarksNumber; i++) {
			for (int j = 0; j <= xMarksNumber; j++) {
				netPoints[i][j] = new Point(new double[] {
					aCoordinates[0] + xAxisStep * j,
					cCoordinates[1] - yAxisStep * i
				});
			}
		}
		
		rectangle.setNetPoints(netPoints);
	}
}
