package com.github.hwestphal.demo.spatial.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.hwestphal.demo.spatial.dto.City;
import com.vividsolutions.jts.geom.Geometry;

public interface ICityDao {

	@Select("SELECT * FROM city WHERE sdo_relate(geometry, #{geom}, 'mask=anyinteract') = 'TRUE' AND population >= #{pop}")
	List<City> findCitiesInGeometry(@Param("geom") Geometry geometry, @Param("pop") int population);

	@Select("SELECT * FROM city WHERE sdo_within_distance(geometry, #{geom}, 'distance=${distance}') = 'TRUE'")
	List<City> findCitiesInDistance(@Param("geom") Geometry geometry, @Param("distance") int distance);

}
