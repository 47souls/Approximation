package com.example.figures;

import java.util.stream.Stream;

import com.example.point.Point;

public abstract class ConvexPolygon {

	protected Point[] edgePoints;
	protected Point[][] netPoints;
	protected Point[] allPoints;

	public Point[] getEdgePoints() {
		return edgePoints;
	}

	public void setEdgePoints(Point[] edgePoints) {
		this.edgePoints = edgePoints;
	}

	public Point[][] getNetPoints() {
		return netPoints;
	}

	public void setNetPoints(Point[][] netPoints) {
		this.netPoints = netPoints;
	}

	public Point[] getAllPoints() {
		return allPoints;
	}

	public void setAllPoints(Point[] allPoints) {
		this.allPoints = allPoints;
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
	
	public void setAllPoints() {
		// Fuck this shit. But you should do it today man.
		Point[][] allPoints = new Point[][];
	}
	
	protected abstract void initAllVertexes();

}
