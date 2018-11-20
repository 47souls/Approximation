package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.calculator.Calculator1D;
import com.example.point.Point;

public class App {
	public static void main(String[] args) {
		
		// 1D case
		List<Double> xValues = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			xValues.add(i * 0.2);
		}

		List<Double> yValues = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			yValues.add(Math.sin(i * 0.2));
		}
		
		Calculator1D calculator = Calculator1D.create1DCalculator(xValues, yValues, 0, 0.1);
		calculator.calculateConstants1D();
		
		//
		calculator.approximate1D(0.3);
		
		// 2D case
		List<Point> xyPoints = new ArrayList<>();
		xyPoints.add(new Point(new double[] {1.0, 1.0}));
		xyPoints.add(new Point(new double[] {2.0, 1.0}));
		xyPoints.add(new Point(new double[] {3.0, 1.0}));
		xyPoints.add(new Point(new double[] {3.0, 2.0}));
		xyPoints.add(new Point(new double[] {3.0, 3.0}));
		xyPoints.add(new Point(new double[] {2.0, 3.0}));
		xyPoints.add(new Point(new double[] {1.0, 3.0}));
		xyPoints.add(new Point(new double[] {1.0, 2.0}));

		List<Point> ksietaPoints = new ArrayList<>();
		ksietaPoints.add(new Point(new double[] {1.0, 1.0}));
		ksietaPoints.add(new Point(new double[] {2.0, 1.0}));
		ksietaPoints.add(new Point(new double[] {3.0, 1.0}));
		ksietaPoints.add(new Point(new double[] {4.0, 1.0}));
		ksietaPoints.add(new Point(new double[] {2.0, 3.0}));
		ksietaPoints.add(new Point(new double[] {1.0, 4.0}));
		ksietaPoints.add(new Point(new double[] {1.0, 3.0}));
		ksietaPoints.add(new Point(new double[] {1.0, 2.0}));
		
		Calculator1D calculator = Calculator1D.create2DCalculator(xyPoints, ksietaPoints, 0, 0.1);
		calculator.calculateConstants2D();
		
		//
		calculator.approximate2D(new Point(new double[] {2.0, 2.0}));
	}
}
