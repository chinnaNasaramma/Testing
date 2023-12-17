package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil 
{
	Workbook wb;
	public ExcelFileUtil(String Excelpath)throws IOException
	{
		FileInputStream fi = new FileInputStream(Excelpath);
		wb = WorkbookFactory.create(fi);
	}
	public int rowcount(String sheetName)
	{
		int Rowcount=wb.getSheet(sheetName).getLastRowNum();
		return Rowcount;
		
		
	}
	public String getcelldata(String sheetname,int row,int column)
	{
		String data ="";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC) 
		{
			int celldata =(int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data =String.valueOf(celldata);
		}else
		{
			data =wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
		
		
	}
	public void setcelldata(String sheetname,int row,int column,String status,String writeExcelpath) throws IOException  
	{
		Sheet ws = wb.getSheet(sheetname);
		Row rowNum = ws.getRow(row);
		Cell cell=rowNum.createCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass")) 
		{
			CellStyle style =wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column) .setCellStyle(style);
			
			
		}
		else if(status.equalsIgnoreCase("Fail"))
			
		{
			CellStyle style =wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column) .setCellStyle(style);

		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style =wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column) .setCellStyle(style);

		}
		FileOutputStream fo = new FileOutputStream(writeExcelpath);
		wb.write(fo);
		
		
	}
	

}
