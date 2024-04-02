package ru.erp.sfsb.utils;

import jakarta.servlet.ServletOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.DocumentFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import ru.erp.sfsb.LogTag;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
public class DocxReportUtil {

    private final XWPFDocument doc;
    private final static LogTag LOG_TAG = LogTag.DOCX_REPORT_UTIL;

    public DocxReportUtil(InputStream is) throws IOException {
        this.doc = new XWPFDocument(is);
    }

    public void save(ServletOutputStream fos) throws IOException {
        log.info("[{}] Сохранение WordDocument", LOG_TAG);
        this.doc.write(fos);
    }

    public void generateCp(Map<String, String> company, List<Map<String, String>> itemList, Map<String, String> bodyData, byte[] image)
            throws IOException, InvalidFormatException {
        log.info("[{}] Генерация отчета по заявке", LOG_TAG);
        this.writeCompanyHeader(company);
        this.addItemsTable(itemList, 1);
        this.fillText(bodyData.get("body"), "body");
        this.addImage(image);
        writeCompanyHeader(company);
        addImage(image);
        fillRun(bodyData);
        addItemsTable(itemList, 1);
    }

    public void generateToolOrder(List<Map<String, String>> tools, String header, String body, String footer) {
        log.info("[{}] Генерация отчета по инструментам", LOG_TAG);
        fillText(header, "[header]");
        fillText(body, "[body]");
        addItemsTable(tools, 0);
        fillText(footer, "[footer]");
        fillText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), "[date]");
    }

    private void fillText(String text, String tag) {
        var run = getTargetRun(tag);
        run.setText(run.getText(0).replace(tag, ""), 0);
        var lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            run.setText(lines[i]);
            if (i != lines.length - 1) {
                run.addBreak();
            }
        }
    }

    private void addItemsTable(List<Map<String, String>> itemList, int tableNumber) {
        XWPFTable table = this.doc.getTables().get(tableNumber);
        var targetRowIndex = getTargetRowIndex(table, itemList.get(0).entrySet().stream().findFirst()
                .orElseThrow(() -> new DocumentFormatException(
                        String.format("[%s} Список инструментов не найден", LOG_TAG)))
                .getKey());
        for (Map<String, String> item : itemList) {
            var row = new XWPFTableRow((CTRow) table.getRows().get(targetRowIndex).getCtRow().copy(), table);
            feelRow(row, item);
            table.addRow(row);
        }
        table.removeRow(targetRowIndex);
    }

    private void feelRow(XWPFTableRow row, Map<String, String> item) {
        for (XWPFTableCell cell : row.getTableCells()) {
            setRunsFromMap(cell.getParagraphs().get(0).getRuns().get(0), item);
        }
    }

    private int getTargetRowIndex(XWPFTable table, String text) {
        for (int i = 0; i < table.getNumberOfRows(); i++) {
            for (XWPFTableCell cell : table.getRow(i).getTableCells()) {
                if (cell.getText().toLowerCase().contains(text)) {
                    return i;
                }
            }
        }
        throw new DocumentFormatException(String.format("[%s] Метка %s не найдена", LOG_TAG, text));
    }

    private XWPFRun getTargetRun(String text) {
        for (XWPFParagraph paragraph : this.doc.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                if (run.getText(0).toLowerCase().contains(text)) {
                    return run;
                }
            }
        }
        throw new DocumentFormatException(String.format("[%s] Метка %s не найдена", LOG_TAG, text));
    }

    private void writeCompanyHeader(Map<String, String> companyMap) {
        XWPFTable table = this.doc.getTables().get(0);
        table.getRows()
                .forEach(row -> row.getTableCells()
                        .forEach(cell -> cell.getParagraphs()
                                .forEach(paragraph -> paragraph.getRuns()
                                        .forEach(run -> setRunsFromMap(run, companyMap)))));
    }

    private void setRunsFromMap(XWPFRun run, Map<String, String> map) {
        var tag = run.getText(0).replace(" ", "");
        if (map.containsKey(tag)) {
            tag = tag.replace(tag, map.get(tag));
            run.setText(tag, 0);
        }
    }

    private void addImage(byte[] file) {
        if (file != null) {
            var cell = this.doc.getTables().get(0).getRow(0).getCell(0);
            XWPFParagraph paragraph = cell.getParagraphs().get(0);
            XWPFRun run = paragraph.createRun();
            try {
                var is = new ByteArrayInputStream(file);
                run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, "image.png", Units.toEMU(149), Units.toEMU(32));
            } catch (Exception e) {
                log.info("[{}] Изображение не загружено!", LOG_TAG);
            }
        }
    }

    private void fillRun(Map<String, String> data) {
        for (XWPFParagraph paragraph : this.doc.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                var tag = run.getText(0).replace(" ", "");
                if (data.containsKey(tag)) {
                    run.setText(data.get(tag), 0);
                }
            }
        }
    }
}