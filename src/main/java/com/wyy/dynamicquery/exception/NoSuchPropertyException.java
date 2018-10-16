package com.wyy.dynamicquery.exception;

/**
 * @author wyy
 * @date 18-10-16
 * @time 下午6:13
 */
public class NoSuchPropertyException extends RuntimeException {

    public NoSuchPropertyException(String message) {
        super(message);
    }

    public NoSuchPropertyException(Throwable cause) {
        super(cause);
    }
}
