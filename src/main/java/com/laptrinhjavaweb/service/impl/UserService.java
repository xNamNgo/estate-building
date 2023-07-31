package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.PasswordDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.respone.ResponseDTO;
import com.laptrinhjavaweb.dto.respone.StaffResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.exception.MyException;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingRepository buildingRepository;

//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
    @Autowired
    private UserConverter userConverter;

    @Override
    public ResponseDTO loadStaff(Long buildingId){
        List<StaffResponseDTO> data = new ArrayList<>();
        List<UserEntity> allStaffList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> assignedStaffList = userRepository.findByBuildingId(buildingId);

        for (UserEntity staffItem : allStaffList) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(staffItem.getId());
            staffResponseDTO.setFullName(staffItem.getFullName());

            // Kiểm tra id current staff có trong assignedStaffList không.
            boolean isAssigned = assignedStaffList.stream().anyMatch(assignedStaff -> staffItem.getId() == assignedStaff.getId());
            staffResponseDTO.setChecked(isAssigned ? "checked" : ""); // "checked" attribute in html
            data.add(staffResponseDTO);
        }
        return new ResponseDTO(data, "success", "");
    }


    // giao tòa nhà cho nhân viên quản lý
    @Transactional
    @Override
    public ResponseDTO saveAssignmentBuilding(AssignmentBuildingRequestDTO assignmentRequest) {
        // receive data from request
        Long buildingId = assignmentRequest.getBuildingId();
        long[] staffIdList = assignmentRequest.getStaffIdList();

        Optional<BuildingEntity> optionalBuildingEntity = buildingRepository.findById(buildingId);
        if (optionalBuildingEntity.isPresent()) {
            BuildingEntity buildingEntity = optionalBuildingEntity.get();
            List<UserEntity> userEntities = userRepository.findByIdIn(staffIdList);
            buildingEntity.setUsers(userEntities);
            buildingRepository.save(buildingEntity);
            return new ResponseDTO("success", "Giao tòa nhà thành công!");
        } else {
            // Handle the case when the building with the given ID is not found
            return new ResponseDTO("error", "Tòa nhà không tồn tại!");
        }
    }
    @Override
    public Map<Long, String> getStaffMap() {
        Map<Long, String> result = new HashMap<>();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        for (UserEntity item : staffs) {
            result.put(item.getId(), item.getFullName());
        }
        return result;
    }


    @Override
    public UserDTO findOneByUserNameAndStatus(String name, int status) {
        return userConverter.convertToDto(userRepository.findOneByUserNameAndStatus(name, status));
    }

//    @Override
//    public List<UserDTO> getUsers(String searchValue, Pageable pageable) {
//        Page<UserEntity> users = null;
//        if (StringUtils.isNotBlank(searchValue)) {
//            users = userRepository.findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0, pageable);
//        } else {
//            users = userRepository.findByStatusNot(0, pageable);
//        }
//        List<UserEntity> newsEntities = users.getContent();
//        List<UserDTO> result = new ArrayList<>();
//        for (UserEntity userEntity : newsEntities) {
//            UserDTO userDTO = userConverter.convertToDto(userEntity);
////            userDTO.setRoleCode(userEntity.getRoles().get(0).getCode());
//            userDTO.setRoleCode(userEntity.getRoles().get(0).getCode());
//            result.add(userDTO);
//        }
//        return result;
//    }
//
//    @Override
//    public int getTotalItems(String searchValue) {
//        int totalItem = 0;
//        if (StringUtils.isNotBlank(searchValue)) {
//            totalItem = (int) userRepository.countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0);
//        } else {
//            totalItem = (int) userRepository.countByStatusNot(0);
//        }
//        return totalItem;
//    }
//
//    @Override
//    public UserDTO findOneByUserName(String userName) {
//        UserEntity userEntity = userRepository.findOneByUserName(userName);
//        UserDTO userDTO = userConverter.convertToDto(userEntity);
//        return userDTO;
//    }
//
//
//
//    @Override
//    public UserDTO findUserById(long id) {
//        UserEntity entity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
//        List<RoleEntity> roles = entity.getRoles();
//        UserDTO dto = userConverter.convertToDto(entity);
//        roles.forEach(item -> {
//            dto.setRoleCode(item.getCode());
//        });
//        return dto;
//    }

//    @Override
//    @Transactional
//    public UserDTO insert(UserDTO newUser) {
//        RoleEntity role = roleRepository.findOneByCode(newUser.getRoleCode());
//        UserEntity userEntity = userConverter.convertToEntity(newUser);
//        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
//        userEntity.setStatus(1);
//        userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
//        return userConverter.convertToDto(userRepository.save(userEntity));
//    }

//    @Override
//    @Transactional
//    public UserDTO update(Long id, UserDTO updateUser) {
//        RoleEntity role = roleRepository.findOneByCode(updateUser.getRoleCode());
//        UserEntity oldUser = userRepository.findOne(id);
//        UserEntity userEntity = userConverter.convertToEntity(updateUser);
//        userEntity.setUserName(oldUser.getUserName());
//        userEntity.setStatus(oldUser.getStatus());
//        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
//        userEntity.setPassword(oldUser.getPassword());
//        return userConverter.convertToDto(userRepository.save(userEntity));
//    }
//
//    @Override
//    @Transactional
//    public void updatePassword(long id, PasswordDTO passwordDTO) throws MyException {
//        UserEntity user = userRepository.findOne(id);
//        if (passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())
//                && passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
//            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
//            userRepository.save(user);
//        } else {
//            throw new MyException(SystemConstant.CHANGE_PASSWORD_FAIL);
//        }
//    }
//
//    @Override
//    @Transactional
//    public UserDTO resetPassword(long id) {
//        UserEntity userEntity = userRepository.findOne(id);
//        userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
//        return userConverter.convertToDto(userRepository.save(userEntity));
//    }
//
//    @Override
//    @Transactional
//    public UserDTO updateProfileOfUser(String username, UserDTO updateUser) {
//        UserEntity oldUser = userRepository.findOneByUserName(username);
//        oldUser.setFullName(updateUser.getFullName());
//        return userConverter.convertToDto(userRepository.save(oldUser));
//    }
//
//    @Override
//    @Transactional
//    public void delete(long[] ids) {
//        for (Long item : ids) {
//            UserEntity userEntity = userRepository.findOne(item);
//            userEntity.setStatus(0);
//            userRepository.save(userEntity);
//        }
//    }
}
