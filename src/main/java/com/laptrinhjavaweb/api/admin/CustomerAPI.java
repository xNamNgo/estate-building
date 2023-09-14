package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.request.AssignmentCustomerDTO;
import com.laptrinhjavaweb.dto.respone.ResponseDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {
    @Autowired ICustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createBuilding(@RequestBody CustomerDTO customer) {
        ResponseEntity<CustomerDTO> response = ResponseEntity.ok(customerService.save(customer));
        return response;
    }

    // Xóa tòa nhà
    @DeleteMapping
    public ResponseEntity<Void> deleteBuilding(@RequestBody long[] idList) {
        if (idList.length > 0) {
            customerService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/staffs")
    public ResponseDTO loadStaff(@RequestParam(required = false, value = "customer_id") Long customerId) {
        ResponseDTO result = new ResponseDTO();
        if(customerId != null){
            result = customerService.loadStaff(customerId);
        }
        return result;
    }

    @PostMapping("/assignment")
    public ResponseDTO saveAssignmentBuilding(
            @RequestBody AssignmentCustomerDTO assignmentRequest) {
        ResponseDTO result = customerService.saveAssignmentCustomer(assignmentRequest);
        return result;
    }

    @GetMapping("/transaction")
    public List<TransactionDTO> getTransaction(@RequestParam Long buildingId, String code) {
        List<TransactionDTO> result = customerService.getTransactions(buildingId, code);
        return result;
    }

    @PostMapping("/transaction")
    public void saveTransaction(@RequestBody TransactionDTO transactionDTO) {
        customerService.saveTransaction(transactionDTO);
    }

}
