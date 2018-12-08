package com.example.calculator;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.example.point.Point;

public abstract class GenericCalculator {

	protected HashMap<String, BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Double>>> radialFunctionsMap = new HashMap<>();
	protected HashMap<String, BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Function<Integer, Double>>>> partialDerivativeRadialFunctionsMap = new HashMap<>();
	protected HashMap<String, BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Function<Integer, Double>>>> doublePartialDerivativeRadialFunctionsMap = new HashMap<>();
	
	// Radial basis functions
	public BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Double>> multiQuadroRadialFunction = 
			(p1, p2) -> (radiusFunction, e) -> Math.pow(1 + Math.pow(radiusFunction.apply(p1, p2) * e, 2), 0.5);
	public BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Double>> revertedMultiQuadroRadialFunction = (p1, p2) -> (radiusFunction, e) -> 1.0 / multiQuadroRadialFunction.apply(p1, p2).apply(radiusFunction, e);
	public BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Double>> revertedQuadroRadialFunction = (p1, p2) -> (radiusFunction, e) -> 1.0 / Math.pow(multiQuadroRadialFunction.apply(p1, p2).apply(radiusFunction, e), 0.5);
	public BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Double>> gaussRadialFunction =  (p1, p2) -> (radiusFunction, e) -> Math.exp(-Math.pow(radiusFunction.apply(p1, p2) * e, 2.0));
	
	// Partial derivative functions
	public BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Function<Integer, Double>>> partialDerivativeMultiQuadroRadialFunction = (p1, p2) -> (radiusFunction, e) -> (coordinateNumber) -> Math.pow(e, 2) * (p1.getCoordinates()[coordinateNumber] - p2.getCoordinates()[coordinateNumber]) / multiQuadroRadialFunction.apply(p1, p2).apply(radiusFunction, e);
	public BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Function<Integer, Double>>> partialDerivativeRevertedMultiQuadroRadialFunction = (p1, p2) -> (radiusFunction, e) -> (coordinateNumber) -> -Math.pow(e, 2) * (p1.getCoordinates()[coordinateNumber] - p2.getCoordinates()[coordinateNumber]) * Math.pow(revertedMultiQuadroRadialFunction.apply(p1,  p2).apply(radiusFunction, e), 3);
	public BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Function<Integer, Double>>> partialDerivativeRevertedQuadroRadialFunction = (p1, p2) -> (radiusFunction, e) -> (coordinateNumber) -> -2 * Math.pow(e, 2) * (p1.getCoordinates()[coordinateNumber] - p2.getCoordinates()[coordinateNumber]) / Math.pow(revertedQuadroRadialFunction.apply(p1,  p2).apply(radiusFunction, e), 2);
	public BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Function<Integer, Double>>> partialDerivativeGaussRadialFunction = (p1, p2) -> (radiusFunction, e) -> (coordinateNumber) -> -2 * Math.pow(e, 2) * (p1.getCoordinates()[coordinateNumber] - p2.getCoordinates()[coordinateNumber]) * gaussRadialFunction.apply(p1,  p2).apply(radiusFunction, e);
	
	// Partial 2 derivative functions
	public BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Function<Integer, Double>>> partial2DerivativeMultiQuadroRadialFunction = (p1, p2) -> (radiusFunction, e) -> (coordinateNumber) -> Math.pow(e, 2) * multiQuadroRadialFunction.apply(p1, p2).apply(radiusFunction, e) - Math.pow(e, 4) * Math.pow((p1.getCoordinates()[coordinateNumber] - p2.getCoordinates()[coordinateNumber]), 2);
	public BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Function<Integer, Double>>> partial2DerivativeRevertedMultiQuadroRadialFunction = (p1, p2) -> (radiusFunction, e) -> (coordinateNumber) -> Math.pow(e, 2) * Math.pow(revertedMultiQuadroRadialFunction.apply(p1, p2).apply(radiusFunction, e), 3) * (-1 + 3 * Math.pow(e,2) * Math.pow((p1.getCoordinates()[coordinateNumber] - p2.getCoordinates()[coordinateNumber]), 2) * Math.pow(revertedMultiQuadroRadialFunction.apply(p1, p2).apply(radiusFunction, e), 2));
	public BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Function<Integer, Double>>> partial2DerivativeRevertedQuadroRadialFunction = (p1, p2) -> (radiusFunction, e) -> (coordinateNumber) -> -2 * Math.pow(e, 2) / Math.pow(revertedQuadroRadialFunction.apply(p1, p2).apply(radiusFunction, e), 2) * (1 + 4 * Math.pow(e, 2) * Math.pow((p1.getCoordinates()[coordinateNumber] - p2.getCoordinates()[coordinateNumber]), 2) / Math.pow(revertedQuadroRadialFunction.apply(p1, p2).apply(radiusFunction, e), 3));
	public BiFunction<Point, Point, BiFunction<BiFunction<Point, Point, Double>, Double, Function<Integer, Double>>> partial2DerivativeGaussRadialFunction = (p1, p2) -> (radiusFunction, e) -> (coordinateNumber) -> 2 * Math.pow(e, 2) * gaussRadialFunction.apply(p1, p2).apply(radiusFunction, e) * (-1 + 2 * Math.pow(e, 2) * Math.pow((p1.getCoordinates()[coordinateNumber] - p2.getCoordinates()[coordinateNumber]), 2));
	
	public static final String MULTI_QUADRO_FUNCTION_NAME = "multiQuadro";
	public static final String REVERTED_MULTI_QUADRO_FUNCTION_NAME = "revertedMultiQuadro";
	public static final String REVERTED_QUADRO_FUNCTION_NAME = "revertedQuadro";
	public static final String GAUSS_NAME_FUNCTION = "gauss";
	
	protected String functionName;
	protected double e;
	
	public GenericCalculator(String functionName, double e) {
		this.functionName = functionName;
		this.e = e;
		
		radialFunctionsMap.put(MULTI_QUADRO_FUNCTION_NAME, multiQuadroRadialFunction);
		radialFunctionsMap.put(REVERTED_MULTI_QUADRO_FUNCTION_NAME, revertedMultiQuadroRadialFunction);
		radialFunctionsMap.put(REVERTED_QUADRO_FUNCTION_NAME, revertedQuadroRadialFunction);
		radialFunctionsMap.put(GAUSS_NAME_FUNCTION, gaussRadialFunction);
		
		partialDerivativeRadialFunctionsMap.put(MULTI_QUADRO_FUNCTION_NAME, partialDerivativeMultiQuadroRadialFunction);
		partialDerivativeRadialFunctionsMap.put(REVERTED_MULTI_QUADRO_FUNCTION_NAME, partialDerivativeRevertedMultiQuadroRadialFunction);
		partialDerivativeRadialFunctionsMap.put(REVERTED_QUADRO_FUNCTION_NAME, partialDerivativeRevertedQuadroRadialFunction);
		partialDerivativeRadialFunctionsMap.put(GAUSS_NAME_FUNCTION, partialDerivativeGaussRadialFunction);
		
		doublePartialDerivativeRadialFunctionsMap.put(MULTI_QUADRO_FUNCTION_NAME, partial2DerivativeMultiQuadroRadialFunction);
		doublePartialDerivativeRadialFunctionsMap.put(REVERTED_MULTI_QUADRO_FUNCTION_NAME, partial2DerivativeRevertedMultiQuadroRadialFunction);
		doublePartialDerivativeRadialFunctionsMap.put(REVERTED_QUADRO_FUNCTION_NAME, partial2DerivativeRevertedQuadroRadialFunction);
		doublePartialDerivativeRadialFunctionsMap.put(GAUSS_NAME_FUNCTION, partial2DerivativeGaussRadialFunction);
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public double getE() {
		return e;
	}

	public void setE(double e) {
		this.e = e;
	}
}
