package ru.erp.sfsb.deserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.erp.sfsb.dto.AbstractDto;
import ru.erp.sfsb.dto.StoreDto;

import java.io.IOException;
import java.util.Map;

public class StoreDtoSerializer extends JsonSerializer<StoreDto> {

    @Override
    public void serialize(StoreDto storeDto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("storeName", storeDto.getStoreName());
        getJsonParts(jsonGenerator, storeDto.getWorkpieces(), "workpiece");
        getJsonParts(jsonGenerator, storeDto.getCutterTools(), "cutterTool");
        getJsonParts(jsonGenerator, storeDto.getMeasureTools(), "measureTool");
        getJsonParts(jsonGenerator, storeDto.getToolings(), "tooling");
        getJsonParts(jsonGenerator, storeDto.getSpecialTools(), "specialTool");
        jsonGenerator.writeEndObject();
    }

    private <T extends AbstractDto> void getJsonParts(JsonGenerator jsonGenerator, Map<T, Integer> dtos, String field) throws IOException {
        jsonGenerator.writeArrayFieldStart(field + "s");
        for (Map.Entry<T, Integer> entry : dtos.entrySet()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField(field, entry.getKey());
            jsonGenerator.writeNumberField("amount", entry.getValue());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}