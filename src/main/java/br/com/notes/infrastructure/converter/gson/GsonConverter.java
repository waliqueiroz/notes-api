package br.com.notes.infrastructure.converter.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class GsonConverter {
    private static final Gson gson;

    static {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTime());
        gson = builder.create();
    }

    public static <T> String toJson(T obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Type valueType) {
        return gson.fromJson(json, valueType);
    }
}
