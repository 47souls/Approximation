package com.example.structures;

import com.example.point.Point;

/**
 * Class to represent rectangle on coordinate XoY axis
 * 
 * It has 4 points which are vertexes
 * 
 * Constructor gets 2 points a and c which form Rectangle as b and d are
 * calculated afterwards
 * 
 * In form
 * 
 * d-------c 
 * - 	   - 
 * a-------b
 * 
 */
public class Rectangle {

	private Point a;
	private Point b;
	private Point c;
	private Point d;

	private Point[][] netPoints;

	public Rectangle(Point a, Point c) {
		this.a = a;
		this.c = c;

		initAllVertexes(a, c);
	}

	public Point getA() {
		return a;
	}

	public void setA(Point a) {
		this.a = a;
	}

	public Point getB() {
		return b;
	}

	public void setB(Point b) {
		this.b = b;
	}

	public Point getC() {
		return c;
	}

	public void setC(Point c) {
		this.c = c;
	}

	public Point getD() {
		return d;
	}

	public void setD(Point d) {
		this.d = d;
	}

	public Point[][] getNetPoints() {
		return netPoints;
	}

	public void setNetPoints(Point[][] netPoints) {
		this.netPoints = netPoints;
	}

	private void initAllVertexes(Point a, Point c) {
		double[] aCoordinates = a.getCoordinates();
		double[] cCoordinates = c.getCoordinates();

		this.b = new Point(new double[] { cCoordinates[0], aCoordinates[1] });
		this.d = new Point(new double[] { aCoordinates[0], cCoordinates[1] });
	}
	
	private String printNetPoints(Point[][] netPoints) {
		String result = "";
		int rows = netPoints.length;
		int columns = netPoints[0].length;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.println(netPoints[i][j]);
			}
			
			System.out.println();
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "Rectangle [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", netPoints=" + printNetPoints(netPoints)
				+ "]";
	}
	
	
}
