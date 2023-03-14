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
		bankMap.put("�Ϻ����йɷ����޹�˾", "�Ϻ�����");
		String placeList = "�ຼ����ͩ®���¸����ٰ�";
		OutputStream out = new FileOutputStream(new File("E:\\temp\\file\\1_week01_.txt"));
		// ���
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

	// ȥ��Excel�ķ���readExcel���÷�������ڲ���Ϊһ��File����
	public static List readExcel(File file) {
		try {
			// ��������������ȡExcel
			InputStream is = new FileInputStream(file.getAbsolutePath());
			// jxl�ṩ��Workbook��
			Workbook wb = Workbook.getWorkbook(is);
			// Excel��ҳǩ����
			int sheet_size = wb.getNumberOfSheets();
			System.out.println("ҳǩ����:" + sheet_size);
			List<List> outerList = new ArrayList<List>();
			for (int index = 0; index < sheet_size; index++) {
				// ÿ��ҳǩ����һ��Sheet����
				Sheet sheet = wb.getSheet(index);
				// System.out.println(sheet.getName());
				// sheet.getRows()���ظ�ҳ��������
				for (int i = 0; i < sheet.getRows(); i++) {
					List innerList = new ArrayList();
					innerList.add(sheet.getName());
					// sheet.getColumns()���ظ�ҳ��������
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
