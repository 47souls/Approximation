package com.example.figures;

import java.util.Arrays;

import com.example.calculator.FunctionHelper;
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
	
	public Trapezium(Point a, Point b, Point c, int numberOfPointsX, int numberOfPointsY) {
		this(a, b, c);
		
		setEdgePointsByStep(numberOfPointsX, numberOfPointsY);
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
	
	/**
	 * This thing works only if AB and CD are in parallel with OX Axis.
	 * Be aware of that me in future
	 * 
	 * */
	private void setEdgePointsByStep(int numberOfPointsX, int numberOfPointsY) {
		// That sucks even more than for Rectangle I know. Will think about it later on
		double[] aCoordinates = a.getCoordinates();
		double[] bCoordinates = b.getCoordinates();
		double[] cCoordinates = c.getCoordinates();
		double[] dCoordinates = d.getCoordinates();
		
		double xABDistance = bCoordinates[0] - aCoordinates[0];
		double yDistance = cCoordinates[1] - bCoordinates[1];
		double xCDDistance = cCoordinates[0] - dCoordinates[0];
		
		double abXAxisStep = xABDistance / numberOfPointsX; 
		double cdXAxisStep = xCDDistance / numberOfPointsX; 
		double yStep = yDistance / numberOfPointsY;

		int numberOfPoints = 2 * numberOfPointsX + 2 * numberOfPointsY;
		
		Point[] edgePoints = new Point[numberOfPoints];
		
		int edgePointIndex = 0;
		// Edge points between a and b
		for (int i = 0; i < numberOfPointsX; i++, edgePointIndex++) {
			edgePoints[edgePointIndex] = new Point(new double[] {
					aCoordinates[0] + abXAxisStep * i,
					aCoordinates[1]
			});
		}
		// Edge points between b and c
		for (int i = 0; i < numberOfPointsY; i++, edgePointIndex++) {
			edgePoints[edgePointIndex] = FunctionHelper.getPointByYCoordinateFunction.apply(b, c).apply(bCoordinates[1] + yStep * i);
		}
		// Edge points between c and d
		for (int i = 0; i < numberOfPointsX; i++, edgePointIndex++) {
			edgePoints[edgePointIndex] = new Point(new double[] {
					cCoordinates[0] - cdXAxisStep * i,
					cCoordinates[1]
			});
		}
		// Edge points between d and a
		for (int i = 0; i < numberOfPointsY; i++, edgePointIndex++) {
			edgePoints[edgePointIndex] = FunctionHelper.getPointByYCoordinateFunction.apply(d, a).apply(dCoordinates[1] - yStep * i);
		}
		
		setEdgePoints(edgePoints);
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
		return "Trapezium [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", netPoints=" + "\n" + printNetPoints(netPoints) 
			+ "," + "edgePoints=" + "\n" + Arrays.toString(edgePoints) + "]";
	}

}
