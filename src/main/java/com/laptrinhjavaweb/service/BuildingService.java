package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.request.BuildingListRequestDTO;
import com.laptrinhjavaweb.dto.respone.BuildingResponeDTO;
import com.laptrinhjavaweb.dto.respone.ResponeDTO;

import java.util.List;
import java.util.Map;

public interface BuildingService {

    Map<String, String> getDistrictMap();

    Map<String, String> getTypeMap();

    ResponeDTO getListStaff(Long buildingId);

    List<BuildingResponeDTO> loadBuilding(BuildingSearchDTO buildingSearchDTO);

    void save(BuildingResponeDTO buildingResponeDTO);

    void delete(BuildingListRequestDTO buildingListRequestDTO);

    BuildingResponeDTO findById(Long id);

    ResponeDTO saveAssignmentBuilding(AssignmentBuildingRequestDTO request);


}
