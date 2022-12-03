/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.idp.ser;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author issac
 *
 */
public class CustomDurationDeserializer extends StdDeserializer<Duration> {

	private static final long serialVersionUID = 1L;
	public CustomDurationDeserializer() {
		this(null);
	}
	public CustomDurationDeserializer(Class<LocalDate> t) {
		super(t);
	}
	@Override
	public Duration deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		String duration = p.getText();
		return Duration.parse(duration);
	}
	
	
}
