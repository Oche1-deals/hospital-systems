package com.Hospital_mang.system.Controller;

import com.Hospital_mang.system.Service.PharmacyService;
import com.Hospital_mang.system.request.PharmacyRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
//@CrossOrigin
@RequestMapping("/api/v1/pharmacy")
@Tag(name = "Pharmacy Controller", description = "Handles Medication setup like registration, status, price and delete with retrieval")
@RequiredArgsConstructor
public class PharmacyController {
    @Autowired
    private PharmacyService pharmacyService;
    @Operation(summary = "Medication Registration. you are required to provide  the Medication name , medication price, and other info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Failed! could not complete operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping({""})
    public ResponseEntity<?> addMedication(@Valid @RequestBody PharmacyRequest pharmacyRequest){
        return  pharmacyService.registerMedication(pharmacyRequest);
    }
}
