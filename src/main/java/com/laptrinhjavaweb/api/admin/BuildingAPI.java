package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRespone;
import com.laptrinhjavaweb.dto.respone.ResponseDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    IBuildingService buildingService;

    // Xóa tòa nhà
    @DeleteMapping
    public ResponseEntity<Void> deleteBuilding(@RequestBody long[] idList) {
        if (idList.length > 0) {
            buildingService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }

    // thêm tòa nhà
    @PostMapping
    public ResponseEntity<BuildingSearchRespone> createBuilding(@RequestBody
                                                                BuildingSearchRespone newBuilding) {
        ResponseEntity<BuildingSearchRespone> response = ResponseEntity.ok(buildingService.save(newBuilding));
        return response;
    }

    // cập nhật tòa nhà
    @PutMapping
    public BuildingSearchRespone update(
            @RequestBody BuildingSearchRespone updateBuilding) {
        buildingService.save(updateBuilding);
        return updateBuilding;
    }


}
