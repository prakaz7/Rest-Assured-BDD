package org.prakash.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class FileReadandWrite {

	public static void writeTextFile(String text) {

		String valueToWrite = text;
		try {

			System.out.println("#####################********* Before Flush *******###########################");
			// *********Read it and flush before ***********//
			BufferedReader readFile = new BufferedReader(new FileReader("Data.txt"));
			boolean decider = readFile.readLine() != null ? true : false;
			if (decider == true) {
				System.out.println(
						"#####################********* If not Flush works using clear content method ******* ###########################");
				clearFileContents();
			}
			System.out.println("#####################********* No Flush ******* ###########################");
			FileWriter writer = new FileWriter("Data.txt", false);
			// for(int i =0; i<valueToWrite.length();i++) {
			writer.write(valueToWrite + "\n");
			// }
			System.out.println("The enetered Text are : " + valueToWrite);
			writer.close();
		} catch (IOException e) {
			System.out.println("Cannot Find and Write into a File");
			e.printStackTrace();
		}
	}

	public static String readTextFile() throws IOException {

//		try {
//			FileReader fileReader = new FileReader("Data.txt");
//			int output;
//			String valueAfterRead = "";
//			while((output = fileReader.read()) != -1) {
//				System.out.println("The Value after Read is : " + output);
//			}
//		} catch (FileNotFoundException e) {
//			System.out.println("Cannot Find the specified File");
//			e.printStackTrace();
//		}
		System.out.println("##################################################################");
		BufferedReader readFile = new BufferedReader(new FileReader("Data.txt"));
		String valueAfterRead = "";
		do {
			valueAfterRead = readFile.readLine();
		} while (readFile.readLine() != null);

//		while (readFile.readLine() != null) {
//			System.out.println("The value after read is : " +valueAfterRead);
//		}
//		System.out.println("##################################################################");
		readFile.close();
		System.out.println("The value after read is outer while loop: " + valueAfterRead);
		return valueAfterRead;

	}

	public static void clearFileContents() {
		try (PrintWriter writer = new PrintWriter(new FileWriter("Data.txt", false))) {
			// Using "false" to overwrite the file and clear its contents
			System.out.println("Existing file contents cleared successfully.");
		} catch (IOException e) {
			System.err.println("Error clearing existing file contents: " + e.getMessage());
		}
	}
	
	
	public static void excelWriteFile(Map<Integer, Object[]> info) throws IOException {
		
		HSSFWorkbook workbook = new HSSFWorkbook();		
		HSSFSheet sheet = workbook.createSheet("ExcelData.txt");
		Set<Integer> keyset = info.keySet();
		int rowNumber = 0;
		
		for(Integer key : keyset) {
			Row row = sheet.createRow(rowNumber++);
			Object[] objArr = info.get(key);
			int cellNumber = 0;
			for(Object obj : objArr) {
				Cell cell = row.createCell(cellNumber++);
				if (obj instanceof String) {
					cell.setCellValue((String)obj);
				}else if (obj instanceof Double) {
					cell.setCellValue((Double)obj);
				}
			}
		}
		try {
			FileOutputStream fileOutWrite = new FileOutputStream(new File("./ExcelData.txt"));
			workbook.write(fileOutWrite);
			fileOutWrite.close();
			System.out.println("The Excel data is been written successfully");
		} catch (FileNotFoundException e) {
			System.out.println("Excel Data is NOT written successfully");
			e.printStackTrace();
		}
		
	}
	

}
