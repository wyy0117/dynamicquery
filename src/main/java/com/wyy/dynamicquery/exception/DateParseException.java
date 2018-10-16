package com.wyy.dynamicquery.exception;

/**
 * @author wyy
 * @date 18-10-16
 * @time 下午6:18
 */
public class DateParseException extends RuntimeException {

    public DateParseException(String message) {
        super(message);
    }

    public DateParseException(Throwable cause) {
        super(cause);
    }
}

