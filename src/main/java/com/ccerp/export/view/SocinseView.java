package com.ccerp.export.view;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.ccerp.bean.services.SocinsServicesBeanBase;

@SuppressWarnings("deprecation")
public class SocinseView extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	public void buildExcelDocument(Map<String, Object> map,HSSFWorkbook workBook, HttpServletRequest req,HttpServletResponse rep) throws Exception {
		List<SocinsServicesBeanBase> list = (List<SocinsServicesBeanBase>) map.get("socinseList");
		
		
		
		Sheet sheet = workBook.createSheet("Sheet1");
		Row row = sheet.createRow((short) 0);
		CellStyle cs = workBook.createCellStyle();
		CellStyle cs2 = workBook.createCellStyle();
		Font f = workBook.createFont();
		Font f2 = workBook.createFont();
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
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		cs2.setAlignment(CellStyle.ALIGN_CENTER);
		
	
		String [] names =new String []{"序号","代垫单位","生效日期","支付单位","员工所在部门","员工姓名",
				"员工编号","养老个人","医疗个人","失业个人","大额医疗保险个人","养老单位","医疗单位","失业单位","工伤单位","生育单位","大额医疗保险单位"};
		
		for(int i=0 ;i<17;i++){
			Cell cell = row.createCell(i);
			sheet.setColumnWidth((short)i,256*15);
			cell.setCellStyle(cs);
			cell.setCellValue(names[i]);
		}
		
		if(list!=null &&list.size()>0){
			
			for (int i = 0; i < list.size(); i++) {
				Row row1 = sheet.createRow(i + 1);
				SocinsServicesBeanBase ssb = list.get(i);
				for (int j = 0; j < 17; j++) {
					Cell cell = row1.createCell(j);
					cell.setCellStyle(cs2);
					if (j == 0) {
						cell.setCellValue(ssb.getRowNum());
					}
					if (j == 1) {
						cell.setCellValue(ssb.getPayOrg2Name());
					}
					if (j == 2) {
						cell.setCellValue(ssb.getPeriodName());
					}
					if (j == 3) {
						cell.setCellValue(ssb.getOrg2Name());
					}
					if (j ==4) {
						cell.setCellValue(ssb.getOrgName());
					}
					if (j == 5) {
						cell.setCellValue(ssb.getEmpName());
					}
					if (j == 6) {
						cell.setCellValue(ssb.getEmpNum());
					}
					if (j == 7) {
						cell.setCellValue(ssb.getEndowmentEe());
					}
					if (j == 8) {
						cell.setCellValue(ssb.getMedicalEe());
					}
					if (j == 9) {
						cell.setCellValue(ssb.getUnempEe());
					}
					if (j == 10) {
						cell.setCellValue(ssb.getLarmedEe());
					}
					if (j == 11) {
						cell.setCellValue(ssb.getEndowmentEr());
					}
					if (j == 12) {
						cell.setCellValue(ssb.getMedicalEr());
					}
					if (j == 13) {
						cell.setCellValue(ssb.getUnempEr());
					}
					if (j == 14) {
						cell.setCellValue(ssb.getInjEr());
					}
					if (j == 15) {
						cell.setCellValue(ssb.getMaterEr());
					}
					if (j == 16) {
						cell.setCellValue(ssb.getLarmedEr());
					}
				}

			}
			
		}
		String filename = "五险数据导出-"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls";
		String contentDisposition = "";
		try {
			if (req.getHeader("User-Agent").toLowerCase()
					.indexOf("firefox") > 0) {
				contentDisposition = "attachment; filename=\""
						+ new String(filename.getBytes("UTF-8"),
								"ISO8859-1") + "\"";// firefox浏览器
			} else {
				contentDisposition = "attachment; filename=\""
						+ URLEncoder.encode(filename, "UTF-8") + "\"";// IE浏览器
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		rep.setContentType("application/vnd.ms-excel");
		rep.setCharacterEncoding("UTF-8");
		rep.setHeader("Content-disposition", contentDisposition);
		OutputStream ouputStream = rep.getOutputStream();
		workBook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		
	}

}
