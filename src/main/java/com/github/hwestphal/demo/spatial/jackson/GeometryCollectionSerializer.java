package com.github.hwestphal.demo.spatial.jackson;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.SerializerBase;

import com.vividsolutions.jts.geom.GeometryCollection;

public class GeometryCollectionSerializer extends
		SerializerBase<GeometryCollection> {

	public GeometryCollectionSerializer() {
		super(GeometryCollection.class);
	}

	@Override
	public void serialize(GeometryCollection value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeStartArray();
		for (int i = 0; i < value.getNumGeometries(); i++) {
			jgen.writeObject(value.getGeometryN(i));
		}
		jgen.writeEndArray();
	}

}
