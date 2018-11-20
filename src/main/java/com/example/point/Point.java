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
}
