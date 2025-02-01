package com.Hospital_mang.system.Service.impl;

import com.Hospital_mang.system.Service.DesignationService;
import com.Hospital_mang.system.Service.GenerateUniqueIDService;
import com.Hospital_mang.system.model.Department;
import com.Hospital_mang.system.model.Desigination;
import com.Hospital_mang.system.repository.DesignationRepository;
import com.Hospital_mang.system.request.PageItem;
import com.Hospital_mang.system.response.MessageResponseObject;
import com.Hospital_mang.system.response.dto.ConvertedDepartmentResponseDTO;
import com.Hospital_mang.system.response.dto.ConvertedDesignationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private GenerateUniqueIDService generateUniqueIDService;

    @Override
    public ResponseEntity<?> registerDesignation(String designationDescription) {
        String desinationId = GenerateUniqueIDServiceImpl.zeroPad(generateUniqueIDService.
                generateUniqueID("role").getGeneNumber(), 5);
        Desigination desigination = new Desigination();
        desigination.setDescription(designationDescription);
        desigination.setStatus(1);
        desigination.setDeleted(Boolean.FALSE);
        desigination.setDesignationId(desinationId);
        Desigination savedDesignation = designationRepository.save(desigination);
        if (savedDesignation == null) {
            return ResponseEntity.badRequest().body(new MessageResponseObject("Failed! Could not register Designatio. Contact an Admin" +
                    "", HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.ok().body(new MessageResponseObject
                ("Successful! Designation Registered", HttpStatus.CREATED.value(), savedDesignation));
    }
    @Override
    public ResponseEntity<?> viewAllActiveDesignation(PageItem pageItem) {
        Pageable paging = PageRequest.of(pageItem.getPage(), pageItem.getSize());
        Page<Desigination> viewAllDesignation = designationRepository.findByStatusAndDeleted(1, Boolean.FALSE, paging);
        if (viewAllDesignation.getContent().isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponseObject("No Designation found", HttpStatus.BAD_REQUEST.value()));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("data",
                ConvertedDesignationResponseDTO.departmentMultiResponse(viewAllDesignation.getContent()));
        response.put("total", viewAllDesignation.getTotalElements());
        response.put("currentPage", viewAllDesignation.getNumber());
        response.put("totalPage", viewAllDesignation.getTotalPages());


        return ResponseEntity.accepted().body(new MessageResponseObject("Designation found", HttpStatus.OK.value(), response));
    }
}
