package com.example.point;

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
		value.append("[ ");
		for (int i = 0; i < coordinates.length - 1; i++) {
			double coordinate = coordinates[i];
			value.append(String.format("%.2f", coordinate)).append(" ; ");
		}
		
		value.append(String.format("%.2f", coordinates[coordinates.length - 1]));
		
		value.append(" ]");

		return value.toString();
	}

}
