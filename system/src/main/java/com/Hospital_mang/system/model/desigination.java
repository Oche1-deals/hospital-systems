package com.Hospital_mang.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.*;
import java.util.*;

@Entity
@Data
public class desigination {


@Id
 private String designation_id;

 private String description;

}