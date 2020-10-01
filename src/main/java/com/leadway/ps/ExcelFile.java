package com.leadway.ps;

import com.leadway.ps.model.Record;
import com.leadway.ps.model.Statement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.awt.Color;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
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
    private final Statement request;
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

    public ExcelFile(Statement request) {
        this.request = request;
        this.pin = request.getPin();
        outFile = FOLDER + File.separator + output("xlsx");
    }

   //1. Add Footer total  and net contributions
   //2. Summary in uppercase
   //3. Fund unit price in ecimal places - 4
   //4. Data to fix column
   //5. Format amount to comma nd decimal places
   //6. Negative amount to be in parentences
    public Fault toFile() throws Exception {
        StringBuilder errors = new StringBuilder();
        int success = 0;
        try {
            XSSFWorkbook workbook = (XSSFWorkbook) ExcelBuilder.createWorkBookForPath(outFile);
            XSSFSheet sheet = ExcelBuilder.newSheet(FOLDER, outFile, HEADERS, workbook);
            CellStyle[] styles = {
                ExcelBuilder.newStyle(workbook,null,false, IndexedColors.WHITE, HorizontalAlignment.CENTER),
                ExcelBuilder.newStyle(workbook,null,false, IndexedColors.YELLOW, HorizontalAlignment.CENTER),
                ExcelBuilder.newStyle(workbook,null,false, IndexedColors.GREY_50_PERCENT, HorizontalAlignment.RIGHT),
                ExcelBuilder.newStyle(workbook,null,true, IndexedColors.WHITE, HorizontalAlignment.CENTER),
                ExcelBuilder.newStyle(workbook,null,false,IndexedColors.WHITE,HorizontalAlignment.RIGHT),
                ExcelBuilder.newStyle(workbook,null,false,IndexedColors.YELLOW,HorizontalAlignment.RIGHT)
            };

            int rowCount = 1;
            addHeaderContent(workbook, rowCount++, sheet);
            ExcelBuilder.createHeaderRow(workbook, sheet, SUB_HEADERS, rowCount++);
            addContent(rowCount, sheet, styles);
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
        ExcelBuilder.createCell(columnCount, row, upper(request.getSurname()), style);
        ExcelBuilder.createCell(++columnCount, row, upper(request.getFirstName()), style);
        ExcelBuilder.createCell(++columnCount, row, upper(request.getMiddleName()), style);
        ExcelBuilder.createCell(++columnCount, row, upper(request.getPin()), style);
        ExcelBuilder.createCell(++columnCount, row, upper(request.getEmployer()), style);
        ExcelBuilder.createCell(++columnCount, row, upper(request.getCode()), style);
        ExcelBuilder.createCell(++columnCount, row, amountFormat(new BigDecimal(request.getPrice()),4), style);
        ExcelBuilder.createCell(++columnCount, row, amountFormat(request.getUnits(),2), style);
        ExcelBuilder.createCell(++columnCount, row, amountFormat(request.getBalance(),2), style);
        ExcelBuilder.createCell(++columnCount, row, amountFormat(request.getEarning(),2), style);
    }

    private void addContent(int rowCount, XSSFSheet sheet, CellStyle[] style) {
        Row row;
        BigDecimal sumTotal = BigDecimal.ZERO,sumNet=BigDecimal.ZERO;
        for (Record record : request.getRecords()) {
            row = sheet.createRow(rowCount);
            int columnCount = 0;
            ExcelBuilder.createCell(columnCount, row, SDF.format(record.getDateReceived()), style[0]);
            ExcelBuilder.createCell(++columnCount, row, SDF.format(record.getMonthStart()), style[0]);
            ExcelBuilder.createCell(++columnCount, row, SF.format(record.getMonthEnd()), style[1]);
            ExcelBuilder.createCell(++columnCount, row, upper(record.getType()), style[1]);
            ExcelBuilder.createCell(++columnCount, row, amountFormat(record.getEmployer(),2), style[4]);
            ExcelBuilder.createCell(++columnCount, row, amountFormat(record.getContribution(),2), style[4]);
            ExcelBuilder.createCell(++columnCount, row, amountFormat(record.getVoluntaryContigent(),2), style[5]);
            ExcelBuilder.createCell(++columnCount, row, amountFormat(record.getVoluntaryRetirement(),2), style[5]);
            ExcelBuilder.createCell(++columnCount, row, amountFormat(record.getOtherInflows(),2), style[4]);
            ExcelBuilder.createCell(++columnCount, row, amountFormat(record.getTotal(),2), style[2]);
            ExcelBuilder.createCell(++columnCount, row, amountFormat(record.getUnits(),2), style[5]);
            ExcelBuilder.createCell(++columnCount, row, amountFormat(record.getFees(),2), style[4]);
            ExcelBuilder.createCell(++columnCount, row, amountFormat(record.getWithdrawals(),2), style[4]);
            ExcelBuilder.createCell(++columnCount, row, amountFormat(record.getNet(),2), style[2]);
            ExcelBuilder.createCell(++columnCount, row, record.getPfa(), style[1]);
            sumTotal = sumTotal.add(record.getTotal());
            sumNet = sumNet.add(record.getNet());
            ++rowCount;
       }

        row = sheet.createRow(rowCount);
        ExcelBuilder.createCell(9,row,amountFormat(sumTotal,2),style[3]);
        ExcelBuilder.createCell(13,row,amountFormat(sumNet,2),style[3]);
        IntStream.range(0,15).forEach((columnIndex) -> sheet.autoSizeColumn(columnIndex));
    }

    private Fault buildFault(StringBuilder errors, int success) {
        String url = "/files/paycode/".concat(outFile);
        String msg = "File production successful";
        String display = success == 0 ? msg : errors.toString();
        return new Fault(success == 0 ? "00" : "15", display, success == 0 ? url : "#");
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

   private static String upper(String s){
      return s == null?"":s.toUpperCase();
   }

    private static String amountFormat(BigDecimal bd, int decimal){
       bd.setScale(decimal, java.math.RoundingMode.HALF_UP);
       DecimalFormat df = new DecimalFormat("#,###.00");
       df.setMinimumIntegerDigits(1);
       df.setMaximumFractionDigits(decimal);
       String s = df.format(bd);
       if(s.startsWith("-")){
          return "("+s.substring(1,s.length())+")";
       }
       return s;
    }
}
