package com.erensayar.cocauthserver.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    UNKNOWN(0, 0),

    ADMIN(1, 1),
    USER(2, 1);

    private final int val;
    private final int roleGroupId;

    public static Role getRoleByVal(Integer id) {
        for (Role e : values()) {
            if (e.val == id) {
                return e;
            }
        }
        return UNKNOWN;
    }

    public static Role getRoleByGroupId(Integer id) {
        for (Role e : values()) {
            if (e.roleGroupId == id) {
                return e;
            }
        }
        return UNKNOWN;
    }

}
