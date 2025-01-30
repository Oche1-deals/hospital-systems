/**
 * Created By: Innocent Idoko
 * Time:15:02
 */
package com.Hospital_mang.system.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageItem {
    private int page = 0;
    private int size = 100;

}

