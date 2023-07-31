package com.laptrinhjavaweb.api.admin;

//import org.apache.log4j.Logger;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.respone.ResponseDTO;
import com.laptrinhjavaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
    @Autowired
    UserService userService;

    @GetMapping("/staffs")
    public ResponseDTO loadStaff(@RequestParam(required = false,value = "building_id") Long buildingId) {
        ResponseDTO result = new ResponseDTO();

        // load staff giao tòa nhà
        if(buildingId != null){
            result = userService.loadStaff(buildingId);
        } else {
            // load stafff
        }
        return result;
    }

    @PostMapping("/assignment")
    public ResponseDTO saveAssignmentBuilding(
            @RequestBody AssignmentBuildingRequestDTO assignmentRequest) {
        ResponseDTO result = userService.saveAssignmentBuilding(assignmentRequest);
        return result;
    }

















//    private Logger LOGGER = Logger.getLogger(UserAPI.class);



//    @PostMapping
//    public ResponseEntity<UserDTO> createUsers(@RequestBody UserDTO newUser) {
//        return ResponseEntity.ok(userService.insert(newUser));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserDTO> updateUsers(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
//        return ResponseEntity.ok(userService.update(id, userDTO));
//    }
//
//    @PutMapping("/change-password/{id}")
//    public ResponseEntity<String> changePasswordUser(@PathVariable("id") long id, @RequestBody PasswordDTO passwordDTO) {
//        try {
//            userService.updatePassword(id, passwordDTO);
//            return ResponseEntity.ok(SystemConstant.UPDATE_SUCCESS);
//        } catch (MyException e) {
//            LOGGER.error(e.getMessage());
//            return ResponseEntity.ok(e.getMessage());
//        }
//    }
//
//    @PutMapping("/password/{id}/reset")
//    public ResponseEntity<UserDTO> resetPassword(@PathVariable("id") long id) {
//        return ResponseEntity.ok(userService.resetPassword(id));
//    }
//
//    @PutMapping("/profile/{username}")
//    public ResponseEntity<UserDTO> updateProfileOfUser(@PathVariable("username") String username, @RequestBody UserDTO userDTO) {
//        return ResponseEntity.ok(userService.updateProfileOfUser(username, userDTO));
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Void> deleteUsers(@RequestBody long[] idList) {
//        if (idList.length > 0) {
//            userService.delete(idList);
//        }
//        return ResponseEntity.noContent().build();
//    }
}
