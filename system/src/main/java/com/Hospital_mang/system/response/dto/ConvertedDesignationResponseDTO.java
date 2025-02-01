/**
 * Created By: Innocent Idoko
 * Time:15:19
 */
package com.Hospital_mang.system.response.dto;

import com.Hospital_mang.system.model.Department;
import com.Hospital_mang.system.model.Desigination;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertedDesignationResponseDTO {
    public static List<DesignationData> departmentMultiResponse(List<Desigination> request){
        List<DesignationData> designationData = request.stream()
                .map(item->{
                    DesignationData data = new DesignationData();
                    data.setDescription(item.getDescription());
                    data.setDesignationId(item.getDesignationId());
                    return data;
                }).collect(Collectors.toList());
        return designationData;
    }

    @Data
    @RequiredArgsConstructor
    public static class DesignationData{
        private String designationId;
        private String description;
    }
}
