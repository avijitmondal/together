/**
 *
 */
package com.avijit.together.server.util.javatime;

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

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    protected LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
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
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        return LocalDateTime.parse(jp.readValueAs(String.class));
    }

}