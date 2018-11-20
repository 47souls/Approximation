package com.example.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Covers approximation of graphics on XoY graph
 * 
 */
public class Calculator1D extends GenericCalculator {

	private List<Double> constants;
	private List<Double> x;
	private List<Double> y;


	public Calculator1D(List<Double> x, List<Double> y, String functionName, double e) {
		super(functionName, e);
		
		this.x = x;
		this.y = y;
	}

	public List<Double> getX() {
		return x;
	}

	public void setX(List<Double> x) {
		this.x = x;
	}

	public List<Double> getY() {
		return y;
	}

	public void setY(List<Double> y) {
		this.y = y;
	}

	public double radius(double begin, double end) {
		return Math.abs(begin - end);
	}
	
	public List<Double> calculateConstants() {
		int counter = 0;
		int numberOfLines = x.size();
		double fi[][] = new double[numberOfLines][numberOfLines];
		List<Double> oldX = new ArrayList<>();
		List<Double> oldY = new ArrayList<>();

		for (int i = 0; i < x.size(); i++) {
			oldX.add(i, x.get(i));
		}

		for (int i = 0; i < y.size(); i++) {
			oldY.add(i, y.get(i));
		}

		/* Calculating fi[][] using radial function */
		BiFunction<Double, Double, Double> radialFunction = radialFunctionsMap.get(functionName);
		
		for (int i = 0; i < numberOfLines; i++) {
			for (int j = 0; j < numberOfLines; j++) {
				fi[i][j] = radialFunction.apply(radius(oldX.get(i), oldX.get(j)), e);
				System.out.print(fi[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("Y= ");
		for (int i = 0; i < numberOfLines; i++) {
			System.out.println(y.get(i));
		}

		/* Algorithm go ahead */
		while (counter < numberOfLines) {
			double temp = fi[counter][counter];
			double temp3 = oldY.get(counter);
			for (int i = counter; i < numberOfLines - 1; i++) {
				double temp2 = fi[i + 1][counter];
				for (int j = counter; j < numberOfLines - 1; j++) {
					fi[i + 1][j + 1] = fi[i + 1][j + 1] - fi[counter][j + 1] * temp2 / temp;
				}
				oldY.set(i + 1, oldY.get(i + 1) - temp3 * temp2 / temp);
			}
			oldY.set(counter, oldY.get(counter) / temp);
			for (int i = counter; i < numberOfLines; i++) {
				fi[counter][i] = fi[counter][i] / temp;
			}
			for (int i = counter + 1; i < numberOfLines; i++) {
				fi[i][counter] = 0;
			}
			counter++;
		}

		/* Algorithm go behind */
		oldX.set(numberOfLines - 1, oldY.get(numberOfLines - 1));
		for (int i = numberOfLines - 2; i >= 0; i--) {
			double temp = 0;
			for (int j = i + 1; j < numberOfLines; j++) {
				temp += fi[i][j] * oldX.get(j);
			}
			oldX.set(i, oldY.get(i) - temp);
		}

		constants = oldX;
		return oldX;
	}

	public double approximate(double x0) {
		double sum = 0;
		double fi = 0;
		for (int i = 0; i < constants.size(); i++) {
			System.out.println("Contant(" + i + "): " + constants.get(i));
			System.out.println("X(" + i + "): " + x.get(i));
			
			// Determining distance from x0 to each x
			double radius = radius(x0, x.get(i));
			
			BiFunction<Double, Double, Double> radialFunction = radialFunctionsMap.get(functionName);
			fi = radialFunction.apply(radius, e);
			sum += constants.get(i) * fi;
		}
		
		System.out.println("Sum : " + sum);
		return sum;
	}
}
