package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.calculator.Calculator;

public class App {
	public static void main(String[] args) {
		List<Double> xValues = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			xValues.add(i * 0.2);
		}

		List<Double> yValues = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			yValues.add(Math.sin(i * 0.2));
		}
		
		Calculator calculator = Calculator.createCalculator(xValues, yValues, 3, 0.1);
		calculator.calculateConstants();
		
		//
		calculator.approximate(0.3);
		
	}
}
