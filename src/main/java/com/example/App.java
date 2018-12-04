package com.example;

import javax.swing.SwingUtilities;

import com.example.calculator.Calculator2D;
import com.example.calculator.GenericCalculator;
import com.example.figures.Rectangle;
import com.example.figures.Trapezium;
import com.example.gui.GraphFrame;
import com.example.helper.RectangleHelper;
import com.example.helper.ReflectionHelper;
import com.example.point.Point;

public class App {
	public static void main(String[] args) {
		// 1. Creating figures
		Rectangle ksiEtaRectangle = new Rectangle(new Point(new double[] {1.0, 1.0}), new Point(new double[] {4.0, 4.0}), 0.5, 0.5);
		RectangleHelper.fillNet(ksiEtaRectangle, 0.5, 0.5);
		ksiEtaRectangle.setAllPoints();
		
		Trapezium xyTrapezium = new Trapezium(new Point(new double[] {1.0, 1.0}), new Point(new double[] {4.0, 1.0}), new Point(new double[] {3.0, 3.0}), 6, 6);
		// 2. Calculating edge points using steps, for circle that should be crazy!
		
		// 3. Filling net
		
		// 4. Reflecting net
		ReflectionHelper.reflectNet(xyTrapezium, ksiEtaRectangle);
		xyTrapezium.setAllPoints();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new GraphFrame("Figure approximation", xyTrapezium, "X", "Y");
			}
		});

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new GraphFrame("Figure approximation", ksiEtaRectangle, "ξ", "η");
			}
		});
		
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
		String functionName = GenericCalculator.MULTI_QUADRO_FUNCTION_NAME;
		double e = 0.1;
		// Pay attention here coordinates are consumed in reversed order
		Calculator2D reversedCalculator = new Calculator2D(ksiEtaRectangle.getAllPoints(), xyTrapezium.getAllPoints(), functionName, e);
		
		Point result = reversedCalculator.approximate(new Point(new double[] { 2.5, 2.5}));
		System.out.println(result);
	}
}
