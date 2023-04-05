package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.respone.BuildingRequestDTO;
import com.laptrinhjavaweb.dto.respone.ResponeDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    IBuildingService buildingService;


    // _controller : load danh sách nhân viên quản lý ra "modal"
    @GetMapping("/{buildingId}/staffs")
    public ResponeDTO loadStaff(@PathVariable("buildingId") Long buildingId) {
        ResponeDTO result = buildingService.getListStaff(buildingId);
        return result;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<BuildingRequestDTO> createBuilding(@RequestBody
                                                             BuildingRequestDTO newBuilding) {
        ResponseEntity<BuildingRequestDTO> response = ResponseEntity.ok(buildingService.save(newBuilding));
        return response;
    }

    @DeleteMapping
    @Transactional
    // TODO : dưới server nhận JSON là 1 kểu dạng key-value , để list sẽ bị lỗi .
    public ResponseEntity<Void> deleteBuilding(@RequestBody long[] idList) {
        if (idList.length > 0) {
            buildingService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public BuildingRequestDTO update(
            @RequestBody BuildingRequestDTO updateBuilding) {
        buildingService.save(updateBuilding);
        return updateBuilding;
    }

    /*
     *  Nhận các id "checked"
     * sau đó xóa danh sách id "checked" hiện tại
     * save lại id mới từ client .
     * */
    @PostMapping("/assignment-building")
    @Transactional
    public ResponeDTO saveAssignmentBuilding(
            @RequestBody AssignmentBuildingRequestDTO request) {
        ResponeDTO result = buildingService.saveAssignmentBuilding(request);
        return result;
    }


}
