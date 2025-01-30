/**
 * Created By: Innocent Idoko
 * Time:14:30
 */
package com.Hospital_mang.system.Service.impl;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.Hospital_mang.system.Service.GenerateUniqueIDService;
import com.Hospital_mang.system.model.GenerateUniqueID;
import com.Hospital_mang.system.repository.GenerateUniqueIDRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class GenerateUniqueIDServiceImpl implements GenerateUniqueIDService {

    @Autowired
    private final GenerateUniqueIDRepository generateUniqueIDRepository;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    public GenerateUniqueID generateUniqueID(String name) {
        int numberGenerated = 0;
        GenerateUniqueID generateUniqueID = null;
        Map<String, Object> responseMap = new HashMap<>();
        try {
            generateUniqueID = generateUniqueIDRepository.findById(name).get();
        } catch (Exception e) {
            generateUniqueID = null;
        }
        if (generateUniqueID == null) {
            numberGenerated = 1;
            generateUniqueID = new GenerateUniqueID();
            generateUniqueID.setGenerateName(name);
            generateUniqueID.setGeneNumber(numberGenerated);
            generateUniqueID.setLast_modified(LocalDateTime.now());
            generateUniqueIDRepository.save(generateUniqueID);
        } else {
            numberGenerated = generateUniqueID.getGeneNumber() + 1;
            generateUniqueID.setGeneNumber(numberGenerated);
            generateUniqueID.setLast_modified(LocalDateTime.now());
            generateUniqueIDRepository.save(generateUniqueID);
        }
        return generateUniqueID;
    }

    public static String zeroPad(int number, int desiredWidth) {
        // %0 specifies zero-padding, and the width is determined by desiredWidth
        return String.format("%0" + desiredWidth + "d", number);
    }

    @Override
    public String genericRandom(int size) {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < size; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }

        return code.toString();
    }

}