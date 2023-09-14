package com.laptrinhjavaweb.dto.request;

public class AssignmentCustomerDTO {
    private long[] staffIdList;
    private Long customerId;

    public long[] getStaffIdList() {
        return staffIdList;
    }

    public AssignmentCustomerDTO setStaffIdList(long[] staffIdList) {
        this.staffIdList = staffIdList;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public AssignmentCustomerDTO setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }
}
