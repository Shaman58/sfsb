package ru.erp.sfsb.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.erp.sfsb.dto.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StoreDeserializer extends JsonDeserializer<StoreDto> {

    @Override
    public StoreDto deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        StoreDto store = new StoreDto();
        JsonNode node = parser.getCodec().readTree(parser);

        store.setId(node.path("id").asLong());
        store.setStoreName(node.path("storeName").asText());
        store.setWorkpieces(getDtoMap(node.path("workpieces"), WorkpieceDto.class, "workpiece"));
        store.setCutterTools(getDtoMap(node.path("cutterTools"), CutterToolDto.class, "cutterTool"));
        store.setMeasureTools(getDtoMap(node.path("measureTools"), MeasureToolDto.class, "measureTool"));
        store.setToolings(getDtoMap(node.path("toolings"), ToolingDto.class, "tooling"));
        store.setSpecialTools(getDtoMap(node.path("specialTools"), SpecialToolDto.class, "specialTool"));

        return store;
    }

    private <T extends AbstractDto> Map<T, Integer> getDtoMap(JsonNode node, Class<T> dtoClass, String fieldName) {
        Map<T, Integer> dtoMap = new HashMap<>();
        for (JsonNode element : node) {
            T dto = createDtoInstance(dtoClass, element.path(fieldName));
            int amount = element.path("amount").asInt();
            dtoMap.put(dto, amount);
        }
        return dtoMap;
    }

    private <T extends AbstractDto> T createDtoInstance(Class<T> dtoClass, JsonNode node) {
        try {
            T dto = dtoClass.getDeclaredConstructor().newInstance();
            dto.setId(node.path("id").asLong());
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create DTO instance", e);
        }
    }
}