package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.request.BuildingListRequestDTO;
import com.laptrinhjavaweb.dto.respone.BuildingRequestDTO;
import com.laptrinhjavaweb.dto.respone.ResponeDTO;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    ResponeDTO getListStaff(Long buildingId);

    List<BuildingRequestDTO> loadBuilding(BuildingSearchDTO buildingSearchDTO);

    void save(BuildingRequestDTO buildingRequestDTO);

    void delete(BuildingListRequestDTO buildingListRequestDTO);

    BuildingRequestDTO findById(Long id);

    ResponeDTO saveAssignmentBuilding(AssignmentBuildingRequestDTO request);


}
