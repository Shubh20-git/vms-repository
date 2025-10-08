package com.project.vms.vmsproject.util;

import com.project.vms.vmsproject.enums.CveSeverity;
import com.project.vms.vmsproject.enums.CveStatus;
import com.project.vms.vmsproject.enums.ProductStatus;

public class VmsUtility {
    public static String upperCase(String str) {
        if (str == null) return null;
        return str.toUpperCase();
    }

    public static ProductStatus productStatusFromString(String value) {
        if (value == null) return null;
        try {
            return ProductStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static CveStatus cveStatusFromString(String value) {
        if (value == null) return null;
        try {
            return CveStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    public static CveSeverity severityFromString(String value) {
        if (value == null) return null;
        try {
            return CveSeverity.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
