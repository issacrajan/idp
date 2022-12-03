/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.idp.ser;

import java.io.IOException;
import java.time.Duration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author issac
 *
 */
public class CustomDurationSerializer extends StdSerializer<Duration> {

	private static final long serialVersionUID = 1L;

	public CustomDurationSerializer() {
		this(null);
	}

	public CustomDurationSerializer(Class<Duration> t) {
		super(t);
	}

	@Override
	public void serialize(Duration value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(value.toString());
	}

}
