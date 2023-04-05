package com.laptrinhjavaweb.dto.request;

import java.util.List;

public class AssignmentBuildingRequestDTO {
    private long[] staffIdList;
    private Long buildingId;

    public long[] getStaffIdList() {
        return staffIdList;
    }

    public void setStaffIdList(long[] staffIdList) {
        this.staffIdList = staffIdList;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
