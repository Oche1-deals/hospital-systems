package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.request.PharmacyRequest;
import org.springframework.http.ResponseEntity;

public interface PharmacyService {
    ResponseEntity<?> registerMedication(PharmacyRequest pharmacyRequest);
}
