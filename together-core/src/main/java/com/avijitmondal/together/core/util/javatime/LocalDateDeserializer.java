/**
 * 
 */
package com.avijitmondal.together.core.util.javatime;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author avijit
 *
 */
public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

	private static final long serialVersionUID = 1L;

    /**
     *
     */
    protected LocalDateDeserializer() {
		super(LocalDate.class);
	}

    /**
     *
     * @param jp
     * @param ctxt
     * @return
     * @throws IOException
	 */
    @Override
	public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException {
		return LocalDate.parse(jp.readValueAs(String.class));
	}

}