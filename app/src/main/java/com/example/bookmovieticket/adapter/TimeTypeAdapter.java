package com.example.bookmovieticket.adapter;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeTypeAdapter implements JsonSerializer<Time>, JsonDeserializer<Time> {
    private final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    public JsonElement serialize(Time src, Type typeOfSrc, JsonSerializationContext context) {
        String formattedTime = timeFormat.format(src);
        return new JsonPrimitive(formattedTime);
    }

    @Override
    public Time deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            String timeString = json.getAsString();
            return new Time(timeFormat.parse(timeString).getTime());
        } catch (ParseException e) {
            throw new JsonParseException("Error parsing Time", e);
        }
    }
}
