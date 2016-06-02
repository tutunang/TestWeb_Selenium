package com.elong.air.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.openqa.selenium.io.FileHandler;

/**
 * 操作文件类，用来操作excel、txt、word、Properties等
 * 
 * @author qiaojiafei
 * @version 创建时间：2016年4月25日 下午1:11:55 类说明
 */
public class OptionFile {

	public static String getPropertiesValue(String filename, String key) {
		String s = null;
		InputStream in = null;
		Properties props = new Properties();
		String s1 = null;
		// log.debug("Get the properties file: "+filename+",key="+key);
		try {
			in = OptionFile.class.getClassLoader()
					.getResourceAsStream(filename);
			props.load(in);
			s = props.getProperty(key);
			if (s != null) {
				s1 = new String(s.getBytes("ISO-8859-1"), "UTF-8");
			} else {
				// log.error("�����ֵ��propertis�ļ��в����ڣ�ֵ="+key);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s1;
	}

	public static String readProperties(String filePath, String key) {
		Properties props = new Properties();
		String s = null;
		String s1 = null;
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filePath));
			props.load(in);
			s = props.getProperty(key);
			if (s != null) {
				s1 = new String(s.getBytes("ISO-8859-1"), "UTF-8");
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s1;
	}

	public static void setExcel(String path, int index, int rowNum, int colNum,
			String value) {
		File file = new File(path);
		String cellValue = "";
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		try {
			FileInputStream in = new FileInputStream(file);
			if (path.endsWith(".xls")) {
				wb = new HSSFWorkbook(in);
				sheet = wb.getSheetAt(index - 1);

				row = sheet.getRow(rowNum - 1);
				cell = row.createCell(colNum - 1);// Ŀ���е�����
				HSSFRichTextString val = new HSSFRichTextString(value);
				cell.setCellValue(val);
			} else if (path.endsWith(".xlsx")) {
				wb = new XSSFWorkbook(in);
				sheet = wb.getSheetAt(index - 1);

				row = sheet.getRow(rowNum - 1);
				cell = row.createCell(colNum - 1);// Ŀ���е�����
				XSSFRichTextString val = new XSSFRichTextString(value);
				cell.setCellValue(val);
			}

			// cell = row.getCell(colNum-1);

			OutputStream out = new FileOutputStream(file);// ��ȡ�ļ������
			wb.write(out);// ������д��excel
			out.close();
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @Title: getExcel
	 * @Description: ��ȡexcel������excel03��07
	 * @param path
	 * @param index
	 * @param rowNum
	 * @param colNum
	 * @return
	 * @return String
	 * @throws
	 */
	public static String getExcel(String path, int index, int rowNum, int colNum) {
		File file = new File(path);
		String cellValue = "";
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		try {
			FileInputStream in = new FileInputStream(file);
			if (path.endsWith(".xls")) {
				wb = new HSSFWorkbook(in);
				sheet = wb.getSheetAt(index - 1);
			} else if (path.endsWith(".xlsx")) {
				wb = new XSSFWorkbook(in);
				sheet = wb.getSheetAt(index - 1);
			}
			row = sheet.getRow(rowNum - 1);
			cell = row.getCell(colNum - 1);
			if (cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					cellValue = cell.getStringCellValue().trim();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						Date date = cell.getDateCellValue();
						if (date != null) {
							cellValue = new SimpleDateFormat("yyyy-MM-dd")
									.format(date);
						} else {
							cellValue = "";
						}
					} else {
						cellValue = new DecimalFormat("###.###").format(cell
								.getNumericCellValue());
					}
					break;
				case Cell.CELL_TYPE_FORMULA:

					if (!cell.getStringCellValue().equals("")) {
						cellValue = cell.getStringCellValue();
					} else {
						cellValue = cell.getNumericCellValue() + "";
					}
					break;

				case Cell.CELL_TYPE_BLANK:
					break;

				case Cell.CELL_TYPE_ERROR:
					cellValue = "";
					break;

				case HSSFCell.CELL_TYPE_BOOLEAN:

					cellValue = (cell.getBooleanCellValue() == true ? "Y" : "N");
					break;

				default:
					cellValue = "";
				}
			}
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cellValue;

	}

	public static String[][] getLocatorArray(String path, int index) {
		// log.debug("��ȡ���excel���ݣ�"+path+"��SHEET:"+index);
		File file = new File(path);
		Workbook wb = null;
		Sheet sheet = null;
		// Row row = null;
		// Cell cell = null;
		FileInputStream in;
		String[][] locatorMap = null;
		try {
			in = new FileInputStream(file);
			if (path.endsWith(".xls")) {
				wb = new HSSFWorkbook(in);
				sheet = wb.getSheetAt(index - 1);
			} else if (path.endsWith(".xlsx")) {
				wb = new XSSFWorkbook(in);
				sheet = wb.getSheetAt(index - 1);
			}
			// HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(in));
			// Sheet sheet = wb.getSheetAt(index);
			Row header = sheet.getRow(0);
			String cellValue = "";
			locatorMap = new String[sheet.getLastRowNum() + 1][header
					.getLastCellNum()];
			for (int rownum = 0; rownum <= sheet.getLastRowNum(); rownum++) {
				// for (Cell cell : row)
				Row row = sheet.getRow(rownum);

				if (row == null) {
					continue;
				}

				for (int cellnum = 0; cellnum <= row.getLastCellNum(); cellnum++) {
					Cell cell = row.getCell(cellnum);
					if (cell != null) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							cellValue = cell.getStringCellValue().trim();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									cellValue = new SimpleDateFormat(
											"yyyy-MM-dd").format(date);
								} else {
									cellValue = "";
								}
							} else {
								cellValue = new DecimalFormat("###.###")
										.format(cell.getNumericCellValue());
							}
							break;
						case HSSFCell.CELL_TYPE_FORMULA:

							if (!cell.getStringCellValue().equals("")) {
								cellValue = cell.getStringCellValue();
							} else {
								cellValue = cell.getNumericCellValue() + "";
							}
							break;

						case HSSFCell.CELL_TYPE_BLANK:
							break;

						case HSSFCell.CELL_TYPE_ERROR:
							cellValue = "";
							break;

						case HSSFCell.CELL_TYPE_BOOLEAN:

							cellValue = (cell.getBooleanCellValue() == true ? "Y"
									: "N");
							break;

						default:
							cellValue = "";
						}
						locatorMap[rownum][cellnum] = cellValue;
					} else {
						cellValue = "";
					}

				}
			}
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return locatorMap;
	}
	public static void writeTxt(String text) {
		try {
			File file = new File("./src/test/resources/order.txt");
			//FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
			bw.write(text+System.getProperty("line.separator"));
			bw.flush();
			bw.close();
			//fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeTxt(String path, String text) {
		try {
			File file = new File(path);
			FileWriter fw = new FileWriter(file);
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
			BufferedWriter bw = new BufferedWriter(fw);
			//bw.write(text+System.getProperty("line.separator"));
			bw.write(text);
			bw.flush();
			bw.close();
			//fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Title: readTxt
	 * @Description: JAVA��ȡtxt�ļ�
	 * @param path
	 * @return
	 * @return String
	 * @throws
	 */
	public static String readTxt(String path) {
		// log.debug("��ȡTxt��"+path);
		File file = new File(path);
		StringBuffer txt = new StringBuffer();
		if (file.isFile() && file.exists()) {
			// InputStreamReader in = null;
			try {
				InputStreamReader in = new InputStreamReader(
						new FileInputStream(file), "GBK");
				BufferedReader bfd = new BufferedReader(in);
				String s;
				while ((s = bfd.readLine()) != null) {
					txt.append(s);
				}
				in.close();
				bfd.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return txt.toString();
	}

	/**
	 * @Title: readTxtByS
	 * @Description: selenium��ȡtxt�ļ�
	 * @param path
	 * @return
	 * @return String
	 * @throws
	 */
	public static String readTxtByS(String path) {
		// log.debug("��ȡTxt��"+path);
		File file = new File(path);
		String s = null;
		try {
			s = FileHandler.readAsString(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * @Title: readWord
	 * @Description: ��ȡword�ļ�����
	 * @param path
	 * @return
	 * @return StringBuffer
	 * @throws
	 */
	public static StringBuffer readWord(String path) {
		// log.debug("��ȡword�ļ���"+path);
		String s = "";
		try {
			if (path.endsWith(".doc")) {
				InputStream is = new FileInputStream(new File(path));
				WordExtractor ex = new WordExtractor(is);
				s = ex.getText();
			} else if (path.endsWith("docx")) {
				OPCPackage opcPackage = POIXMLDocument.openPackage(path);
				POIXMLTextExtractor extractor = new XWPFWordExtractor(
						opcPackage);
				s = extractor.getText();
			} else {
				// log.warn("�����word�ļ�����ȷ:"+path);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuffer bf = new StringBuffer(s);
		return bf;
	}

	/**
	 * @Title: getExcelRowCount
	 * @Description: ��ȡexcel������
	 * @param path
	 * @param index
	 * @return
	 * @return int
	 * @throws
	 */
	public static int getExcelRowCount(String path, int index) {
		int rowcount = 0;
		File file = new File(path);
		Workbook wb = null;
		Sheet sheet = null;
		// Row row = null;
		// Cell cell = null;
		FileInputStream in;
		try {
			in = new FileInputStream(file);
			if (path.endsWith(".xls")) {
				wb = new HSSFWorkbook(in);
				sheet = wb.getSheetAt(index - 1);
			} else if (path.endsWith(".xlsx")) {
				wb = new XSSFWorkbook(in);
				sheet = wb.getSheetAt(index - 1);
			}
			// HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(in));
			// Sheet sheet = wb.getSheetAt(index);
			Row header = sheet.getRow(0);
			rowcount = sheet.getLastRowNum() + 1;

			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowcount;
	}

	/**
	 * @Title: getExcelPriority
	 * @Description: ��ȡexcel�����ȼ�����
	 * @param path
	 * @param index
	 * @param p
	 * @return
	 * @return List<Integer>
	 * @throws
	 */
	public static List<Integer> getExcelPriority(String path, int index,
			String p) {
		int rowcount = 0;
		int rowstart = 3;
		String value = "";
		List<Integer> list = new ArrayList<Integer>();

		File file = new File(path);
		Workbook wb = null;
		Sheet sheet = null;
		// Row row = null;
		// Cell cell = null;
		FileInputStream in;
		try {
			in = new FileInputStream(file);
			if (path.endsWith(".xls")) {
				wb = new HSSFWorkbook(in);
				sheet = wb.getSheetAt(index - 1);
			} else if (path.endsWith(".xlsx")) {
				wb = new XSSFWorkbook(in);
				//from 0 start!0,1.2....
				sheet = wb.getSheetAt(index - 1);
			}
			// HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(in));
			// Sheet sheet = wb.getSheetAt(index);
		//	Row header = sheet.getRow(0);
			rowcount = sheet.getLastRowNum() + 1;

			if (p.equals("ALL") || p.equals("P1") || p.equals("P2")
					|| p.equals("P3")) {
				for (int i = rowstart; i <= rowcount; i++) {
					//2 为excel第二列（级别）
					value = getExcel(path, index, i, 2);
					// 得到指定用例优先级的的casenum，存入list
					if (p.equals("ALL")) {
						list.add(i);
					} else if (p.equals("P1")) {
						if (value.equals("P1")) {
							list.add(i);
						}
					} else if (p.equals("P2")) {
						if (value.equals("P1") || value.equals("P2")) {
							list.add(i);
						}
					} else if (p.equals("P3")) {
						if (value.equals("P1") || value.equals("P2")
								|| value.equals("P3")) {
							list.add(i);
						}
					}
					/*
					 * ִֻ�д�������ȼ�����ִ�����е����� if(p.equals("ALL") ||
					 * p.equals("P1") || p.equals("P2") || p.equals("P3")){
					 * for(int i=rowstart; i<=rowcount; i++) { value =
					 * getExcel(path, index, i, 2);
					 * //��������ȼ������P1,��ִ��P1��
					 * ��������ȼ������P2����ִ��P1+P2
					 * ;�������P3����ִ��P1+P2+P3���������ALL����ִ����������
					 * if(p.equals("ALL")) { list.add(i); }else
					 * if(p.equals("P1")) { if(value.equals("P1")) {
					 * list.add(i); } }else if(p.equals("P2")) {
					 * if(value.equals("P2")) { list.add(i); } }else
					 * if(p.equals("P3")) { if(value.equals("P3")) {
					 * list.add(i); } } } }
					 */
				}
			} else {
				System.out.println("excel中没有找到该级别" + p);
			}
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public static Map<String, String> getExcelDataByCaseNum(String path,
			int index, int caseNum) {
		int rowstart = 2;
		int colstart = 4;
		int elementcount = Integer.parseInt(getExcel(path, index, 1, 4));

		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < elementcount; i++) {
			String key = getExcel(path, index, rowstart, colstart + i);
			String value = getExcel(path, index, caseNum, colstart + i);

			map.put(key, value);
		}

		return map;
	}
}
