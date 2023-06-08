package ru.erp.sfsb.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.erp.sfsb.dto.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StoreDeserializer extends JsonDeserializer<StoreDto> {

    @Override
    public StoreDto deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        var store = new StoreDto();
        JsonNode node = parser.getCodec().readTree(parser);
        store.setId(node.path("id").asLong());
        store.setStoreName(node.path("storeName").asText());
        store.setWorkpieces(nodeToMap(node.path("workpieces").elements(), WorkpieceDto::new));
        store.setCutterTools(nodeToMap(node.path("cutterTools").elements(), CutterToolDto::new));
        store.setMeasureTools(nodeToMap(node.path("measureTools").elements(), MeasureToolDto::new));
        store.setToolings(nodeToMap(node.path("toolings").elements(), ToolingDto::new));
        store.setSpecialTools(nodeToMap(node.path("specialTools").elements(), SpecialToolDto::new));
        return store;
    }

    private static <T extends AbstractDto> Map<T, Integer> nodeToMap(Iterator<JsonNode> elements, Supplier<T> dtoSupplier) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(elements, Spliterator.ORDERED), false)
                .collect(Collectors.toMap(node -> setId(dtoSupplier.get(), node.path("id").asLong()), node -> node.path("amount").asInt()));
    }

    private static <T extends AbstractDto> T setId(T dto, Long id) {
        dto.setId(id);
        return dto;
    }
}