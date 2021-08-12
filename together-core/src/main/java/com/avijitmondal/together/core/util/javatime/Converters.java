/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avijitmondal.together.core.util.javatime;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

/**
 *
 * @author avijit
 */
@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class Converters {

    private Converters() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * The specific genericized type for {@code LocalDate}.
     */
    public static final Type LOCAL_DATE_TYPE = new TypeToken<LocalDate>() {
    }.getType();

    /**
     * The specific genericized type for {@code LocalDateTime}.
     */
    public static final Type LOCAL_DATE_TIME_TYPE = new TypeToken<LocalDateTime>() {
    }.getType();

    /**
     * The specific genericized type for {@code LocalTime}.
     */
    public static final Type LOCAL_TIME_TYPE = new TypeToken<LocalTime>() {
    }.getType();

    /**
     * The specific genericized type for {@code OffsetDateTime}.
     */
    public static final Type OFFSET_DATE_TIME_TYPE = new TypeToken<OffsetDateTime>() {
    }.getType();

    /**
     * The specific genericized type for {@code OffsetTime}.
     */
    public static final Type OFFSET_TIME_TYPE = new TypeToken<OffsetTime>() {
    }.getType();

    /**
     * The specific genericized type for {@code ZonedDateTime}.
     */
    public static final Type ZONED_DATE_TIME_TYPE = new TypeToken<ZonedDateTime>() {
    }.getType();

    /**
     * The specific genericized type for {@code Instant}.
     */
    public static final Type INSTANT_TYPE = new TypeToken<Instant>() {
    }.getType();

    /**
     * Registers all the Java Time converters.
     *
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerAll(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        }

        registerLocalDate(builder);
        registerLocalDateTime(builder);
        registerLocalTime(builder);
        registerOffsetDateTime(builder);
        registerOffsetTime(builder);
        registerZonedDateTime(builder);
        registerInstant(builder);

        return builder;
    }

    /**
     * Registers the {@link LocalDateConverter} converter.
     *
     * @param builder The GSON builder to register the converter with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerLocalDate(GsonBuilder builder) {
        builder.registerTypeAdapter(LOCAL_DATE_TYPE, new LocalDateConverter());

        return builder;
    }

    /**
     * Registers the {@link LocalDateTimeConverter} converter.
     *
     * @param builder The GSON builder to register the converter with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerLocalDateTime(GsonBuilder builder) {
        builder.registerTypeAdapter(LOCAL_DATE_TIME_TYPE, new LocalDateTimeConverter());

        return builder;
    }

    /**
     * Registers the {@link LocalTimeConverter} converter.
     *
     * @param builder The GSON builder to register the converter with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerLocalTime(GsonBuilder builder) {
        builder.registerTypeAdapter(LOCAL_TIME_TYPE, new LocalTimeConverter());

        return builder;
    }

    /**
     * Registers the {@link OffsetDateTimeConverter} converter.
     *
     * @param builder The GSON builder to register the converter with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerOffsetDateTime(GsonBuilder builder) {
        builder.registerTypeAdapter(OFFSET_DATE_TIME_TYPE, new OffsetDateTimeConverter());

        return builder;
    }

    /**
     * Registers the {@link OffsetTimeConverter} converter.
     *
     * @param builder The GSON builder to register the converter with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerOffsetTime(GsonBuilder builder) {
        builder.registerTypeAdapter(OFFSET_TIME_TYPE, new OffsetTimeConverter());

        return builder;
    }

    /**
     * Registers the {@link ZonedDateTimeConverter} converter.
     *
     * @param builder The GSON builder to register the converter with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerZonedDateTime(GsonBuilder builder) {
        builder.registerTypeAdapter(ZONED_DATE_TIME_TYPE, new ZonedDateTimeConverter());

        return builder;
    }

    /**
     * Registers the {@link InstantConverter} converter.
     *
     * @param builder The GSON builder to register the converter with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerInstant(GsonBuilder builder) {
        builder.registerTypeAdapter(INSTANT_TYPE, new InstantConverter());

        return builder;
    }
}
