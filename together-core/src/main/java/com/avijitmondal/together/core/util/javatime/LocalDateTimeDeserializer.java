/**
 * 
 */
package com.avijitmondal.together.core.util.javatime;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author avijit
 *
 */
public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

	private static final long serialVersionUID = 1L;

    /**
     *
     */
    protected LocalDateTimeDeserializer() {
		super(LocalDateTime.class);
	}

    /**
     *
     * @param jp
     * @param ctxt
     * @return
     * @throws IOException
	 */
    @Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException {
		return LocalDateTime.parse(jp.readValueAs(String.class));
	}

}