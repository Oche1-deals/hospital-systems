/**
 * Created By: Innocent Idoko
 * Time:16:20
 */
package com.Hospital_mang.system.model;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "refreshtoken")
public class RefreshToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(targetEntity = Login.class)
    @JoinColumn(name = "staff_id",referencedColumnName = "staff_id")
    private Login staffId;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

}
