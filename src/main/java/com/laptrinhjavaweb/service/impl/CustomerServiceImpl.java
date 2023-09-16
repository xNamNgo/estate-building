package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.CustomerBuilder;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.request.AssignmentCustomerDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRespone;
import com.laptrinhjavaweb.dto.respone.ResponseDTO;
import com.laptrinhjavaweb.dto.respone.StaffResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired private CustomerRepository customerRepository;
    @Autowired private CustomerConverter customerConverter;
    @Autowired private UserRepository userRepository;
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private TransactionConverter transactionConverter;

    @Override
    public List<CustomerDTO> loadCustomer(Pageable page, CustomerDTO customerDTO) {
        if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            customerDTO.setStaffId(SecurityUtils.getPrincipal().getId());
        }
        List<CustomerDTO> result = new ArrayList<>();
        CustomerBuilder customerBuilder = customerConverter.convertToBuilder(customerDTO);

        List<CustomerEntity> customerEntities = customerRepository.findByCondition(page, customerBuilder);

        for (CustomerEntity entity : customerEntities) {
            CustomerDTO dto = customerConverter.convertToDTO(entity);
            result.add(dto);
        }
        return result;
    }

    @Override
    public int countTotalItems(CustomerDTO customerDTO) {
        CustomerBuilder customerBuilder = customerConverter.convertToBuilder(customerDTO);
        return customerRepository.countTotalItem(customerBuilder);
    }

    @Override
    public CustomerDTO save(CustomerDTO customer) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customer);
        customerRepository.save(customerEntity);
        return customer;
    }

    @Override
    @Transactional
    public void delete(long[] idList) {
        if (idList.length > 0) {
            Long count = customerRepository.countByIdIn(idList);
            if (count != idList.length) {
                throw new NotFoundException("Không tìm khách hàng nhà hợp lệ!");
            }
            customerRepository.deleteByIdIn(idList);
        }
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerDTO result = new CustomerDTO();
        result = customerConverter.convertToDTO(customerRepository.findById(id).get());
        return result;
    }

    @Override
    public ResponseDTO loadStaff(Long customerId) {
        List<StaffResponseDTO> data = new ArrayList<>();
        List<UserEntity> allStaffList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> assignedStaffList = userRepository.findByCustomers_Id(customerId);

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

    @Transactional
    @Override
    public ResponseDTO saveAssignmentCustomer(AssignmentCustomerDTO assignmentRequest) {
        // receive data from request
        Long customerId = assignmentRequest.getCustomerId();
        long[] staffIdList = assignmentRequest.getStaffIdList();

        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customerId);
        if (optionalCustomerEntity.isPresent()) {
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            List<UserEntity> userEntities = userRepository.findByIdIn(staffIdList);
            customerEntity.setStaff(userEntities);
            customerRepository.save(customerEntity);
            return new ResponseDTO("success", "Giao khách hàng thành công!");
        } else {
            // Handle the case when the building with the given ID is not found
            return new ResponseDTO("error", "Khách hàng không tồn tại!");
        }
    }

    @Override
    public List<TransactionDTO> getTransactions(Long id, String code) {
        List<TransactionEntity> transactionEntityList = transactionRepository.findByCodeAndCustomer_Id(code, id);
        List<TransactionDTO> result = new ArrayList<>();
        for (TransactionEntity transactionEntity : transactionEntityList) {
            TransactionDTO transactionDTO = transactionConverter.convertToDTO(transactionEntity);
            result.add(transactionDTO);
        }
        return result;
    }

    @Override
    @Transactional
    public void saveTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = transactionConverter.convertToEntity(transactionDTO);
        CustomerEntity customerEntity = customerRepository.findById(transactionDTO.getCustomerId()).get();
        transactionEntity.setCustomer(customerEntity);
        transactionRepository.save(transactionEntity);
    }


}
