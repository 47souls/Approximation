package com.example.figures;

import com.example.point.Point;

public abstract class ConvexPolygon {
	
	protected Point[] givenPoints; 
	protected Point[][] netPoints;
	
	public Point[] getGivenPoints() {
		return givenPoints;
	}
	public void setGivenPoints(Point[] givenPoints) {
		this.givenPoints = givenPoints;
	}
	public Point[][] getNetPoints() {
		return netPoints;
	}
	public void setNetPoints(Point[][] netPoints) {
		this.netPoints = netPoints;
	}
	
	protected String printNetPoints(Point[][] netPoints) {
		if (netPoints != null && netPoints.length != 0) {
			StringBuilder result = new StringBuilder();
			int rows = netPoints.length;
			int columns = netPoints[0].length;

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					result.append(netPoints[i][j]).append(" ");
				}

				result.append("\n");
			}

			return result.toString();

		} else {
			return "";
		}
	}
	
	protected abstract void initAllVertexes();
	
}