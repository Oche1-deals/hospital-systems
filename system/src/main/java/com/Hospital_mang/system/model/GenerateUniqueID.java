/**
 * Created By: Innocent Idoko
 * Time:14:27
 */
package com.Hospital_mang.system.model;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenerateUniqueID implements Serializable {

    @Id
    @Column(name = "generate_name", nullable = false, length = 105)
    private String generateName;
    @Column(length = 150)
    private int geneNumber;
    private LocalDateTime last_modified;

}
