/**
 * Created By: Innocent Idoko
 * Time:21:58
 */
package com.Hospital_mang.system.Service.impl;

import com.Hospital_mang.system.Service.StaffRecordService;
import com.Hospital_mang.system.model.StaffRecord;
import com.Hospital_mang.system.repository.RoleRepository;
import com.Hospital_mang.system.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffRecordServiceImpl implements StaffRecordService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public boolean saveStaffRecord(StaffRecord staffRecord) {
        StaffRecord saveStaff = staffRepository.save(staffRecord);
        return (saveStaff == null)?Boolean.FALSE:Boolean.TRUE;
    }

    @Override
    public StaffRecord findByEmail(String email) {
        return staffRepository.findByEmail(email);
    }

    @Override
    public StaffRecord findByPhoneNumber(String phoneNumber) {
        return staffRepository.findByPhoneNumber(phoneNumber);
    }
}
