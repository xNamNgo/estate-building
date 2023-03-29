package com.laptrinhjavaweb.entity;


import javax.persistence.*;

@Entity
@Table(name = "rent_area")
public class RentAreaEntity extends BaseEntity {
    @Column
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private BuildingEntity building;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }
}
