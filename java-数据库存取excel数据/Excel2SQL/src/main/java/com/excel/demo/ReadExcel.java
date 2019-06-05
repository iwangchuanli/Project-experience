package com.excel.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

public class ReadExcel {

	public static void main(String[] args) throws Exception {
		File file = new File("E:\\temp\\file\\1_week01.xls");
		List excelList = readExcel(file);
		Map<String, String> bankMap = new HashMap<String, String>();
		bankMap.put("上海银行股份有限公司", "上海银行");
		String placeList = "余杭淳安桐庐建德富阳临安";
		OutputStream out = new FileOutputStream(new File("E:\\temp\\file\\1_week01_.txt"));
		// 输出
		for (int i = 0; i < excelList.size(); i++) {
			List list = (List) excelList.get(i);
			for (int j = 0; j < list.size(); j++) {
				String place = (String) list.get(j);
				if (placeList.contains(place)) {
					place = (String) list.get(j) + "  ";
					System.out.println(place);
					out.write(place.getBytes());
					String bank = (String) list.get(j + 2);
					if (bankMap.containsKey(bank)) {
						list.add(j + 2, bankMap.get(bank));
					}
					// System.out.print(list.get(j + 2));
					bank = (String) list.get(j + 2) + "  ";
					// System.out.println(list.get(j + 1));
					String num = (String) list.get(j + 1);

					out.write(bank.getBytes());
					out.write(num.getBytes());
					out.write("\r\n".getBytes());
				}
			}
		}

	}

	// 去读Excel的方法readExcel，该方法的入口参数为一个File对象
	public static List readExcel(File file) {
		try {
			// 创建输入流，读取Excel
			InputStream is = new FileInputStream(file.getAbsolutePath());
			// jxl提供的Workbook类
			Workbook wb = Workbook.getWorkbook(is);
			// Excel的页签数量
			int sheet_size = wb.getNumberOfSheets();
			System.out.println("页签数量:" + sheet_size);
			List<List> outerList = new ArrayList<List>();
			for (int index = 0; index < sheet_size; index++) {
				// 每个页签创建一个Sheet对象
				Sheet sheet = wb.getSheet(index);
				// System.out.println(sheet.getName());
				// sheet.getRows()返回该页的总行数
				for (int i = 0; i < sheet.getRows(); i++) {
					List innerList = new ArrayList();
					innerList.add(sheet.getName());
					// sheet.getColumns()返回该页的总列数
					for (int j = 0; j < sheet.getColumns(); j++) {
						String cellinfo = sheet.getCell(j, i).getContents();
						if (cellinfo.isEmpty()) {
							continue;
						}
						innerList.add(cellinfo);
						// System.out.print(cellinfo);
					}
					outerList.add(i, innerList);
					// System.out.println();
				}
			}
			return outerList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
