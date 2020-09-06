package com.leadway.ps;

import com.leadway.ps.model.Record;
import com.leadway.ps.model.StatementRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.awt.Color;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Dev.io
 */
public final class ExcelFile {

    String outFile;
    String pin;
    public static final String FOLDER = new File("files").getAbsolutePath() + File.separator + "statements";
    static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MMM-yyyy");
    static final SimpleDateFormat SF = new SimpleDateFormat("MMM-yyyy");
    private final StatementRequest request;
    private final String[] HEADERS = {
        "SURNAME", "FIRSTNAME", "MIDDLENAME", "RSA_PIN", "EMPLOYER_CODE", "FUND_CODE",
        "FUND_UNIT_PRICE", "TOTAL_FUND_UNITS", "RSA_BALANCE", "RSA_GAIN_LOSS"
    };
    private final String[] SUB_HEADERS = {
        "PAY_RECEIVE_DATE", "RELATED_MONTH_START", "RELATED_MONTH_END", "TRANSACTION_TYPE",
        "EMPLOYER_CONTRIBUTION", "EMPLOYEE_CONTRIBUTION", "VOLUNTARY_CONTINGENT",
        "VOLUNTARY_RETIREMENT", "OTHER_INFLOWS", "TOTAL_CONTRIBUTIONS", "NUMBER_OF_UNITS",
        "FEES", "OTHER_WITHDRAWALS", "NET_CONTRIBUTIONS", "RELATED_PFA_CODE"
    };

    public ExcelFile(StatementRequest request) {
        this.request = request;
        this.pin = request.getPin();
        outFile = FOLDER + File.separator + output("xlsx");
    }

    public Fault toFile() throws Exception {
        StringBuilder errors = new StringBuilder();
        int success = 0;
        try {
            XSSFWorkbook workbook = (XSSFWorkbook) ExcelBuilder.createWorkBookForPath(outFile);
            XSSFSheet sheet = ExcelBuilder.newSheet(FOLDER, outFile, HEADERS, workbook);
            CellStyle style = ExcelBuilder.newStyle(workbook, null, false);
            int rowCount = 1;
            addHeaderContent(workbook, rowCount++, sheet);
            ExcelBuilder.createHeaderRow(workbook, sheet, SUB_HEADERS, rowCount++);
            addContent(rowCount, sheet, style);
            writeFile(workbook, outFile);
        } catch (IOException ex) {
            System.out.println("error writing transaction history file " + ex);
            return new Fault("PS3", ex.getMessage());
        }
        return buildFault(errors, success);
    }

    private void addHeaderContent(XSSFWorkbook workbook, int rowCount, XSSFSheet sheet) {
        Row row = sheet.createRow(rowCount);
        CellStyle style = ExcelBuilder.newStyle(workbook, new XSSFColor(Color.RED), true);
        int columnCount = 0;
        ExcelBuilder.createCell(columnCount, row, request.getSurname(), style);
        ExcelBuilder.createCell(++columnCount, row, request.getFirstName(), style);
        ExcelBuilder.createCell(++columnCount, row, request.getMiddleName(), style);
        ExcelBuilder.createCell(++columnCount, row, request.getPin(), style);
        ExcelBuilder.createCell(++columnCount, row, request.getEmployer(), style);
        ExcelBuilder.createCell(++columnCount, row, request.getCode(), style);
        ExcelBuilder.createCell(++columnCount, row, request.getPrice(), style);
        ExcelBuilder.createCell(++columnCount, row, request.getUnits(), style);
        ExcelBuilder.createCell(++columnCount, row, request.getBalance(), style);
        ExcelBuilder.createCell(++columnCount, row, request.getEarning(), style);
    }

    private void addContent(int rowCount, XSSFSheet sheet, CellStyle style) {
        Row row;
        for (Record record : request.getRecords()) {
            row = sheet.createRow(++rowCount);
            int columnCount = 0;
            ExcelBuilder.createCell(columnCount, row, SDF.format(record.getDateReceived()), style);
            ExcelBuilder.createCell(++columnCount, row, SDF.format(record.getMonthStart()), style);
            ExcelBuilder.createCell(++columnCount, row, SF.format(record.getMonthEnd()), style);
            ExcelBuilder.createCell(++columnCount, row, record.getType(), style);
            ExcelBuilder.createCell(++columnCount, row, record.getEmployer(), style);
            ExcelBuilder.createCell(++columnCount, row, record.getContribution(), style);
            ExcelBuilder.createCell(++columnCount, row, record.getVoluntaryContigent(), style);
            ExcelBuilder.createCell(++columnCount, row, record.getVoluntaryRetirement(), style);
            ExcelBuilder.createCell(++columnCount, row, record.getOtherInflows(), style);
            ExcelBuilder.createCell(++columnCount, row, record.getTotal(), style);
            ExcelBuilder.createCell(++columnCount, row, record.getUnits(), style);
            ExcelBuilder.createCell(++columnCount, row, record.getFees(), style);
            ExcelBuilder.createCell(++columnCount, row, record.getWithdrawals(), style);
            ExcelBuilder.createCell(++columnCount, row, record.getNet(), style);
            ExcelBuilder.createCell(++columnCount, row, record.getPfa(), style);
        }
    }

    private Fault buildFault(StringBuilder errors, int success) {
        String url = "/files/paycode/".concat(outFile);
        String msg = "File production successful";
        String display = success > 0 ? msg : errors.toString();
        return new Fault(success > 0 ? "00" : "15", display, success > 0 ? url : "#");
    }

    private void writeFile(XSSFWorkbook workbook, String file) throws IOException {
        if (workbook == null) {
            return;
        }
        try (FileOutputStream os = new FileOutputStream(file)) {
            workbook.write(os);
        }
    }

    private String output(String ext) {
        String extension = ext != null && ext.equals("xls") ? "xlsx" : ext;
        return String.format("%s.%s", pin, extension);
    }
}
