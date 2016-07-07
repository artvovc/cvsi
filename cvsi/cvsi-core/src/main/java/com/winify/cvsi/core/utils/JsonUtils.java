package com.winify.cvsi.core.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;

public class JsonUtils {
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }

    public static String convertObjectToJsonString(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static Object convertJsonSourceToObject(String str, Class clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(str, clazz);
    }

    public static Object convertJsonSourceToObject(Reader reader, Class clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(reader, clazz);
    }

    public static <T> T convertJsonSourceToGenericObject(String json, TypeReference reference) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(json, reference);
    }
}
