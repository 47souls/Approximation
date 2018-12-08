package com.example.helper;

public class LinearEquationsSolver {

	/* Solving system of linear equations */
	public static double[] solve(double[] xCoordinatesCopy, double[][] fi) {
		int counter = 0;
		int numberOfLines = xCoordinatesCopy.length;
		double[] constants = new double[numberOfLines];

		/* Algorithm go ahead */
		while (counter < numberOfLines) {
			double temp = fi[counter][counter];
			double temp3 = xCoordinatesCopy[counter];
			for (int i = counter; i < numberOfLines - 1; i++) {
				double temp2 = fi[i + 1][counter];
				for (int j = counter; j < numberOfLines - 1; j++) {
					fi[i + 1][j + 1] = fi[i + 1][j + 1] - fi[counter][j + 1] * temp2 / temp;
				}
				xCoordinatesCopy[i + 1] = xCoordinatesCopy[i + 1] - temp3 * temp2 / temp;
			}
			xCoordinatesCopy[counter] = xCoordinatesCopy[counter] / temp;
			for (int i = counter; i < numberOfLines; i++) {
				fi[counter][i] = fi[counter][i] / temp;
			}
			for (int i = counter + 1; i < numberOfLines; i++) {
				fi[i][counter] = 0;
			}
			counter++;
		}

		/* Algorithm go behind */
		constants[numberOfLines - 1] = xCoordinatesCopy[numberOfLines - 1];

		for (int i = numberOfLines - 2; i >= 0; i--) {
			double temp = 0;
			for (int j = i + 1; j < numberOfLines; j++) {
				temp += fi[i][j] * constants[j];
			}
			constants[i] = xCoordinatesCopy[i] - temp;
		}

		return constants;
	}
}
