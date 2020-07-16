package com.jyami.springbootgettingstartedmaven.webMVC.user;

/**
 * Created by jyami on 2020/07/16
 */
public class AppError {

    private String message;
    private String reason;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
