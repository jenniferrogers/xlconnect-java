package com.miraisolutions.xlconnect;

import com.miraisolutions.xlconnect.data.DataFrame;
import com.miraisolutions.xlconnect.data.DataType;
import com.miraisolutions.xlconnect.utils.Logging;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Logging.withLevel(Level.INFO);

        // org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(new FileInputStream("C:/Users/mstuder/Documents/GP-CTRLPARA-RBC 09 Q2 B1-V8.xls"));
        // Name name = wb.getName("AM.input");

        // Workbook workbookx = Workbook.getWorkbook("C:/Users/mstuder/Documents/GP-CTRLPARA-RBC 09 Q2 B1-V8.xls", false);
        // workbookx.getDefinedNames(true);
        
//        org.apache.poi.ss.usermodel.Workbook wb = new XSSFWorkbook();
//        Sheet sheet = wb.createSheet();
//        Cell c = sheet.createRow(0).createCell(0);
//        org.apache.poi.ss.usermodel.CellStyle cs = wb.createCellStyle();
//        cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        cs.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.SOLID_FOREGROUND);
//        c.setCellStyle(cs);
//        wb.write(new FileOutputStream("C:/temp/repro.xlsx"));
//
//        if(1 == 1) return;


//        XSSFWorkbook wb = new XSSFWorkbook();
//        wb.createSheet("asdf");
//
//        DataFormat dataFormat = wb.createDataFormat();
//
//        // Header style
//        CellStyle headerStyle = wb.createCellStyle();
//        headerStyle.setDataFormat(dataFormat.getFormat("General"));
//        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        headerStyle.setWrapText(true);
//        // String / boolean / numeric style
//        CellStyle style = wb.createCellStyle();
//        style.setDataFormat(dataFormat.getFormat("General"));
//        style.setWrapText(true);
//        // Date style
//        CellStyle dateStyle = wb.createCellStyle();
//        dateStyle.setDataFormat(dataFormat.getFormat("mm/dd/yyyy hh:mm:ss"));
//        dateStyle.setWrapText(true);
//
//        FileOutputStream fos = new FileOutputStream("C:/temp/test.xlsx");
//        wb.write(fos);
//        fos.close();
//
//        if(1 == 1) return;
        
        File excelFile = new File("C:/temp/test.xlsx");
        if(excelFile.exists()) excelFile.delete();

        Vector<String> col1 = new Vector<String>(5);
        col1.add("A");
        col1.add("B");
        col1.add("C");
        col1.add(null);
        col1.add("E");

        Vector<Double> col2 = new Vector<Double>(5);
        for(int i = 0; i < 5; i++) col2.add(new Double(i));
        col2.setElementAt(null, 1);

        Vector<Boolean> col3 = new Vector<Boolean>(5);
        for(int i = 0; i < 5; i++) col3.add(i%2 == 0);
        col3.setElementAt(null, 2);

        Vector<Date> col4 = new Vector<Date>(5);
        for(int i = 0; i < 5; i++) col4.add(new Date(System.currentTimeMillis()));
        col4.setElementAt(null, 4);

        DataFrame df = new DataFrame();
        df.addColumn("Letter", DataType.String, col1);
        df.addColumn("Value", DataType.Numeric, col2);
        df.addColumn("Logical", DataType.Boolean, col3);
        df.addColumn("DateTime", DataType.DateTime, col4);

        Workbook workbook = Workbook.getWorkbook(excelFile, true);
        workbook.setStyleAction(StyleAction.XLCONNECT);

        // Write named region
        // workbook.createName("Test", "Test!$B$2", true);
        // workbook.writeNamedRegion(df, "Test", true);
        
        workbook.createSheet("Test");

        /*
        // Write worksheet
        workbook.createSheet("Test Data");
        workbook.writeWorksheet(df, "Test Data", 0, 0, true);
        // Add images
        workbook.createName("Mirai1", "Image!$B$3:$D$5", true);
        workbook.createName("Mirai2", "Image!$B$10", true);
        workbook.addImage("C:/temp/mirai_solutions1.jpg", "Mirai1", true);
        workbook.addImage("C:/temp/mirai-solutions2.jpg", "Mirai2", false);

        
        CellStyle cs = workbook.createCellStyle("MyPersonalStyle.Header");
        cs.setBorderBottom(org.apache.poi.ss.usermodel.CellStyle.BORDER_THICK);
        workbook.setStyleAction(StyleAction.STYLE_NAME_PREFIX);
        workbook.setStyleNamePrefix("MyPersonalStyle");
        workbook.createName("Somewhere", "Somewhere!$C$5", true);
        workbook.writeNamedRegion(df, "Somewhere", true);
        **/

        workbook.save();

        /*
        DataFrame res = workbook.readNamedRegion("Test", true);
        printDataFrame(res);

        res = workbook.readWorksheet("Test Data", true);
        printDataFrame(res);
         * 
         */
    }

    public static void printDataFrame(DataFrame df) {
        for(int i = 0; i < df.columns(); i++) {
            System.out.println(df.getColumnName(i) + ":");
            System.out.println(df.getColumn(i).toString());
        }
    }
}
