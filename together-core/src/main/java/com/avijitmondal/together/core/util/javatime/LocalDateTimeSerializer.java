/**
 * 
 */
package com.avijitmondal.together.core.util.javatime;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author avijit
 *
 */
public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

	private static final long serialVersionUID = 1L;

    /**
     *
     */
    public LocalDateTimeSerializer() {
		super(LocalDateTime.class);
	}

    /**
     *
     * @param value
     * @param gen
     * @param sp
     * @throws IOException
	 */
    @Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider sp)
			throws IOException {
		gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}
}
