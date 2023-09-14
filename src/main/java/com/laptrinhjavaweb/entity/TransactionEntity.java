package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity {
    @Column(name = "code")
    private String code;
    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    public String getCode() {
        return code;
    }

    public TransactionEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getNote() {
        return note;
    }

    public TransactionEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public TransactionEntity setCustomer(CustomerEntity customer) {
        this.customer = customer;
        return this;
    }
}
