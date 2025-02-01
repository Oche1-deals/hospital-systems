package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.model.Desigination;
import com.Hospital_mang.system.request.PageItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DesignationService {

    ResponseEntity<?> registerDesignation(String designationDescription);

    ResponseEntity<?> viewAllActiveDesignation(PageItem pageItem);
}
