package com.laptrinhjavaweb.builder;

public class CustomerBuilder {
    private String full_name;
    private String phone;
    private String email;
    private String company_name;
    private String need;
    private String note;
    private Long staff_id;
    private String staff_name;
    private String createdBy;


    public CustomerBuilder(Builder builder) {
        this.full_name = builder.fullName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.company_name = builder.companyName;
        this.need = builder.need;
        this.note = builder.note;
        this.staff_id = builder.staffId;
        this.staff_name = builder.staffName;
        this.createdBy = builder.createdBy;
    }

    public String getFull_name() {
        return full_name;
    }

    public CustomerBuilder setFull_name(String full_name) {
        this.full_name = full_name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompany_name() {
        return company_name;
    }

    public CustomerBuilder setCompany_name(String company_name) {
        this.company_name = company_name;
        return this;
    }

    public String getNeed() {
        return need;
    }

    public CustomerBuilder setNeed(String need) {
        this.need = need;
        return this;
    }

    public String getNote() {
        return note;
    }

    public CustomerBuilder setNote(String note) {
        this.note = note;
        return this;
    }

    public Long getStaff_id() {
        return staff_id;
    }

    public CustomerBuilder setStaff_id(Long staff_id) {
        this.staff_id = staff_id;
        return this;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public CustomerBuilder setStaff_name(String staff_name) {
        this.staff_name = staff_name;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CustomerBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public static class Builder {
        private String fullName;
        private String phone;
        private String email;
        private String companyName;
        private String need;
        private String note;
        private Long staffId;
        private String staffName;
        private String createdBy;

        public String getCreatedBy() {
            return createdBy;
        }

        public Builder setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public String getFullName() {
            return fullName;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public String getPhone() {
            return phone;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getCompanyName() {
            return companyName;
        }

        public Builder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public String getNeed() {
            return need;
        }

        public Builder setNeed(String need) {
            this.need = need;
            return this;
        }

        public String getNote() {
            return note;
        }

        public Builder setNote(String note) {
            this.note = note;
            return this;
        }

        public Long getStaffId() {
            return staffId;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public String getStaffName() {
            return staffName;
        }

        public Builder setStaffName(String staffName) {
            this.staffName = staffName;
            return this;
        }

        public CustomerBuilder build(){
            return new CustomerBuilder(this);
        }



    }
}
