/**
 * 
 */
package com.avijitmondal.together.core.util.javatime;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author avijit
 *
 */
public class LocalDateSerializer extends StdSerializer<LocalDate> {

	private static final long serialVersionUID = 1L;

    /**
     *
     */
    public LocalDateSerializer() {
		super(LocalDate.class);
	}

    /**
     *
     * @param value
     * @param gen
     * @param sp
     * @throws IOException
	 */
    @Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider sp)
			throws IOException {
		gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE));
	}
}
