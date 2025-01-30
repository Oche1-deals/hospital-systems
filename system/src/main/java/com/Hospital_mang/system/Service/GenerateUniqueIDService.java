/**
 * Created By: Innocent Idoko
 * Time:14:29
 */
package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.model.GenerateUniqueID;

public interface GenerateUniqueIDService {
    GenerateUniqueID generateUniqueID(String name);
    String genericRandom(int size);
}
