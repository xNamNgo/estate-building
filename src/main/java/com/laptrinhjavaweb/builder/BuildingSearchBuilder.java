package com.laptrinhjavaweb.builder;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
    private String name;
    private Integer floor_area;
    private String district;
    private String ward;
    private String street;
    private Integer number_of_basement;
    private String direction;
    private String level;
    private Long rentAreaFrom;
    private Long rentAreaTo;
    private Long costRentFrom;
    private Long costRentTo;
    private String manager_name;
    private String phone_number;
    private Integer staffId;
    private List<String> types;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloorArea() {
        return floor_area;
    }

    public void setFloorArea(Integer floorArea) {
        this.floor_area = floorArea;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumberOfBasement() {
        return number_of_basement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.number_of_basement = numberOfBasement;
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

    public Long getRentAreaFrom() {
        return rentAreaFrom;
    }

    public void setRentAreaFrom(Long rentAreaFrom) {
        this.rentAreaFrom = rentAreaFrom;
    }

    public Long getRentAreaTo() {
        return rentAreaTo;
    }

    public void setRentAreaTo(Long rentAreaTo) {
        this.rentAreaTo = rentAreaTo;
    }

    public Long getCostRentFrom() {
        return costRentFrom;
    }

    public void setCostRentFrom(Long costRentFrom) {
        this.costRentFrom = costRentFrom;
    }

    public Long getCostRentTo() {
        return costRentTo;
    }

    public void setCostRentTo(Long costRentTo) {
        this.costRentTo = costRentTo;
    }

    public String getManagerName() {
        return manager_name;
    }

    public void setManagerName(String managerName) {
        this.manager_name = managerName;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    private BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.floor_area = builder.floorArea;
        this.district = builder.district;
        this.ward = builder.ward;
        this.street = builder.street;
        this.number_of_basement = builder.numberOfBasement;
        this.direction = builder.direction;
        this.level = builder.level;
        this.rentAreaFrom = builder.rentAreaFrom;
        this.rentAreaTo = builder.rentAreaTo;
        this.costRentFrom = builder.costRentFrom;
        this.costRentTo = builder.costRentTo;
        this.manager_name = builder.managerName;
        this.phone_number = builder.phoneNumber;
        this.staffId = builder.staffId;
        this.types = builder.types;
    }

    public static class Builder {
        private String name;
        private Integer floorArea;
        private String district;
        private String ward;
        private String street;
        private Integer numberOfBasement;
        private String direction;
        private String level;
        private Long rentAreaFrom;
        private Long rentAreaTo;
        private Long costRentFrom;
        private Long costRentTo;
        private String managerName;
        private String phoneNumber;
        private Integer staffId;
        private List<String> types = new ArrayList<>();

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setFloorArea(Integer floorArea) {
            this.floorArea = floorArea;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setNumberOfBasement(Integer numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setRentAreaFrom(Long rentAreaFrom) {
            this.rentAreaFrom = rentAreaFrom;
            return this;
        }

        public Builder setRentAreaTo(Long rentAreaTo) {
            this.rentAreaTo = rentAreaTo;
            return this;
        }

        public Builder setCostRentFrom(Long costRentFrom) {
            this.costRentFrom = costRentFrom;
            return this;
        }

        public Builder setCostRentTo(Long costRentTo) {
            this.costRentTo = costRentTo;
            return this;
        }

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setStaffId(Integer staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setTypes(List<String> types) {
            this.types = types;
            return this;
        }

        public BuildingSearchBuilder build()  {
            return new BuildingSearchBuilder(this);
        }
    }
}
