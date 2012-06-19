package com.github.hwestphal.demo.spatial.jackson;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.SerializerBase;

import com.vividsolutions.jts.geom.Point;

public class PointSerializer extends SerializerBase<Point> {

	public PointSerializer() {
		super(Point.class);
	}

	@Override
	public void serialize(Point value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeObject(value.getCoordinate());
	}

}
