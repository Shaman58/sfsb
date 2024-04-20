package ru.erp.sfsb.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.erp.sfsb.LogTag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static java.lang.Math.max;

@Slf4j
public class XlsxReportUtil {
    private static final int MIN_LENGTH = 15;
    private final static LogTag LOG_TAG = LogTag.XLSX_REPORT_UTIL;

    public static byte[] saveToFile(List<List<String>> data) throws IOException {
        log.info("[{}] Сохранение Workbook файла", LOG_TAG);
        var xls = new XSSFWorkbook();
        fillXlsxDocument(xls, data);
        var byteArrayOutputStream = new ByteArrayOutputStream();
        xls.write(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private static void fillXlsxDocument(XSSFWorkbook xls, List<List<String>> data) {
        log.info("[{}] Заполнение документа", LOG_TAG);
        var sheet = xls.createSheet("Manufacturing Report");
        var cellStyle = getCellStyleBorder(xls);

        int rowNum = 0;
        setCustomColumnWidth(sheet, data.get(0));
        for (List<String> list : data) {
            var dataRow = sheet.createRow(rowNum++);
            for (int i = 0; i < list.size(); i++) {
                createCellWithStyle(dataRow, cellStyle, i, list.get(i));
            }
        }
    }

    private static void setCustomColumnWidth(Sheet sheet, List<String> headers) {
        for (int i = 0; i < headers.size(); i++) {
            sheet.setColumnWidth(i, max(((headers.get(i).length() + 2) * 256 / 2), MIN_LENGTH * 256));
        }
    }

    private static CellStyle getCellStyleBorder(XSSFWorkbook xls) {
        var cellStyle = xls.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    private static void createCellWithStyle(XSSFRow row, CellStyle cellStyle, int index, String value) {
        var cell = row.createCell(index);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }
}