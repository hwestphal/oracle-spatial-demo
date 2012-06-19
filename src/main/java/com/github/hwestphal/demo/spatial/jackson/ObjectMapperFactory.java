package com.github.hwestphal.demo.spatial.jackson;

import java.util.List;
import java.util.UUID;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class ObjectMapperFactory extends AbstractFactoryBean<ObjectMapper> {

	private List<JsonSerializer<?>> serializers;

	public void setSerializers(List<JsonSerializer<?>> serializers) {
		this.serializers = serializers;
	}

	@Override
	public Class<?> getObjectType() {
		return ObjectMapper.class;
	}

	@Override
	protected ObjectMapper createInstance() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		if (serializers != null && !serializers.isEmpty()) {
			SimpleModule module = new SimpleModule(
					UUID.randomUUID().toString(), Version.unknownVersion());
			for (JsonSerializer<?> serializer : serializers) {
				module.addSerializer(serializer);
			}
			mapper.registerModule(module);
		}
		return mapper;
	}

}
