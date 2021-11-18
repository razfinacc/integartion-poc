package org.mulesoft.salesforce.utilities;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mulesoft.salesforce.model.SheetData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelUtility {

    private static FileInputStream fileInputStream = null;
    private static Workbook workbook = null;
    private static Sheet sheet = null;

    public static void getWorkbookHandle(String path) {
        try {
            fileInputStream = new FileInputStream(new File(path));
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            log.error("no file found at this location: {} or stack trace follows: {}", path, e.getStackTrace());
        }
    }

    public static void getSheetHandle(String sheetName) {
        sheet = workbook.getSheet(sheetName);
    }

    public static List<SheetData> getSheetData() {
        List<SheetData> sheetDataList = new ArrayList<>();
        for (int counter = 1; counter < sheet.getLastRowNum(); counter++) {
            Row nextRow = sheet.getRow(counter);
            SheetData sheetData = new SheetData();

            for (int index = 1; index < nextRow.getLastCellNum(); index++) {
                Cell cell = nextRow.getCell(index);
                switch (index) {
                    case 1:
                        sheetData.setContractId(getStringCellData(cell));
                        break;
                    case 2:
                        sheetData.setForecastSummary(getStringCellData(cell));
                        break;
                    case 3:
                        sheetData.setPropertyDescription(getStringCellData(cell));
                        break;
                    case 4:
                        sheetData.setProductTypeDescription(getStringCellData(cell));
                        break;
                    case 5:
                        sheetData.setTerritory(getStringCellData(cell));
                        break;
                    case 6:
                        sheetData.setRetailerDescription(getStringCellData(cell));
                        break;
                    case 7:
                        sheetData.setRevenueType(getStringCellData(cell));
                        break;
                    case 8:
                        sheetData.setRoyaltyRate(getNumericCellData(cell));
                        break;
                    case 9:
                        sheetData.setQ1(getStringCellData(cell));
                        break;
                    case 10:
                        sheetData.setQ2(getStringCellData(cell));
                        break;
                    case 11:
                        sheetData.setQ3(getStringCellData(cell));
                        break;
                    case 12:
                        sheetData.setQ4(getStringCellData(cell));
                        break;
                    case 13:
                        sheetData.setTotalAmount(getNumericCellData(cell));
                        break;
                }
            }
            sheetDataList.add(sheetData);
        }
        try {
            workbook.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheetDataList;
    }

    private static String getStringCellData(Cell cell) {
        return StringUtils.isNotBlank(cell.getStringCellValue()) ? cell.getStringCellValue() : "";
    }

    private static double getNumericCellData(Cell cell) {
        try {
            return cell.getNumericCellValue();
        } catch (IllegalStateException e) {
            log.error("no numeric value present in this cell: {}", e.getStackTrace());
        }
        return 0.0;
    }
}