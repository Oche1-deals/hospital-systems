package com.Hospital_mang.system.request;

import com.Hospital_mang.system.model.enummodel.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Data
@Setter
@Getter
public class PharmacyRequest {

    @NotNull(message = "medicine name cannot be null")
    @NotEmpty(message = "medicine name cannot be empty")
    private String medicineName;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "quantity must not be 0")
    private int quantity;

    @NotNull(message = "expiryDate cannot be null")
    private LocalDate expiryDate;

    @NotNull(message = "pricePerUnit cannot be null")
    @Min(value = 0,message = "price must not be 0")
    private Double pricePerUnit;



}
