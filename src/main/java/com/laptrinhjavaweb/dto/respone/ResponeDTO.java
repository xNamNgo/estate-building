package com.laptrinhjavaweb.dto.respone;

public class ResponeDTO {
    private Object data;
    private String message;
    private String detail;

    public ResponeDTO() {
    }

    public ResponeDTO(Object data, String message, String detail) {
        this.data = data;
        this.message = message;
        this.detail = detail;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
