package com.maids.LMS.enums;

public enum BookStatus {
    AVAILABLE,
    BORROWED,
    RESERVED,
    LOST,
    DAMAGED;


    // is valid status string
    public static boolean isValidStatus(String status) {
        for (BookStatus s : BookStatus.values()) {
            if (s.name().equals(status)) {
                return true;
            }
        }
        return false;
    }

    // is status valid ignore case
    public static boolean isValidStatusIgnoreCase(String status) {
        return isValidStatus(status.toUpperCase());
    }

    // get status by string
    public static BookStatus getStatus(String status) {
        for (BookStatus s : BookStatus.values()) {
            if (s.name().equals(status)) {
                return s;
            }
        }
        return null;
    }

    // get status by string ignore case
    public static BookStatus getStatusIgnoreCase(String status) {
        return getStatus(status.toUpperCase());
    }

    // get status string
    public static String getStatusString(BookStatus status) {
        return status.name();
    }

    // get status string ignore case
    public static String getStatusStringIgnoreCase(BookStatus status) {
        return status.name().toLowerCase();
    }

}
