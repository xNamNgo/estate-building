package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.enums.DistrictEnum;

import java.util.HashMap;
import java.util.Map;

public class GetDistrictUtils {
    public static Map<String, String> getDistrictMap() {
        Map<String, String> districts = new HashMap<>();
        for (DistrictEnum item : DistrictEnum.values()) {
            districts.put(item.toString(), item.getValue());
        }
        return districts;
    }
}
