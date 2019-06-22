/**
 *
 */
package com.avijit.together.core.util.javatime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author avijit
 *
 */
public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public LocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.fasterxml.jackson.databind.ser.std.StdSerializer#serialize(java.lang.
     * Object, com.fasterxml.jackson.core.JsonGenerator,
     * com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider sp)
            throws IOException {
        gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
