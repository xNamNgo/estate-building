package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.respone.BuildingResponeDTO;
import com.laptrinhjavaweb.dto.respone.ResponeDTO;
import com.laptrinhjavaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    BuildingService buildingService;


    // _controller : load danh sách nhân viên quản lý ra "modal"
    @GetMapping("/{buildingId}/staffs")
    public ResponeDTO loadStaff(@PathVariable("buildingId") Long buildingId) {
        ResponeDTO result = buildingService.getListStaff(buildingId);
        return result;
    }

    @PostMapping
    @Transactional
    public BuildingResponeDTO createBuilding(@RequestBody
                                             BuildingResponeDTO newBuilding) {
        buildingService.save(newBuilding);
        return newBuilding;
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> deleteBuliding(@RequestBody List<Long> idList) {
        buildingService.delete(idList);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public BuildingResponeDTO update(@RequestBody BuildingResponeDTO updateBuilding) {
        buildingService.save(updateBuilding);
        return updateBuilding;
    }


}
