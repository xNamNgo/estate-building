package com.laptrinhjavaweb.dto;

public class TransactionDTO extends AbstractDTO{
    private String code;
    private String note;
    private Long customerId;

    public String getCode() {
        return code;
    }

    public TransactionDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getNote() {
        return note;
    }

    public TransactionDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public TransactionDTO setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }
}
