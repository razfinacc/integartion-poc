package org.mulesoft.salesforce.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SheetData {

    private String contractNo;
    private String forecastCurrency;
    private String propertyDescription;
    private String productTypeDescription;
    private String territory;
    private String retailerDescription;
    private String revenueType;
    private double royaltyRate;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private double totalAmount;
    private String createdDate;
    private String lastModifiedDate;
}
