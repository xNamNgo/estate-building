package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.respone.BuildingRequestDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.DisplayTagUtils;
import com.laptrinhjavaweb.utils.GetDistrictUtils;
import com.laptrinhjavaweb.utils.GetTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "buildingControllerOfAdmin")
@RequestMapping("/admin")
public class BuildingController {
    @Autowired
    IBuildingService buildingService;
    @Autowired
    IUserService userService;

    // _controller Trang danh sách tòa nhà .
    @GetMapping("/building-list")
    public ModelAndView buildingListPage(@ModelAttribute("searchBox")
                                         BuildingSearchDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        // Phân trang
        // load list building to table
        DisplayTagUtils.of(request, model);
        Pageable page = new PageRequest(model.getPage() - 1, model.getMaxPageItems());
        List<BuildingRequestDTO> buildingList = buildingService.loadBuilding(page, model);
        model.setListResult(buildingList);
        model.setTotalItem(buildingService.countTotalItems());
        mav.addObject("buildings", model);

        // load list nhân viên phụ trách
        mav.addObject("staffMaps", userService.getStaffmaps());

        // load list quận
        mav.addObject("districts", GetDistrictUtils.getDistrictMap());

        // load list type
        mav.addObject("types", GetTypeUtils.getTypeMap());

        return mav;
    }

    // _controller trang thêm tòa nhà.
    @GetMapping("/building-edit")
    public ModelAndView buildingAddPage(
            @RequestParam(name = "building_id", required = false) Long id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", GetDistrictUtils.getDistrictMap());
        mav.addObject("types", GetTypeUtils.getTypeMap());
        mav.addObject("building", buildingService.findById(id));
        return mav;
    }


}
