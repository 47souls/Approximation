package com.example.point;

import java.util.Arrays;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coordinates);
		result = prime * result + dimension;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (!Arrays.equals(coordinates, other.coordinates))
			return false;
		if (dimension != other.dimension)
			return false;
		return true;
	}
}
