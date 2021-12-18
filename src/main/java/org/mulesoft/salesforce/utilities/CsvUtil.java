package org.mulesoft.salesforce.utilities;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mulesoft.salesforce.model.SheetData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CsvUtil {

    private static List<String> allLines = new ArrayList<>();

    /**
     * fetches control for accessing csv file
     *
     * @param csvFilePath - path to csv file
     */
    public static void getCsvHandle(String csvFilePath) {
        try {
            allLines = Files.readAllLines(new File(csvFilePath).toPath());
            log.info("Csv file is read from the path: {} on {}", csvFilePath, LocalDateTime.now());
        } catch (IOException e) {
            log.error("Unable to read from CSV file: ", e);
        }
    }

    /**
     * fetches all data from the lines
     *
     * @return - list of all lines available in the csv file
     */
    public static List<SheetData> getCsvData() {
        List<SheetData> sheetDataList = new ArrayList<>();
        allLines.stream()
                .filter(Objects::nonNull)
                .skip(1)
                .forEach(line -> {
                    String[] rowValues = line.split(",");
                    SheetData sheetData = new SheetData();
                    for (int index = 0; index < rowValues.length; index++) {
                        switch (index) {
                            case 0:
                                sheetData.setContractNo(getStringCellData(rowValues[index]));
                                break;
                            case 1:
                                sheetData.setForecastCurrency(getStringCellData(rowValues[index]));
                                break;
                            case 2:
                                sheetData.setPropertyDescription(getStringCellData(rowValues[index]));
                                break;
                            case 3:
                                sheetData.setProductTypeDescription(getStringCellData(rowValues[index]));
                                break;
                            case 4:
                                sheetData.setTerritory(getStringCellData(rowValues[index]));
                                break;
                            case 5:
                                sheetData.setRetailerDescription(getStringCellData(rowValues[index]));
                                break;
                            case 6:
                                sheetData.setRevenueType(getStringCellData(rowValues[index]));
                                break;
                            case 7:
                                sheetData.setRoyaltyRate(getNumericCellData(rowValues[index]));
                                break;
                            case 8:
                                sheetData.setQ1(getStringCellData(rowValues[index]));
                                break;
                            case 9:
                                sheetData.setQ2(getStringCellData(rowValues[index]));
                                break;
                            case 10:
                                sheetData.setQ3(getStringCellData(rowValues[index]));
                                break;
                            case 11:
                                sheetData.setQ4(getStringCellData(rowValues[index]));
                                break;
                            case 12:
                                sheetData.setTotalAmount(getNumericCellData(rowValues[index]));
                                break;
                            case 13:
                                sheetData.setCreatedDate(getStringCellData(rowValues[index]));
                                break;
                            case 14:
                                sheetData.setLastModifiedDate(getStringCellData(rowValues[index]));
                                break;
                        }
                    }
                    sheetDataList.add(sheetData);
                });
        return sheetDataList;
    }

    /**
     * format cell String data
     *
     * @param value - value
     * @return - actual value if present or "" in case of no cell value/null
     */
    private static String getStringCellData(String value) {
        return StringUtils.isNotBlank(value) ? value : "";
    }

    /**
     * format cell numeric value
     *
     * @param value - value
     * @return - actual value if present or 0.0 default
     */
    private static double getNumericCellData(String value) {
        try {
            return Double.parseDouble(value.replace("%", ""));
        } catch (NumberFormatException e) {
            log.error("{}: can't be converted to a Numeric: {}, stack trace: {}", value, e.fillInStackTrace(), e.getStackTrace());
        }
        return 0.0;
    }
}
