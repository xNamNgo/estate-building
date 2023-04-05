package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column
    private String street;
    @Column
    private String ward;
    @Column
    private String district;
    @Column
    private String structure;
    @Column(name = "number_of_basement")
    private Integer numberOfBasement;
    @Column(name = "floor_area")
    private Integer floorArea;
    @Column
    private String direction;
    @Column
    private String level;
    @Column(name = "rent_price")
    private Integer rentPrice;
    @Column(name = "rent_price_description")
    private String rentPriceDescription;
    @Column(name = "service_fee")
    private String serviceFee;
    @Column(name = "car_fee")
    private String carFee;
    @Column(name = "moto_fee")
    private String motofee;
    @Column(name = "over_time_fee")
    private String overtimeFee;
    @Column(name = "electricity_fee")
    private String electricityFee;
    @Column
    private String deposit;
    @Column
    private String payment;
    @Column(name = "rent_time")
    private String rentTime;
    @Column(name = "decoration_time")
    private String decorationTime;
    @Column(name = "bokerage_fee")
    private Integer brokerageFee;
    @Column
    private String type;
    @Column
    private String note;
    @Column(name = "link_of_building")
    private String linkOfbuilding;
    @Column
    private String image;

    @ManyToMany
    @JoinTable(name = "assignment_building",
            joinColumns = @JoinColumn(name = "building_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staff_id", nullable = false))
    private List<UserEntity> users;

    @OneToMany(mappedBy = "building", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<RentAreaEntity> rentAreas = new ArrayList<>();

    @Column(name = "manager_name")
    private String managerName;
    @Column(name = "phone_number")
    private String phoneNumber;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public void addRentArea(RentAreaEntity rentAreaEntity) {
        rentAreas.add(rentAreaEntity);
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public void setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getCarFee() {
        return carFee;
    }

    public void setCarFee(String carFee) {
        this.carFee = carFee;
    }

    public String getMotofee() {
        return motofee;
    }

    public void setMotofee(String motofee) {
        this.motofee = motofee;
    }

    public String getOvertimeFee() {
        return overtimeFee;
    }

    public void setOvertimeFee(String overtimeFee) {
        this.overtimeFee = overtimeFee;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public String getDecorationTime() {
        return decorationTime;
    }

    public void setDecorationTime(String decorationTime) {
        this.decorationTime = decorationTime;
    }

    public Integer getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Integer brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLinkOfbuilding() {
        return linkOfbuilding;
    }

    public void setLinkOfbuilding(String linkOfbuilding) {
        this.linkOfbuilding = linkOfbuilding;
    }

    public List<RentAreaEntity> getRentAreas() {
        return rentAreas;
    }

    public void setRentAreas(List<RentAreaEntity> rentAreas) {
        this.rentAreas = rentAreas;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
