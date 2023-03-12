package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "buildingControllerOfAdmin")
@RequestMapping("/admin")
public class BuildingController {
    @Autowired
    BuildingService buildingService;
    @Autowired
    IUserService userService;

    // _controller Trang danh sách tòa nhà .
    @GetMapping("/building-list")
    public ModelAndView buildingListPage(@ModelAttribute("searchBox")
                                         BuildingSearchDTO buildingSearchDTO) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        // load list building to table
        mav.addObject("buildings", buildingService.loadBuilding(buildingSearchDTO));

        // load list nhân viên phụ trách
        mav.addObject("staffMaps", userService.getStaffmaps());

        // load list quận
        mav.addObject("districts", buildingService.getDistrictMap());

        // load list type
        mav.addObject("types",buildingService.getTypeMap());

        return mav;
    }

    // _controller trang thêm tòa nhà.
    @GetMapping("/building-edit")
    public ModelAndView buildingAddPage(
            @RequestParam(name = "building_id", required = false) Long id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", buildingService.getDistrictMap());
        mav.addObject("types",buildingService.getTypeMap());
        mav.addObject("building", buildingService.findById(id));
        return mav;
    }


}