package com.example.calculator;

import java.util.HashMap;
import java.util.function.BiFunction;

public abstract class GenericCalculator {

	protected HashMap<String, BiFunction<Double, Double, Double>> radialFunctionsMap = new HashMap<>();

	public BiFunction<Double, Double, Double> multiQuadroRadialFunction = (radius, e) -> Math.pow(1 + Math.pow(radius * e, 2), 0.5);
	public BiFunction<Double, Double, Double> revertedMultiQuadroRadialFunction = (radius, e) -> 1.0 / multiQuadroRadialFunction.apply(radius, e);
	public BiFunction<Double, Double, Double> revertedQuadroRadialFunction = (radius, e) -> 1.0 / Math.pow(multiQuadroRadialFunction.apply(radius, e), 0.5);
	public BiFunction<Double, Double, Double> gaussRadialFunction = (radius, e) -> Math.exp(-Math.pow(radius * e, 2.0));
	
	protected String functionName;
	protected double e;
	
	public GenericCalculator(String functionName, double e) {
		this.functionName = functionName;
		this.e = e;
		
		radialFunctionsMap.put("multiQuadro", multiQuadroRadialFunction);
		radialFunctionsMap.put("revertedMultiQuadro", revertedMultiQuadroRadialFunction);
		radialFunctionsMap.put("revertedQuadro", revertedQuadroRadialFunction);
		radialFunctionsMap.put("gauss", gaussRadialFunction);
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
