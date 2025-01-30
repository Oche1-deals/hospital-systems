/**
 * Created By: Innocent Idoko
 * Time:15:19
 */
package com.Hospital_mang.system.response.dto;

import com.Hospital_mang.system.model.Department;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertedDepartmentResponseDTO {
    public static List<DepartmentData> departmentMultiResponse(List<Department> request){
        List<DepartmentData> departmentData = request.stream()
                .map(item->{
                    DepartmentData data = new DepartmentData();
                    data.setDepartName(item.getDeptName());
                    data.setDeptId(item.getDeptId());
                    return data;
                }).collect(Collectors.toList());
        return departmentData;
    }

    @lombok.Data
    @RequiredArgsConstructor
    public static class DepartmentData{
        private String deptId;
        private String departName;
    }
}
