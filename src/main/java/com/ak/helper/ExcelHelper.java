/**
 * 
 */
package com.ak.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.ak.entity.Product;

/**
 * @author Akshay Mandage
 *
 */
public class ExcelHelper {

	// check that file is of excel format
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}

	// create excel to list of product
	@SuppressWarnings("resource")
	public static List<Product> converExcelToListOfProduct(InputStream is) {

		List<Product> list = new ArrayList<>();
		try {
//			XSSFWorkbook workbook = new XSSFWorkbook(is);
//			XSSFSheet sheet = workbook.getSheet("Sheet1");

			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			int rowNumber = 0;

			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cells = row.iterator();

				int cid = 0;
				Product p = new Product();
				while (cells.hasNext()) {

					Cell cell = cells.next();

					switch (cid) {
					case 0:
						p.setPid((int) cell.getNumericCellValue());
						break;
					case 1:
						p.setPname(cell.getStringCellValue());
						break;
					case 2:
						p.setPDesc(cell.getStringCellValue());
						break;
					case 3:
						p.setPrice(cell.getNumericCellValue());
						break;
					default:
						break;
					}
					cid++;
				}
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
