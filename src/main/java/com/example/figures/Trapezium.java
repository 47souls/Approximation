package com.example.figures;

import com.example.point.Point;

/**
 * Class to represent rectangle on coordinate XoY axis
 * 
 * It has 4 points which are vertexes
 * 
 * Constructor gets 3 points a,b and c which form Trapezium as d is
 * calculated afterwards
 * 
 * In form
 * 
 * 	 d---c 
 * - 	   - 
 * a-------b
 * 
 */
public class Trapezium extends ConvexPolygon {

	private Point a;
	private Point b;
	private Point c;
	private Point d;

	public Trapezium(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;

		initAllVertexes();
		
		setEdgePoints(new Point[] {a, b, c, d});
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

	@Override
	protected void initAllVertexes() {
		double[] aCoordinates = a.getCoordinates();
		double[] bCoordinates = b.getCoordinates();
		double[] cCoordinates = c.getCoordinates();

		this.d = new Point(new double[] { aCoordinates[0] + bCoordinates[0] - cCoordinates[0], cCoordinates[1] });
	}

	@Override
	public String toString() {
		return "Trapezium [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", netPoints=" + "\n"
				+ printNetPoints(netPoints) + "]";
	}

}
