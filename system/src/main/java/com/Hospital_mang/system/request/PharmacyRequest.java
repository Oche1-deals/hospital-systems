package com.Hospital_mang.system.request;

import com.Hospital_mang.system.model.enummodel.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotEmpty(message = "Quantity cannot be empty")
    private int quantity;

    @NotNull(message = "expiryDate cannot be null")
    @NotEmpty(message = "expiryDate cannot be empty")
    @Email(message = "invalid email format")
    private LocalDate expiryDate;

    @NotNull(message = "pricePerUnit cannot be null")
    @NotEmpty(message = "pricePerUnit cannot be empty")
    private Double pricePerUnit;



}
