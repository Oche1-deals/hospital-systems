package com.Hospital_mang.system.Service.impl;

import com.Hospital_mang.system.Service.DesignationTypeService;
import com.Hospital_mang.system.Service.GenerateUniqueIDService;
import com.Hospital_mang.system.Service.UserLoginService;
import com.Hospital_mang.system.model.DesignationType;
import com.Hospital_mang.system.model.Login;
import com.Hospital_mang.system.repository.DesignationTypeRepository;
import com.Hospital_mang.system.request.PageItem;
import com.Hospital_mang.system.response.MessageResponseObject;
import com.Hospital_mang.system.response.dto.ConvertedDesignationTypeResponseDTO;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class DesignationTypeServiceImpl implements DesignationTypeService {

    @Autowired
    private DesignationTypeRepository designationTypeRepository;
    @Autowired
    private GenerateUniqueIDService generateUniqueIDService;
    @Autowired
    private UserLoginService userLoginService;

    @Override
    public ResponseEntity<?> registerDesignationType(String designationTypeName) {
        String deptID = GenerateUniqueIDServiceImpl.zeroPad(generateUniqueIDService.
                generateUniqueID("dept").getGeneNumber(), 5);
        Login staffRcord = userLoginService.getUserJWTLogin();
        DesignationType designationType = new DesignationType();
        designationType.setDesignationName(designationTypeName);
        designationType.setStatus(1);
        designationType.setDeleted(Boolean.FALSE);
        designationType.setId(deptID);
        DesignationType saveDesignationType = designationTypeRepository.save(designationType);
        if (saveDesignationType == null) {
            return ResponseEntity.badRequest().body(new MessageResponseObject("Failed! Could not register Designation Type. Contact an Admin" +
                    "", HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.ok().body(new MessageResponseObject
                ("Successful! Designation Type Registered", HttpStatus.CREATED.value(), saveDesignationType));
    }

    @Override
    public ResponseEntity<?> viewAllActiveDesignationType(PageItem pageItem) {
        Pageable paging = PageRequest.of(pageItem.getPage(), pageItem.getSize());
        Page<DesignationType> viewAllDesignatinType = designationTypeRepository.findByStatusAndDeleted(1, Boolean.FALSE, paging);
        if (viewAllDesignatinType.getContent().isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponseObject("No DesignationType found", HttpStatus.BAD_REQUEST.value()));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("data",
                ConvertedDesignationTypeResponseDTO.departmentMultiResponse(viewAllDesignatinType.getContent()));
        response.put("total", viewAllDesignatinType.getTotalElements());
        response.put("currentPage", viewAllDesignatinType.getNumber());
        response.put("totalPage", viewAllDesignatinType.getTotalPages());


        return ResponseEntity.accepted().body(new MessageResponseObject("Department found", HttpStatus.OK.value(), response));
    }

    public DesignationType findDesignationById(String Id) {
        return designationTypeRepository.findById(Id).get();
    }
}

