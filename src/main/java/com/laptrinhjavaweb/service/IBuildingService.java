package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRespone;
import com.laptrinhjavaweb.dto.respone.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBuildingService {

    List<BuildingSearchRespone> loadBuilding(Pageable page, BuildingSearchRequest model);

    BuildingSearchRespone save(BuildingSearchRespone buildingSearchRespone);

    void delete(long[] idList);

    BuildingSearchRespone findById(Long id);

    int countTotalItems(BuildingSearchRequest model);
}
