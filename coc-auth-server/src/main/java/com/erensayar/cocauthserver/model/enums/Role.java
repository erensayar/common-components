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

    public static Role getValById(Integer id) {
        for (Role e : values()) {
            if (e.val == id) {
                return e;
            }
        }
        return UNKNOWN;
    }

}
