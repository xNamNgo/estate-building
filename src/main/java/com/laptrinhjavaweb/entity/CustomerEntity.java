package com.laptrinhjavaweb.entity;

import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {
    @Column(name = "full_name")
    private String fullName;
    @Column
    private String phone;
    @Column
    private String email;

    @Column(name = "company_name")
    private String companyName;

    @Column
    private String need;

    @Column
    private String note;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<TransactionEntity> transactions = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="assignment_customer",
               joinColumns = @JoinColumn(name = "customer_id",nullable = false),
               inverseJoinColumns = @JoinColumn(name = "staff_id",nullable = false))
    private List<UserEntity> staff = new ArrayList<>();



    public String getFullName() {
        return fullName;
    }

    public CustomerEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public CustomerEntity setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getNeed() {
        return need;
    }

    public CustomerEntity setNeed(String need) {
        this.need = need;
        return this;
    }

    public String getNote() {
        return note;
    }

    public CustomerEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public CustomerEntity setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
        return this;
    }

    public List<UserEntity> getStaff() {
        return staff;
    }

    public CustomerEntity setStaff(List<UserEntity> staff) {
        this.staff = staff;
        return this;
    }
}
