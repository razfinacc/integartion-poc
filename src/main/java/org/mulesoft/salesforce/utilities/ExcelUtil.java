package org.mulesoft.salesforce.utilities;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mulesoft.salesforce.model.SheetData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class ExcelUtil {

    private static FileInputStream fileInputStream = null;
    private static Workbook workbook = null;
    private static Sheet sheet = null;

    /**
     * fetches control for accessing excel workbook
     *
     * @param excelFilePath - path to excel file
     */
    public static void getWorkbookHandle(String excelFilePath) {
        try {
            fileInputStream = new FileInputStream(new File(excelFilePath));
            workbook = new XSSFWorkbook(fileInputStream);
            log.info("Excel file is read from the path: {} on {}", excelFilePath, LocalDateTime.now());
        } catch (IOException e) {
            log.error("Unable to read from EXCEL file: ", e);
        }
    }

    /**
     * fetches control for accessing excel sheet
     *
     * @param sheetName - sheet name to access
     */
    public static void getSheetHandle(String sheetName) {
        sheet = workbook.getSheet(sheetName);
    }

    /**
     * fetches all data from the sheet
     *
     * @return - list of all rows available in the sheet
     */
    public static List<SheetData> getSheetData() {
        List<SheetData> sheetDataList = new ArrayList<>();
        for (int counter = 1; counter < sheet.getLastRowNum(); counter++) {
            Row nextRow = sheet.getRow(counter);
            SheetData sheetData = new SheetData();
            for (int index = 1; index < nextRow.getLastCellNum(); index++) {
                Cell cell = nextRow.getCell(index);
                switch (index) {
                    case 1:
                        sheetData.setContractNo(getStringCellData(cell));
                        break;
                    case 2:
                        sheetData.setForecastCurrency(getStringCellData(cell));
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
            log.error("exception in closing input stream/workbook", e);
            e.printStackTrace();
        }
        return sheetDataList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * format cell String data
     *
     * @param cell - cell
     * @return - actual value if present or "" in case of no cell value/null
     */
    private static String getStringCellData(Cell cell) {
        return StringUtils.isNotBlank(cell.getStringCellValue()) ? cell.getStringCellValue() : "";
    }

    /**
     * format cell numeric value
     *
     * @param cell - cell
     * @return - actual value if present or 0.0 default
     */
    private static double getNumericCellData(Cell cell) {
        try {
            return cell.getNumericCellValue();
        } catch (IllegalStateException e) {
            log.error("cell value can't be converted to a Numeric: {}, stack trace: {}", e.fillInStackTrace(), e.getStackTrace());
        }
        return 0.0;
    }
}