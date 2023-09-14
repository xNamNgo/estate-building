package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchRespone;
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

    @GetMapping("/building-list")
    public ModelAndView buildingListPage(@ModelAttribute("searchBox")
                                         BuildingSearchRequest model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");

        // Phân trang
        DisplayTagUtils.of(request, model);
        Pageable page = new PageRequest(model.getPage() - 1, model.getMaxPageItems());
        List<BuildingSearchRespone> buildingList = buildingService.loadBuilding(page, model);
        model.setListResult(buildingList);
        model.setTotalItem(buildingService.countTotalItems(model));
        mav.addObject("buildings", model);

        mav.addObject("staffs", userService.getStaffMap());
        mav.addObject("districts", GetDistrictUtils.getDistrictMap());
        mav.addObject("types", GetTypeUtils.getTypeMap());
        return mav;
    }

    @GetMapping("/building-edit")
    public ModelAndView buildingAddPage(@RequestParam(name = "building_id", required = false) Long id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        if (id != null) {
            mav.addObject("building", buildingService.findById(id));
        } else {
            mav.addObject("building", new BuildingSearchRespone());
        }
        mav.addObject("districts", GetDistrictUtils.getDistrictMap());
        mav.addObject("types", GetTypeUtils.getTypeMap());
        return mav;
    }


}
