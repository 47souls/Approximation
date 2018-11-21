package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.example.calculator.Calculator1D;
import com.example.calculator.Calculator2D;
import com.example.calculator.FunctionHelper;
import com.example.point.Point;
import com.example.structures.Rectangle;
import com.example.structures.RectangleManipulator;

public class App {
	public static void main(String[] args) {
		
		// 1D case
//		List<Double> xValues = new ArrayList<>();
//		for (int i = 0; i < 12; i++) {
//			xValues.add(i * 0.2);
//		}
//
//		List<Double> yValues = new ArrayList<>();
//		for (int i = 0; i < 12; i++) {
//			yValues.add(Math.sin(i * 0.2));
//		}
//		
//		Calculator1D calculator = new Calculator1D(xValues, yValues, "multiQuadro", 0.1);
//		
//		//
//		calculator.approximate(0.3);
		
		// 2D case
//		List<Point> xyPoints = new ArrayList<>();
//		xyPoints.add(new Point(new double[] {1.0, 1.0}));
//		xyPoints.add(new Point(new double[] {2.0, 1.0}));
//		xyPoints.add(new Point(new double[] {3.0, 1.0}));
//		xyPoints.add(new Point(new double[] {3.0, 2.0}));
//		xyPoints.add(new Point(new double[] {3.0, 3.0}));
//		xyPoints.add(new Point(new double[] {2.0, 3.0}));
//		xyPoints.add(new Point(new double[] {1.0, 3.0}));
//		xyPoints.add(new Point(new double[] {1.0, 2.0}));
//
//		List<Point> ksietaPoints = new ArrayList<>();
//		ksietaPoints.add(new Point(new double[] {1.0, 1.0}));
//		ksietaPoints.add(new Point(new double[] {2.0, 1.0}));
//		ksietaPoints.add(new Point(new double[] {3.0, 1.0}));
//		ksietaPoints.add(new Point(new double[] {4.0, 1.0}));
//		ksietaPoints.add(new Point(new double[] {2.0, 3.0}));
//		ksietaPoints.add(new Point(new double[] {1.0, 4.0}));
//		ksietaPoints.add(new Point(new double[] {1.0, 3.0}));
//		ksietaPoints.add(new Point(new double[] {1.0, 2.0}));
//		
//		Calculator2D calculator = new Calculator2D(xyPoints, ksietaPoints, "multiQuadro", 0.1);
//		//
//		Point resultOnXY = calculator.approximate(new Point(new double[] {2.0, 2.0}));
//		
//		System.out.println("Resulting point: " + resultOnXY);
		
		Rectangle rectangle = new Rectangle(new Point(new double[] {1.0, 1.0}), new Point(new double[] {4.0, 4.0}));
		RectangleManipulator.fillNet(rectangle, 0.2, 0.3);
		
		System.out.println(rectangle);
	}
}
