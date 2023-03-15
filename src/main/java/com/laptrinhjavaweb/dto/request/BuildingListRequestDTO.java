package com.laptrinhjavaweb.dto.request;

import java.util.List;

public class BuildingListRequestDTO {
    private List<Long> idList;

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}
