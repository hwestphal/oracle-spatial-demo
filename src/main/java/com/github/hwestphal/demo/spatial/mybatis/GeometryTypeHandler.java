package com.github.hwestphal.demo.spatial.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleConnection;
import oracle.sql.STRUCT;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.geotools.data.oracle.sdo.GeometryConverter;
import org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;

@MappedTypes({ Point.class, LineString.class, Polygon.class, MultiPoint.class,
		MultiLineString.class, MultiPolygon.class, GeometryCollection.class,
		Geometry.class })
public class GeometryTypeHandler extends BaseTypeHandler<Geometry> {

	private GeometryFactory geometryFactory = new GeometryFactory(
			new PrecisionModel(), 8307);

	private NativeJdbcExtractor jdbcExtractor;

	public void setGeometryFactory(GeometryFactory geometryFactory) {
		this.geometryFactory = geometryFactory;
	}

	public void setJdbcExtractor(NativeJdbcExtractor jdbcExtractor) {
		this.jdbcExtractor = jdbcExtractor;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			Geometry parameter, JdbcType jdbcType) throws SQLException {
		OracleConnection conn;
		if (jdbcExtractor == null) {
			conn = (OracleConnection) ps.getConnection();
		} else {
			conn = (OracleConnection) jdbcExtractor
					.getNativeConnectionFromStatement(ps);
		}
		GeometryConverter converter = new GeometryConverter(conn,
				parameter.getFactory());
		ps.setObject(i, converter.toSDO(parameter));
	}

	@Override
	public Geometry getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		GeometryConverter converter = new GeometryConverter(null,
				geometryFactory);
		return converter.asGeometry((STRUCT) rs.getObject(columnName));
	}

	@Override
	public Geometry getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		GeometryConverter converter = new GeometryConverter(null,
				geometryFactory);
		return converter.asGeometry((STRUCT) cs.getObject(columnIndex));
	}

}
