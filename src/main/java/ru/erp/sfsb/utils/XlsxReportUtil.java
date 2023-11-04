package ru.erp.sfsb.utils;

import jakarta.servlet.ServletOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.max;

@Slf4j
public class XlsxReportUtil {

    private final XSSFWorkbook xls;
    private static final int MIN_LENGTH = 15;

    public XlsxReportUtil() throws IOException {
        this.xls = new XSSFWorkbook();
    }

    public void save(ServletOutputStream fos) throws IOException {
        this.xls.write(fos);
    }

    public void fillXlsxDocument(List<List<String>> data) {
        var sheet = xls.createSheet("Manufacturing Report");
        var cellStyle = getCellStyleBorder();

        int rowNum = 0;
        setCustomColumnWidth(sheet, data.get(0));
        for (List<String> list : data) {
            var dataRow = sheet.createRow(rowNum++);
            for (int i = 0; i < list.size(); i++) {
                createCellWithStyle(dataRow, cellStyle, i, list.get(i));
            }
        }
    }

    private void setCustomColumnWidth(Sheet sheet, List<String> headers) {
        for (int i = 0; i < headers.size(); i++) {
            sheet.setColumnWidth(i, max(((headers.get(i).length() + 2) * 256 / 2), MIN_LENGTH * 256));
        }
    }

    private CellStyle getCellStyleBorder() {
        var cellStyle = xls.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    private void createCellWithStyle(XSSFRow row, CellStyle cellStyle, int index, String value) {
        var cell = row.createCell(index);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }
}
