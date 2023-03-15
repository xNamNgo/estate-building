package com.laptrinhjavaweb.dto.request;

import java.util.List;

public class AssignmentBuildingRequestDTO {
    private List<Long> staffIdList;
    private Long buildingId;

    public List<Long> getStaffIdList() {
        return staffIdList;
    }

    public void setStaffIdList(List<Long> staffIdList) {
        this.staffIdList = staffIdList;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
