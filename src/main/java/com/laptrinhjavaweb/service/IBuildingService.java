package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.request.BuildingListRequestDTO;
import com.laptrinhjavaweb.dto.respone.BuildingRequestDTO;
import com.laptrinhjavaweb.dto.respone.ResponeDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    ResponeDTO getListStaff(Long buildingId);

    List<BuildingRequestDTO> loadBuilding(Pageable page,BuildingSearchDTO model);

    BuildingRequestDTO save(BuildingRequestDTO buildingRequestDTO);

    void delete(BuildingListRequestDTO buildingListRequestDTO);

    BuildingRequestDTO findById(Long id);

    ResponeDTO saveAssignmentBuilding(AssignmentBuildingRequestDTO request);


}
