package com.me.weathertracker.common.deserialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZoneOffset;

public class ZoneOffsetJsonDeserializer extends JsonDeserializer<ZoneOffset> {

    @Override
    public ZoneOffset deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return ZoneOffset.ofTotalSeconds(jsonParser.getIntValue());
    }
}
