package com.example.calculator;

import java.util.ArrayList;
import java.util.List;

import com.example.point.Point;

public class Calculator2D extends GenericCalculator {

	private List<Point> xyPoints;
	private List<Point> ksietaPoints;
	private List<Double> etaConstants;
	private List<Double> ksiConstants;

	public Calculator2D(List<Point> xyPoints, List<Point> ksietaPoints, String functionName, double e) {
		super(functionName, e);

		this.xyPoints = xyPoints;
		this.ksietaPoints = ksietaPoints;
	}
	
	public double radius(Point begin, Point end) {
		double[] beginCoordinates = begin.getCoordinates();
		double[] endCoordinates = end.getCoordinates();
		
		return Math.sqrt(Math.pow(beginCoordinates[0] - endCoordinates[0], 2) + Math.pow(beginCoordinates[1] - endCoordinates[1], 2));
	}

	@Override
	protected List<Double> calculateConstants() {
		int counter = 0;
		int numberOfLines = xyPoints.size();
		double fi[][] = new double[numberOfLines][numberOfLines];
		List<Point> oldXY = new ArrayList<>();
		List<Point> oldKsiEta = new ArrayList<>();

		for (int i = 0; i < xyPoints.size(); i++) {
			oldXY.add(i, xyPoints.get(i));
		}

		for (int i = 0; i < ksietaPoints.size(); i++) {
			oldKsiEta.add(i, ksietaPoints.get(i));
		}
		
		return null;
	}
}
