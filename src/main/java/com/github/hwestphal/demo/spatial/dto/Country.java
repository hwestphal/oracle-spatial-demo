package com.github.hwestphal.demo.spatial.dto;

import com.vividsolutions.jts.geom.Geometry;

public class Country {

	private String code;
	private String name;
	private Geometry geometry;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

}
