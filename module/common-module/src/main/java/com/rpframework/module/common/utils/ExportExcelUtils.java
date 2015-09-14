package com.rpframework.module.common.utils;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportExcelUtils {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final String exportExcel(HttpServletResponse response,
			String fileName, String sheetTitle, List<Map<String, Object>>... lists) {
		String result = "系统提示：Excel文件导出成功！";
		try {
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));

			response.setContentType("application/msexcel");

			WritableWorkbook workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet(sheetTitle, 0);

			SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD);

			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf_center.setAlignment(Alignment.CENTRE);
			wcf_center.setWrap(false);

			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN);
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf_left.setAlignment(Alignment.LEFT);
			wcf_left.setWrap(false);

			
			int i = 0;
			for(List<Map<String, Object>> list : lists) {
				Map<String, Object> titles = (Map<String, Object>) list.get(0);

				int sourceI = i;
				i = 0;
				for (Map.Entry e : titles.entrySet()) {
					sheet.addCell(new Label(i, sourceI, (String) e.getKey(), wcf_center));
					i++;
				}

				i = sourceI + 1;

				for (Map<String, Object> m : list) {
					int j = 0;
					for (Map.Entry e : m.entrySet()) {
						String v = null;
						if (e.getValue() == null)
							v = "";
						else {
							v = e.getValue().toString();
						}
						sheet.addCell(new Label(j, i, v, wcf_left));
						j++;
					}
					i++;
				}
			}

			workbook.write();

			workbook.close();
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}
}
