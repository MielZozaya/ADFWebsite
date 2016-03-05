/*
 * Source From: http://poi.apache.org/spreadsheet/how-to.html#user_api
 * Edited by: Miel Zozaya Garcia
 */
package adf.page.Administrator;

import adf.model.ViewHistory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author miel
 */
public class ConvertToXls {

    public static void convertViewHistory(File file, List<ViewHistory> viewHistoryList) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            // create a new workbook
            HSSFWorkbook wb = new HSSFWorkbook();
            // create a new sheet
            HSSFSheet s = wb.createSheet();
            // declare a row object reference
            Row r = null;
            // declare a cell object reference
            Cell c = null;
            // create 3 cell styles
            CellStyle cs = wb.createCellStyle();
            CellStyle cs2 = wb.createCellStyle();
            DataFormat df = wb.createDataFormat();
            // create 2 fonts objects
            Font f = wb.createFont();
            Font f2 = wb.createFont();
            //set font 1 to 12 point type
            f.setFontHeightInPoints((short) 12);
            // make it bold
            //arial is the default font
            f.setBoldweight(Font.BOLDWEIGHT_BOLD);
            //set font 2 to 10 point type
            f2.setFontHeightInPoints((short) 10);
            //make it bold
            f2.setBoldweight(Font.BOLDWEIGHT_NORMAL);
            //set cell stlye
            cs.setFont(f);
            //set the cell format
            cs.setDataFormat(df.getFormat("#,##0.0"));
            //set a thin border
            cs2.setBorderBottom(cs2.BORDER_THIN);
            // set the font
            cs2.setFont(f2);
            // set the sheet name in Unicode
            wb.setSheetName(0, "\u0422\u0435\u0441\u0442\u043E\u0432\u0430\u044F " + "\u0421\u0442\u0440\u0430\u043D\u0438\u0447\u043A\u0430");
            // in case of plain ascii
            // wb.setSheetName(0, "HSSF Test");
            
            
            // create header cells
            r = s.createRow(0);
            c = r.createCell(0);
            c.setCellStyle(cs);
            c.setCellValue("Username");
            c = r.createCell(1);
            c.setCellStyle(cs);
            c.setCellValue("Start Date");
            c = r.createCell(2);
            c.setCellStyle(cs);
            c.setCellValue("End Date");
            c = r.createCell(3);
            c.setCellStyle(cs);
            c.setCellValue("Duration (s)");
            c = r.createCell(4);
            c.setCellStyle(cs);
            c.setCellValue("Page Url");
            // create a sheet with 30 rows (0-29)

            int rownum = 0;
            for (ViewHistory viewHistory : viewHistoryList) {
                rownum++;
                // create a row
                r = s.createRow(rownum);
                // Fill the cells of the rows
                c = r.createCell(0);
                c.setCellStyle(cs2);
                c.setCellValue(viewHistory.getUsername());
                c = r.createCell(1);
                c.setCellStyle(cs2);
                c.setCellValue(viewHistory.getStartDate().toString());
                c = r.createCell(2);
                c.setCellStyle(cs2);
                c.setCellValue(viewHistory.getEndDate().toString());
                c = r.createCell(3);
                c.setCellStyle(cs2);
                c.setCellValue((int)((viewHistory.getEndDate().getTime()-viewHistory.getStartDate().getTime())/1000));
                c = r.createCell(4);
                c.setCellStyle(cs2);
                c.setCellValue(viewHistory.getPageUrl());
            }
            wb.write(out);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConvertToXls.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (IOException ex) {
            Logger.getLogger(ConvertToXls.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        }
    }
}
