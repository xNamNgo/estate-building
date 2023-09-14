package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionTypeService;
import com.laptrinhjavaweb.service.impl.UserService;
import com.laptrinhjavaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class CustomerController {
    @Autowired ICustomerService customerService;
    @Autowired UserService userService;
    @Autowired ITransactionTypeService transactionTypeService;

    @GetMapping("/customer-list")
    public ModelAndView customerPage(@ModelAttribute(
            "customer") CustomerDTO customer, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");

        DisplayTagUtils.of(request, customer);
        Pageable page = new PageRequest(customer.getPage() - 1, customer.getMaxPageItems());
        List<CustomerDTO> customerList = customerService.loadCustomer(page, customer);
        customer.setListResult(customerList);
        customer.setTotalItem(customerService.countTotalItems(customer));
        mav.addObject("customers", customer);
        mav.addObject("staffs", userService.getStaffMap());
        return mav;
    }

    @GetMapping("/customer-edit")
    public ModelAndView customerEditPage(@RequestParam(name = "customer_id",
                                                       required = false) Long id) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("mapTransactionTypes", transactionTypeService.getTransactionType());
        if (id != null) {
            mav.addObject("customer", customerService.findById(id));
            Map<String, List<TransactionDTO>> listMap = new HashMap<>();
            for (String code : transactionTypeService.getTransactionType().keySet()) {
                listMap.put(code, customerService.getTransactions(id, code));
            }
            mav.addObject("mapTransaction", listMap);
        } else {
            mav.addObject("customer", new CustomerDTO());
        }
        return mav;
    }
}
