package ru.erp.sfsb.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.erp.sfsb.dto.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class StoreDeserializer extends JsonDeserializer<StoreDto> {

    @Override
    public StoreDto deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        var store = new StoreDto();
        store.setId(node.get("id") == null ? null : node.get("id").longValue());
        store.setStoreName(node.get("storeName") == null ? null : node.get("storeName").asText());
        store.setCutterToolDtoIntegerMap(node.get("cuttingTools") == null ? null : nodeToMap(node.get("cuttingTools").elements())
                .entrySet().stream().collect(Collectors.toMap(entry -> (CutterToolDto) create(new CutterToolDto(), entry.getKey()),
                        Map.Entry::getValue)));
        store.setWorkpieceDtoIntegerMap(node.get("workpieces") == null ? null : nodeToMap(node.get("workpieces").elements())
                .entrySet().stream().collect(Collectors.toMap(entry -> (WorkpieceDto) create(new WorkpieceDto(), entry.getKey()),
                        Map.Entry::getValue)));
        store.setMeasureToolDtoIntegerMap(node.get("measuringTools") == null ? null : nodeToMap(node.get("measuringTools").elements())
                .entrySet().stream().collect(Collectors.toMap(entry -> (MeasureToolDto) create(new MeasureToolDto(), entry.getKey()),
                        Map.Entry::getValue)));
        store.setToolingDtoIntegerMap(node.get("toolings") == null ? null : nodeToMap(node.get("toolings").elements())
                .entrySet().stream().collect(Collectors.toMap(entry -> (ToolingDto) create(new ToolingDto(), entry.getKey()),
                        Map.Entry::getValue)));
        store.setSpecialToolDtoIntegerMap(node.get("specialTools") == null ? null : nodeToMap(node.get("specialTools").elements())
                .entrySet().stream().collect(Collectors.toMap(entry -> (SpecialToolDto) create(new SpecialToolDto(), entry.getKey()),
                        Map.Entry::getValue)));
        return store;
    }

    private Map<Long, Integer> nodeToMap(Iterator<JsonNode> elements) {
        var map = new HashMap<Long, Integer>();
        while (elements.hasNext()) {
            var elementNode = elements.next();
            map.put(elementNode.get("id") == null ? null : elementNode.get("id").longValue(),
                    elementNode.get("amount") == null ? null : elementNode.get("amount").intValue());
        }
        return map;
    }

    private AbstractDto create(AbstractDto dto, Long id) {
        dto.setId(id);
        return dto;
    }
}