package com.epam.pattern.aggregator;

/**
 * Created by Aliaksandr_Shynkevich on 2/15/15
 */
public class JsonErrorResponse {
    private String message;
    private String cause;

    public JsonErrorResponse() {
        // Ctor.
    }

    public JsonErrorResponse(String message, String cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
