package com.github.hwestphal.demo.spatial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.hwestphal.demo.spatial.dao.ICityDao;
import com.github.hwestphal.demo.spatial.dao.ICountryDao;
import com.github.hwestphal.demo.spatial.dto.City;
import com.github.hwestphal.demo.spatial.dto.Country;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;

@Controller
public class GeoService {

	private final GeometryFactory geometryFactory = new GeometryFactory(
			new PrecisionModel(), 8307);

	private final ICityDao cityDao;
	private final ICountryDao countryDao;

	@Autowired
	public GeoService(ICityDao cityDao, ICountryDao countryDao) {
		this.cityDao = cityDao;
		this.countryDao = countryDao;
	}

	@RequestMapping(value = "/cities", method = RequestMethod.GET)
	@ResponseBody
	public List<City> getCitiesInArea(@RequestParam double east,
			@RequestParam double west, @RequestParam double north,
			@RequestParam double south,
			@RequestParam(defaultValue = "0") int population) {
		Coordinate[] coordinates = new Coordinate[5];
		coordinates[0] = new Coordinate(west, south);
		coordinates[1] = new Coordinate(west, north);
		coordinates[2] = new Coordinate(east, north);
		coordinates[3] = new Coordinate(east, south);
		coordinates[4] = coordinates[0];
		Polygon polygon = geometryFactory.createPolygon(
				geometryFactory.createLinearRing(coordinates), null);
		return cityDao.findCitiesInGeometry(polygon, population);
	}

	@RequestMapping(value = "/cities", params = "distance", method = RequestMethod.GET)
	@ResponseBody
	public List<City> getCitiesInDistance(@RequestParam double lat,
			@RequestParam double lng, @RequestParam int distance) {
		Point point = geometryFactory.createPoint(new Coordinate(lng, lat));
		return cityDao.findCitiesInDistance(point, distance);
	}

	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	@ResponseBody
	public List<Country> getCountriesForCity(@RequestParam String city) {
		return countryDao.findCountriesByCity(city);
	}

	@RequestMapping(value = "/country", method = RequestMethod.GET)
	@ResponseBody
	public Country getCountryForPoint(@RequestParam double lat,
			@RequestParam double lng) {
		Point point = geometryFactory.createPoint(new Coordinate(lng, lat));
		return countryDao.findCountryByPoint(point);
	}

}
