package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.request.AssignmentCustomerDTO;
import com.laptrinhjavaweb.dto.respone.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> loadCustomer(Pageable page, CustomerDTO customerDTO);
    int countTotalItems(CustomerDTO customerDTO);
    CustomerDTO save(CustomerDTO customer);
    void delete(long[] idList);
    CustomerDTO findById(Long id);
    ResponseDTO loadStaff(Long id);
    ResponseDTO saveAssignmentCustomer(AssignmentCustomerDTO assignmentRequest);
    List<TransactionDTO> getTransactions(Long customerId, String code);
    void saveTransaction(TransactionDTO transactionDTO);
}
