package com.laptrinhjavaweb.enums;

public enum TypeEnum {
    TANG_TRET("Tầng trệt"),
    NOI_THAT("Nội thất"),
    NGUYEN_CAN("Nguyên căn");
    private final String value;

    TypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


