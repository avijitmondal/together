/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avijit.together.user.ws;

import com.avijit.together.core.util.javatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;

/**
 *
 * @author avijit
 */
public class GsonParser {

    private static final Gson GSON;
    private static final GsonBuilder BUILDER;

    static {
        BUILDER = Converters.registerAll(new GsonBuilder());
        GSON = BUILDER.create();
    }

    public static <T> T fromString(String body, Class<T> targetClass) {
        if (body == null) {
            return null;
        }

        if ("".equals(body.trim()) || targetClass == null) {
            return null;
        }
        return GSON.fromJson(body, targetClass);
    }

    public static Object fromString(String body, Type targetType) {
        if (body == null) {
            return null;
        }

        if ("".equals(body.trim()) || targetType == null) {
            return null;
        }
        return GSON.fromJson(body, targetType);
    }

    public static String toString(Object payload) {
        return GSON.toJson(payload);
    }
}
