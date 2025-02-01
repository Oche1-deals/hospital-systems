package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.request.PageItem;
import org.springframework.http.ResponseEntity;

public interface DesignationTypeService {
    ResponseEntity<?> registerDesignationType(String  designationTypeName);

    ResponseEntity<?> viewAllActiveDesignationType (PageItem pageItem);
}
