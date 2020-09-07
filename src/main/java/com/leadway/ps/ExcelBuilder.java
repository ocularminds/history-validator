package com.leadway.ps;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 *
 * @author Jejelowo B. Festus <festus.jejelowo@ocularminds.com>
 */
public class ExcelBuilder {

    public static Workbook createWorkBookForPath(String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }

    public static XSSFSheet newSheet(String folder, String outFile, String[] headers, XSSFWorkbook workbook) throws Exception {
        if (!new File(folder).exists()) {
            System.out.println(folder + " does not exist. creating it...");
            new File(folder).mkdirs();
        }
        XSSFSheet sheet = workbook.createSheet("HISTORY");
        ExcelBuilder.createHeaderRow(workbook, sheet, headers, 0);
        return sheet;
    }

    public static CellStyle newStyle(XSSFWorkbook workbook, XSSFColor color, boolean isBold){
       return newStyle(workbook,color,isBold,null,null);
    }

    public static CellStyle newStyle(XSSFWorkbook workbook, XSSFColor color, boolean isBold, IndexedColors background, HorizontalAlignment ha) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        if (isBold) {
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        }
        if(background != null){
           style.setFillBackgroundColor(background.getIndex());
        }
        if(ha != null){
           switch(ha){
                case RIGHT:
                style.setAlignment((short)3);
                break;
                case CENTER:
                style.setAlignment((short)2);
                break;
                default:
                break;
            }
        }
        font.setFontHeightInPoints((short) 11);
        font.setColor(color);
        style.setFont(font);

        return style;
    }

    public static void createHeaderRow(Workbook wb, Sheet sheet, String[] headers, int rownum) {
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);

        Row row = sheet.createRow(rownum);
        for (int x = 0; x < headers.length; x++) {
            createCell(x, row, headers[x], cellStyle);
        }
    }

    @SuppressWarnings("deprecated")
    public static void createHeaderTitle(Workbook wb, Sheet sheet, String title, int columns) {
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cs = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 30);
        cellStyle.setFont(font);
        Font font2 = wb.createFont();
        font2.setFontHeightInPoints((short) 20);
        cs.setFont(font2);

        Row row = sheet.createRow(1);
        row.setHeightInPoints(30);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        createCell(1, row, title, cellStyle);

        sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
                4, // last row (0-based)
                1, // first column (0-based)
                columns // last column (0-based)
        ));
        String date;
        date = new SimpleDateFormat("EEE, MMM d, yyyy K:mm a").format(new java.util.Date());
        row = sheet.createRow(5);
        row.setHeightInPoints(25);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        createCell(1, row, date, cs);
        sheet.addMergedRegion(new CellRangeAddress(columns - 2, // first row (0-based)
                columns - 1, // last row (0-based)
                1, // first column (0-based)
                columns // last column (0-based)
        ));
    }

    public static void createCell(int col, Row row, Object obj, CellStyle cs) {
        createCell(col, row, obj, cs, false);
    }

    public static void createCell(int col, Row row, Object obj, CellStyle cs, boolean painted) {
        Cell cell = row.createCell(col);
        if (obj instanceof Integer) {
            cell.setCellValue((Integer) obj);
        } else if (obj instanceof Long) {
            cell.setCellValue((Long) obj);
        } else if (obj instanceof Double) {
            cell.setCellValue((Double) obj);
        } else if (obj instanceof Float) {
            cell.setCellValue((Float) obj);
        } else if (obj instanceof BigDecimal) {
            cell.setCellValue((Double) ((BigDecimal) obj).doubleValue());
        } else {
            cell.setCellValue((String) obj);
        }
        cell.setCellStyle(cs);

        cs.setFillForegroundColor(HSSFColor.YELLOW.index);
//cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }
}
