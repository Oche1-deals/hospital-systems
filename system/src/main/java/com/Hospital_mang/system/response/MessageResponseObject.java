/**
 * Created By: Innocent Idoko
 * Time:15:48
 */
package com.Hospital_mang.system.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Service
public class MessageResponseObject {
    private String message;
    private int code;
    private Object data;

    public MessageResponseObject(String message, int code, Object responseData) {
        this.message = message;
        this.code = code;
        this.data = responseData;
    }


    public MessageResponseObject() {
    }

    public MessageResponseObject(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public MessageResponseObject(String message) {
        this.message = message;
    }


}
