package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.enums.TypeEnum;

import java.util.HashMap;
import java.util.Map;

public class GetTypeUtils {
    public static Map<String, String> getTypeMap() {
        Map<String, String> types = new HashMap<>();
        for (TypeEnum item : TypeEnum.values()) {
            types.put(item.toString(), item.getValue());
        }
        return types;
    }
}


