package com.laptrinhjavaweb.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assignment_building")
public class AssignmentBuildingEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private UserEntity staff;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private BuildingEntity building;

    public AssignmentBuildingEntity() {

    }

    public AssignmentBuildingEntity(UserEntity staff, BuildingEntity building) {
        this.staff = staff;
        this.building = building;
    }

    public UserEntity getStaff() {
        return staff;
    }

    public void setStaff(UserEntity staff) {
        this.staff = staff;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }


}
