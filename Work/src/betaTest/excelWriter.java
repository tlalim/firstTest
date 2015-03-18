package betaTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;






public class excelWriter  {

	public static void writeToExcel(String Date,String time,String username, String msg, String Status) throws IOException {
		
		FileInputStream file = new FileInputStream(new File("u:\\HelpDesk\\soft\\tlalim.xlsx"));
		 
	    XSSFWorkbook workbook = new XSSFWorkbook(file);
	    XSSFSheet sheet = workbook.getSheetAt(0);

        Row row = null;
  
        row = sheet.createRow(sheet.getLastRowNum() + 1);
        
        
        Cell cell = row.createCell(0);
        Cell cell1 = row.createCell(1);
        Cell cell3 = row.createCell(3);
        Cell cell4 = row.createCell(4);
    
        Cell cell6 = row.createCell(6);
    
        cell.setCellValue(Date);
        cell1.setCellValue(time);
        cell3.setCellValue(username);
        cell4.setCellValue(msg);
        cell6.setCellValue(Status);
        file.close();
        try
        {
            
            FileOutputStream out = new FileOutputStream(new File("u:\\HelpDesk\\soft\\tlalim.xlsx"));
            workbook.write(out);
           
            out.close();
           
           
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("problem whis excel");
        }

	}
	

	
	}


