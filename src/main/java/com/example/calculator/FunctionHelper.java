package com.example.calculator;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.example.point.Point;

/** 
 * Class to find equation of line by 2 Points
 * 
 * For example we have 2 Points with coordinates
 * P1 = [1, 1]
 * P2 = [2, 2]
 * 
 * and we can pass any other first coordinate(x) to find second one(y) so that
 * 
 * double x* = 3
 * double y* = function.apply(P1, P2).apply(3) = 3
 * 
 * */
public class FunctionHelper {
	
	public static BiFunction<Point, Point, Function<Double, Point>> getPointFunction = (p1, p2) -> (x -> {
		double[] point1Coordinates = p1.getCoordinates();
		double[] point2Coordinates = p2.getCoordinates();
		
		double y = (x - point1Coordinates[0]) * (point2Coordinates[1] - point1Coordinates[1]) / (point2Coordinates[1] - point1Coordinates[0]) + point1Coordinates[1];
		
		return new Point(new double[] {x, y});
	});
	
	
	public static BiFunction<Point, Point, Double> twoPointsDistanceFunction = (p1, p2) -> {
		double[] point1Coordinates = p1.getCoordinates();
		double[] point2Coordinates = p2.getCoordinates();
		
		return Math.sqrt(Math.pow(point1Coordinates[0] - point2Coordinates[0], 2) + Math.pow(point1Coordinates[1] - point2Coordinates[1], 2));
	};
	
}
