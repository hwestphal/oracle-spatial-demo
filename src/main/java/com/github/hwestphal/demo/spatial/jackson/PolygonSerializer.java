package com.github.hwestphal.demo.spatial.jackson;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.SerializerBase;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;

public class PolygonSerializer extends SerializerBase<Polygon> {

	public PolygonSerializer() {
		super(Polygon.class);
	}

	@Override
	public void serialize(Polygon value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeStartArray();
		Coordinate[] coords = value.getExteriorRing().getCoordinates();
		for (int i = 0; i < coords.length - 1; i++) {
			jgen.writeObject(coords[i]);
		}
		jgen.writeEndArray();
	}

}
