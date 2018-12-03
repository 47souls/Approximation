package com.example.figures;

import java.util.Arrays;

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
public class Rectangle extends ConvexPolygon {

	private Point a;
	private Point b;
	private Point c;
	private Point d;

	public Rectangle(Point a, Point c) {
		this.a = a;
		this.c = c;

		initAllVertexes();
		
		setEdgePoints(new Point[] {a, b, c, d });
	}
	
	public Rectangle(Point a, Point c, double xAxisStep, double yAxisStep) {
		this(a, c);
		
		setEdgePointsByStep(xAxisStep, yAxisStep);
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
	
	private void setEdgePointsByStep(double xAxisStep, double yAxisStep) {
		// That sucks I know. Will think about it later on
		double[] aCoordinates = a.getCoordinates();
		double[] bCoordinates = b.getCoordinates();
		double[] cCoordinates = c.getCoordinates();
		double[] dCoordinates = d.getCoordinates();
		
		double xDistance = bCoordinates[0] - aCoordinates[0];
		int xMarksNumber = (int) (xDistance / xAxisStep);
		
		double yDistance = cCoordinates[1] - bCoordinates[1];
		int yMarksNumber = (int) (yDistance / yAxisStep);
		
		int numberOfPoints = 2 * xMarksNumber + 2 * yMarksNumber;
		Point[] edgePoints = new Point[numberOfPoints];
		
		int edgePointIndex = 0;
		// Edge points between a and b
		for (int i = 0; i < xMarksNumber; i++, edgePointIndex++) {
			edgePoints[edgePointIndex] = new Point(new double[] {
					aCoordinates[0] + xAxisStep * i,
					aCoordinates[1]
			});
		}
		// Edge points between b and c
		for (int i = 0; i < yMarksNumber; i++, edgePointIndex++) {
			edgePoints[edgePointIndex] = new Point(new double[] {
					bCoordinates[0],
					bCoordinates[1] + yAxisStep * i
			});
		}
		// Edge points between c and d
		for (int i = 0; i < xMarksNumber; i++, edgePointIndex++) {
			edgePoints[edgePointIndex] = new Point(new double[] {
					cCoordinates[0] - xAxisStep * i,
					cCoordinates[1]
			});
		}
		// Edge points between d and a
		for (int i = 0; i < yMarksNumber; i++, edgePointIndex++) {
			edgePoints[edgePointIndex] = new Point(new double[] {
					dCoordinates[0],
					dCoordinates[1] - yAxisStep * i
			});
		}
		
		setEdgePoints(edgePoints);
	}

	@Override
	protected void initAllVertexes() {
		double[] aCoordinates = a.getCoordinates();
		double[] cCoordinates = c.getCoordinates();

		this.b = new Point(new double[] { cCoordinates[0], aCoordinates[1] });
		this.d = new Point(new double[] { aCoordinates[0], cCoordinates[1] });
	}

	@Override
	public String toString() {
		return "Rectangle [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", netPoints=" + "\n" + printNetPoints(netPoints)
				+ "," + "edgePoints=" + "\n" + Arrays.toString(edgePoints) + "]";
	}
}
