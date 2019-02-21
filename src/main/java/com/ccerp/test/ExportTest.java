package com.ccerp.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportTest {
	
	public static void main(String[] args) {
		
		
		
		ExportTest Test= new ExportTest();
		
		try {
			Test.qqqqq();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("===========finish=================");
		
		
		
		
		
		
		
		
	}
	
	
	

	public void qqqqq() throws IOException {
		 Workbook wb = new HSSFWorkbook();
		if("WX".equalsIgnoreCase("WX")){
			
	         Sheet sheet = wb.createSheet("sheet");
	         /*for(int i=0;i<keys.length;i++){
	                sheet.setColumnWidth((short) i, (short) (35.7 * 150));
	         }*/
	         Row row = sheet.createRow((short) 0);
	         CellStyle cs = wb.createCellStyle();
	         CellStyle cs2 = wb.createCellStyle();
	         
	         Font f = wb.createFont();
	         Font f2 = wb.createFont();
	         
	         f.setFontHeightInPoints((short) 10);
	         f.setColor(IndexedColors.BLACK.getIndex());
	         f.setBoldweight(Font.BOLDWEIGHT_BOLD);
	         f2.setFontHeightInPoints((short) 10);
	         f2.setColor(IndexedColors.BLACK.getIndex());
	         
	         
	        cs.setFont(f);
            cs.setBorderLeft(CellStyle.BORDER_THIN);
            cs.setBorderRight(CellStyle.BORDER_THIN);
            cs.setBorderTop(CellStyle.BORDER_THIN);
            cs.setBorderBottom(CellStyle.BORDER_THIN);
            cs.setBottomBorderColor(HSSFColor.YELLOW.index);
            cs.setAlignment(CellStyle.ALIGN_CENTER);

            // 设置第二种单元格的样式（用于值）
            cs2.setFont(f2);
            cs2.setBorderLeft(CellStyle.BORDER_THIN);
            cs2.setBorderRight(CellStyle.BORDER_THIN);
            cs2.setBorderTop(CellStyle.BORDER_THIN);
            cs2.setBorderBottom(CellStyle.BORDER_THIN);
            cs2.setAlignment(CellStyle.ALIGN_CENTER);
            
        	String [] names =new String []{"序号","代垫单位代码","代垫单位名称","期间","支付单位代码","支付单位名称","员工所在部门名称","员工姓名","员工编号","公积金个人","公积金单位"};
			for(int i=0 ;i<11;i++){
				//sheet.autoSizeColumn((short)i); //自动调整列宽
				sheet.setColumnWidth((short)i,256*15);
				Cell cell = row.createCell(i);
				cell.setCellStyle(cs);
				cell.setCellValue(names[i]);
			}
            
        /*    for (int i = 0; i < 10; i++) {
            	   Cell cell = row.createCell(i);
                   cell.setCellValue("你好");
                   cell.setCellStyle(cs);;
			}*/
            
            for (int i = 0; i < 10; i++) {
            	Row row1 =sheet.createRow(i+1);
            	
            	for (int j = 0; j <10; j++) {
            		Cell cell=row1.createCell(j);
        		   cell.setCellValue(i+","+j);
                   cell.setCellStyle(cs2);
				}
         	    
			}
		}
		
		
		
		
		//设置下载时客户端Excel的名称   
        String filename = new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls";
      //处理中文文件名
       // filename = MyUtils.encodeFilename(filename, request);
        FileOutputStream fos = new FileOutputStream(filename);
        OutputStream ouputStream = fos;   
        wb.write(ouputStream);   
        ouputStream.flush();   
        ouputStream.close();  

	}
	

}
