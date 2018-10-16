package com.wyy.dynamicquery.exception;

/**
 * @author wyy
 * @date 18-10-16
 * @time 下午6:14
 */
public class UnSupportDataTypeException extends RuntimeException {

    public UnSupportDataTypeException(String message) {
        super(message);
    }

    public UnSupportDataTypeException(Throwable cause) {
        super(cause);
    }
}
