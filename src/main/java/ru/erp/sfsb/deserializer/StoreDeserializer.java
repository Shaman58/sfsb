package ru.erp.sfsb.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.erp.sfsb.dto.*;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

public class StoreDeserializer extends JsonDeserializer<SetupDto> {

    @Override
    public SetupDto deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        var setup = new SetupDto();
        setup.setId(node.get("id") == null ? null : node.get("id").longValue());
        setup.setSetupNumber(node.get("setupNumber") == null ? null : node.get("setupNumber").intValue());
        setup.setSetupName(node.get("setupName") == null ? null : node.get("setupName").asText());
        setup.setSetupTime(Duration.of(node.get("setupTime") == null ? 0 : node.get("setupTime").longValue(), SECONDS));
        setup.setProcessTime(Duration.of(node.get("processTime") == null ? 0 : node.get("processTime").longValue(), SECONDS));
        setup.setInteroperativeTime(Duration.of(node.get("interoperativeTime") == null ? 0 : node.get("interoperativeTime").longValue(), SECONDS));
        setup.setCutterToolDtoList(node.get("cutterToolDtoList") == null ? null : toList(node.get("cutterToolDtoList").elements())
                .stream().map(e -> (CutterToolDto) create(new CutterToolDto(), e)).toList());
        setup.setMeasureToolDtoList(node.get("measureToolDtoList") == null ? null : toList(node.get("measureToolDtoList").elements())
                .stream().map(e -> (MeasureToolDto) create(new MeasureToolDto(), e)).toList());
        setup.setAdditionalToolDtoList(node.get("additionalToolDtoList") == null ? null : toList(node.get("additionalToolDtoList").elements())
                .stream().map(e -> (AdditionalToolDto) create(new AdditionalToolDto(), e)).toList());
        setup.setSpecialToolDtoList(node.get("specialToolDtoList") == null ? null : toList(node.get("specialToolDtoList").elements())
                .stream().map(e -> (SpecialToolDto) create(new SpecialToolDto(), e)).toList());
        setup.setToolingDtoList(node.get("toolingDtoList") == null ? null : toList(node.get("toolingDtoList").elements())
                .stream().map(e -> (ToolingDto) create(new ToolingDto(), e)).toList());
        setup.setProductionUnitDto((ProductionUnitDto) create(new ProductionUnitDto(),
                node.get("productionUnitDto") == null ? null : node.get("productionUnitDto").get("id") == null ? null : node.get("productionUnitDto").get("id").longValue()));
        return setup;
    }

    private List<Long> toList(Iterator<JsonNode> elements) {
        var list = new ArrayList<Long>();
        while (elements.hasNext()) {
            list.add(elements.next().get("id").numberValue().longValue());
        }
        return list;
    }

    private AbstractDto create(AbstractDto dto, Long id) {
        dto.setId(id);
        return dto;
    }
}