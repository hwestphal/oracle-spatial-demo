package com.github.hwestphal.demo.spatial.jackson;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.SerializerBase;

import com.vividsolutions.jts.geom.Coordinate;

public class CoordinateSerializer extends SerializerBase<Coordinate> {

	public CoordinateSerializer() {
		super(Coordinate.class);
	}

	@Override
	public void serialize(Coordinate value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeStartObject();
		jgen.writeNumberField("lat", value.y);
		jgen.writeNumberField("lng", value.x);
		jgen.writeEndObject();
	}

}
