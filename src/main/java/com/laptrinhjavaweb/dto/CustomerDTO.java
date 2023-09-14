package com.laptrinhjavaweb.dto.request;

import com.laptrinhjavaweb.dto.AbstractDTO;

public class CustomerDTO extends AbstractDTO {
    private String fullName;
    private String phone;
    private String email;
    private String companyName;
    private String need;
    private String note;
    private Integer staffId;

    public CustomerDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public CustomerDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public CustomerDTO setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getNeed() {
        return need;
    }

    public CustomerDTO setNeed(String need) {
        this.need = need;
        return this;
    }

    public String getNote() {
        return note;
    }

    public CustomerDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public CustomerDTO setStaffId(Integer staffId) {
        this.staffId = staffId;
        return this;
    }
}
