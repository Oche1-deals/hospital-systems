/**
 * Created By: Innocent Idoko
 * Time:14:28
 */
package com.Hospital_mang.system.repository;

import com.Hospital_mang.system.model.GenerateUniqueID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenerateUniqueIDRepository extends JpaRepository<GenerateUniqueID, String> {

}