package com.Hospital_mang.system.Service.impl;

import com.Hospital_mang.system.Service.GenerateUniqueIDService;
import com.Hospital_mang.system.Service.PharmacyService;
import com.Hospital_mang.system.Service.UserLoginService;
import com.Hospital_mang.system.model.Department;
import com.Hospital_mang.system.model.Login;
import com.Hospital_mang.system.model.Pharmacy;
import com.Hospital_mang.system.repository.PharmacyRepository;
import com.Hospital_mang.system.request.PharmacyRequest;
import com.Hospital_mang.system.response.MessageResponseObject;
import com.Hospital_mang.system.response.PharmacyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private GenerateUniqueIDService generateUniqueIDService;

    @Autowired
    private UserLoginService userLoginService;

    @Override
    public ResponseEntity<?> registerMedication(PharmacyRequest pharmacyRequest) {
            String medicinesId = GenerateUniqueIDServiceImpl.zeroPad(generateUniqueIDService.
                    generateUniqueID("medicine").getGeneNumber(), 5);
            Login staffRcord = userLoginService.getUserJWTLogin();


            Pharmacy pharmacy = pharmacyResponse(pharmacyRequest);
            pharmacy.setStatus(1);
            pharmacy.setDeleted(Boolean.FALSE);
            pharmacy.setMedicinesId(medicinesId);
        //  pharmacy.setQuantity(pharmacyRequest.getQuantity());
        //    pharmacy.setPricePerUnit(pharmacyRequest.getPricePerUnit());
          //  pharmacy.setExpiryDate(pharmacyRequest.getExpiryDate());
          //  pharmacy.setMedicineName(pharmacy.getMedicineName());
            Pharmacy saveMedication = pharmacyRepository.save(pharmacy);

//
            if (saveMedication == null) {
                return ResponseEntity.badRequest().body(new MessageResponseObject("Failed! Could not register Medicin. Contact an Admin" +
                        "", HttpStatus.BAD_REQUEST.value()));
            }
            return ResponseEntity.ok().body(new MessageResponseObject
                    ("Successful! Medicine Registered", HttpStatus.CREATED.value(), saveMedication));
        }

    private Pharmacy pharmacyResponse(PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setMedicineName(pharmacyRequest.getMedicineName());
        pharmacy.setPricePerUnit(pharmacyRequest.getPricePerUnit());
        pharmacy.setQuantity(pharmacyRequest.getQuantity());
        pharmacy.setMedicinesId(pharmacy.getMedicinesId());
        pharmacy.setExpiryDate(pharmacyRequest.getExpiryDate());
        return pharmacy;
    }

}
