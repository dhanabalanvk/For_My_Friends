/**
 * 
 */
package com.dhana.rajesh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author DhanabalanV
 *
 */
public class convertToXlsx {

	static void convertToXlsx(File inputFile, File outputFile) {
		// For storing data into CSV files
		StringBuffer cellValue = new StringBuffer();
		try {
			FileOutputStream fos = new FileOutputStream(outputFile);

			// Get the workbook instance for XLSX file
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(inputFile));

			// Get first sheet from the workbook
			XSSFSheet sheet = wb.getSheetAt(0);

			Row row;
			Cell cell;

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				row = rowIterator.next();

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_BOOLEAN:
						cellValue.append(cell.getBooleanCellValue() + ",");
						break;

					case Cell.CELL_TYPE_NUMERIC:
						cellValue.append(cell.getNumericCellValue() + ",");
						break;

					case Cell.CELL_TYPE_STRING:
						cellValue.append(cell.getStringCellValue() + ",");
						break;

					case Cell.CELL_TYPE_BLANK:
						cellValue.append("" + ",");
						break;

					default:
						cellValue.append(cell + ",");

					}
				}
			}

			fos.write(cellValue.toString().getBytes());
			fos.close();

		} catch (Exception e) {
			System.err.println("Exception :" + e.getMessage());
		}
	}

	static void convertToXls(File inputFile, File outputFile) {
		// For storing data into CSV files
		StringBuffer cellDData = new StringBuffer();
		try {
			FileOutputStream fos = new FileOutputStream(outputFile);

			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
					inputFile));
			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			Cell cell;
			Row row;

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_BOOLEAN:
						cellDData.append(cell.getBooleanCellValue() + ",");
						break;

					case Cell.CELL_TYPE_NUMERIC:
						cellDData.append(cell.getNumericCellValue() + ",");
						break;

					case Cell.CELL_TYPE_STRING:
						cellDData.append(cell.getStringCellValue() + ",");
						break;

					case Cell.CELL_TYPE_BLANK:
						cellDData.append("" + ",");
						break;

					default:
						cellDData.append(cell + ",");

					}
				}
			}

			fos.write(cellDData.toString().getBytes());
			fos.close();

		} catch (FileNotFoundException e) {
			System.err.println("Exception" + e.getMessage());
		} catch (IOException e) {
			System.err.println("Exception" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		File inputFile2 = new File("C:\\test.xlsx");
		File outputFile2 = new File("C:\\output2.csv");
		convertToXlsx(inputFile2, outputFile2);
	}
}
