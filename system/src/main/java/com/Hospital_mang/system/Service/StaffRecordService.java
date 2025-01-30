/**
 * Created By: Innocent Idoko
 * Time:21:58
 */
package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.model.StaffRecord;

public interface StaffRecordService {
    boolean saveStaffRecord(StaffRecord staffRecord);
    StaffRecord findByEmail(String email);
    StaffRecord findByPhoneNumber(String phoneNumber);
}
