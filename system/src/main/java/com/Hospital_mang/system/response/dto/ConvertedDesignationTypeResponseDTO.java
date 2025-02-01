package com.Hospital_mang.system.response.dto;

import com.Hospital_mang.system.model.Department;
import com.Hospital_mang.system.model.DesignationType;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertedDesignationTypeResponseDTO {
    public static List<DesignationTypeData> departmentMultiResponse(List<DesignationType> request){
        List<DesignationTypeData> designationTypeData = request.stream()
                .map(item->{
                    DesignationTypeData  data = new DesignationTypeData();
                    data.setDesignationName(item.getDesignationName());
                    data.setID(item.getId());
                    return data;
                }).collect(Collectors.toList());
        return designationTypeData;
    }
    @lombok.Data
    @RequiredArgsConstructor
    public static class DesignationTypeData{
        private String ID;
        private String designationName;
    }
}
