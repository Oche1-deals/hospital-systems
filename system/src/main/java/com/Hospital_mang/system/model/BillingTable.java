package com.Hospital_mang.system.model;

import com.Hospital_mang.system.model.enummodel.Payment_description;
import com.Hospital_mang.system.model.enummodel.Payment_status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class BillingTable {


@Id
@Column(name = "bill_id", nullable = false)
 private String billId;

 private String patientId;

 private Double totalAmount;
 @Enumerated(EnumType.ORDINAL)
 private Payment_description paymentDescription;
 @Enumerated(EnumType.ORDINAL)
 private Payment_status status;

 private LocalDateTime payment_date;

}