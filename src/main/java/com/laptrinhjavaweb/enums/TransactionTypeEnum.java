package com.laptrinhjavaweb.enums;

public enum TransactionTypeEnum {
    CSKH("Quá Trình Chăm Sóc Khác Hàng"), DAN_DI_XEM("Dẫn đi xem nhà");

    private final String typeTransaction;

    TransactionTypeEnum(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public String getValue() {
        return typeTransaction;
    }
}
