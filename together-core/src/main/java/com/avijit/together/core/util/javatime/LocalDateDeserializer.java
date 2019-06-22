/**
 *
 */
package com.avijit.together.core.util.javatime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author avijit
 *
 */
public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml
     * .jackson.core.JsonParser,
     * com.fasterxml.jackson.databind.DeserializationContext)
     */
    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        return LocalDate.parse(jp.readValueAs(String.class));
    }

}