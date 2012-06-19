package com.github.hwestphal.demo.spatial.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import com.vividsolutions.jts.geom.Point;

public class City {

	private String name;
	private String country;
	private int population;

	@JsonProperty("point")
	private Point geometry;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public Point getGeometry() {
		return geometry;
	}

	public void setGeometry(Point geometry) {
		this.geometry = geometry;
	}

}
