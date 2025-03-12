package com.maids.LMS.enums;

public enum Role {
    ROLE_SUPER_PARTON,
    ROLE_PATRON;

    // is valid role string
    public static boolean isValidRole(String role) {
        for (Role r : Role.values()) {
            if (r.name().equals(role)) {
                return true;
            }
        }
        return false;
    }
    // is role valid without prefix
    public static boolean isValidRoleWithoutPrefix(String role) {
        return isValidRole("ROLE_" + role);
    }

    // is role valid ignore case
    public static boolean isValidRoleIgnoreCase(String role) {
        return isValidRole(role.toUpperCase());
    }

    // is role valid without prefix ignore case
    public static boolean isValidRoleWithoutPrefixIgnoreCase(String role) {
        return isValidRoleWithoutPrefix(role.toUpperCase());
    }

    // get role by string
    public static Role getRole(String role) {
        for (Role r : Role.values()) {
            if (r.name().equals(role)) {
                return r;
            }
        }
        return null;
    }

    // get role by string ignore case
    public static Role getRoleIgnoreCase(String role) {
        return getRole(role.toUpperCase());
    }

    // get role by string without prefix
    public static Role getRoleWithoutPrefix(String role) {
        return getRole("ROLE_" + role);
    }

    // get role by string without prefix ignore case
    public static Role getRoleWithoutPrefixIgnoreCase(String role) {
        return getRoleWithoutPrefix(role.toUpperCase());
    }

    // get role string
    public static String getRoleString(Role role) {
        return role.name();
    }

    // get role string without prefix
    public static String getRoleStringWithoutPrefix(Role role) {
        return role.name().substring(5);
    }

}
