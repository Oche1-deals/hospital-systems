package com.Hospital_mang.system.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyResponse {
    private String medicinesId;

    private String medicineName;

    private int  quantity;

    private LocalDate expiryDate;

    private Double pricePerUnit;

    private int status;//1 is active , 0 is invactive
    private boolean deleted;

}
