package com.epam.pattern;

/**
 * Created by Aliaksandr_Shynkevich on 2/7/15
 */
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
