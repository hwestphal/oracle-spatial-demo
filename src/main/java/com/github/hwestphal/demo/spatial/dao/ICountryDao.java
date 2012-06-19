package com.github.hwestphal.demo.spatial.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.hwestphal.demo.spatial.dto.Country;
import com.vividsolutions.jts.geom.Point;

public interface ICountryDao {

	@Select("SELECT cntry.* FROM country cntry, city c WHERE sdo_relate(cntry.geometry, c.geometry, 'mask=anyinteract') = 'TRUE' AND c.name = #{city}")
	List<Country> findCountriesByCity(@Param("city") String city);

	@Select("SELECT * FROM country WHERE sdo_relate(geometry, #{point}, 'mask=anyinteract') = 'TRUE'")
	Country findCountryByPoint(@Param("point") Point point);

}
