package com.example.primejsf.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class ApiRestMapper<T> {

    public List<T> mapList(Object response, Class<? extends T> cls)
            throws IOException {
        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules().configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false).build();


        return mapper.readValue(String.valueOf(response), mapper.getTypeFactory().constructParametricType(List.class, cls)
        );

    }

    public T mapOne(Object response, Class<? extends T> cls) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(String.valueOf(response), cls);
    }
}
