package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.builder.CustomerBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    @Autowired private ModelMapper modelMapper;

    public CustomerEntity convertToEntity(CustomerDTO customer) {
        CustomerEntity entity = modelMapper.map(customer, CustomerEntity.class);
        return entity;
    }


    public CustomerBuilder convertToBuilder(CustomerDTO customerDTO) {
        CustomerBuilder result = new CustomerBuilder.Builder()
                .setFullName(customerDTO.getFullName())
                .setNeed(customerDTO.getNeed())
                .setNote(customerDTO.getNote())
                .setPhone(customerDTO.getPhone())
                .setEmail(customerDTO.getEmail())
                .setStaffName(customerDTO.getStaffName())
                .setCreatedBy(customerDTO.getCreatedBy())
                .setStaffId(customerDTO.getStaffId()).build();
        return result;
    }

    public CustomerDTO convertToDTO(CustomerEntity entity) {
        CustomerDTO result = modelMapper.map(entity,CustomerDTO.class);
        return result;
    }
}
