package com.epam.mentor.exception;

/**
 * Created by Aliaksandr_Shynkevich on 11/18/14
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
