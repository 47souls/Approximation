package com.example.point;

import java.text.DecimalFormat;

public class Point {
	private int dimension;
	private double[] coordinates;

	public Point(double[] coordinates) {
		this.coordinates = coordinates;
		this.dimension = coordinates.length;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();
		DecimalFormat decimal2Points = new DecimalFormat(".##");
		value.append("[ ");
		for (int i = 0; i < coordinates.length - 1; i++) {
			value.append(decimal2Points.format(coordinates[i])).append(" ; ");
		}
		
		value.append(decimal2Points.format(coordinates[coordinates.length - 1]));
		
		value.append(" ]");

		return value.toString();
	}

}
