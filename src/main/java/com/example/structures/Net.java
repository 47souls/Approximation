package com.example.structures;

import java.util.List;

import com.example.point.Point;

/**
 * Class represent net of inner points of rectangle
 * 
 * 
 */
public class Net {
	private List<Point> points;

	public Net(List<Point> points) {
		this.points = points;
	}
	
	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

}
