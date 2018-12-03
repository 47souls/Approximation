package com.example;

import javax.swing.SwingUtilities;

import com.example.calculator.Calculator2D;
import com.example.figures.Rectangle;
import com.example.figures.Trapezium;
import com.example.gui.GraphFrame;
import com.example.helper.RectangleHelper;
import com.example.helper.ReflectionHelper;
import com.example.point.Point;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.SwingWrapper;

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
		
//		// 1. Creating figures
//		Rectangle ksiEtaRectangle = new Rectangle(new Point(new double[] {1.0, 1.0}), new Point(new double[] {4.0, 4.0}));
//		Trapezium xyTrapezium = new Trapezium(new Point(new double[] {1.0, 1.0}), new Point(new double[] {4.0, 1.0}), new Point(new double[] {3.0, 3.0}));
//		// 2. Calculating edge points using steps, for circle that should be crazy!
//		
//		// 3. Filling net
//		RectangleHelper.fillNet(ksiEtaRectangle, 0.5, 0.5);
//		// 4. Reflecting net
//		ReflectionHelper.reflectNet(xyTrapezium, ksiEtaRectangle);
//		
//		SwingUtilities.invokeLater(new Runnable() {
//			
//			@Override
//			public void run() {
//				new GraphFrame("Figure approximation", xyTrapezium, "X", "Y");
//			}
//		});
//
//		SwingUtilities.invokeLater(new Runnable() {
//			
//			@Override
//			public void run() {
//				new GraphFrame("Figure approximation", ksiEtaRectangle, "ξ", "η");
//			}
//		});
		
		Rectangle ksiEtaRectangle = new Rectangle(new Point(new double[] {1.0, 1.0}), new Point(new double[] {4.0, 4.0}), 1.0, 1.0);
		Trapezium xyTrapezium = new Trapezium(new Point(new double[] {1.0, 1.0}), new Point(new double[] {4.0, 1.0}), new Point(new double[] {3.0, 3.0}), 3, 3);
		System.out.println(ksiEtaRectangle);
		System.out.println(xyTrapezium);
		
		// Need to get both edge and net points from both of figures and build new reflection
		/* 
		 * so
		 * TODO:
		 * 
		 * 1) Still need to fill edge points for each figure using steps
		 * 2) Keep net points distinct and not include edge points
		 * 3) Then have array of all points. How it should include all points?
		 * 
		 * 
		 * */
		
		//Calculator2D reversedCalculator = new Calculator2D(xyPoints, ksietaPoints, functionName, e)
	}
}
