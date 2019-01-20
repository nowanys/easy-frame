package com.frame.easy.common.constant.status;

/**
 * 角色状态
 *
 * @Author tengchong
 * @Date 2018/9/4
 */

public enum RoleStatus {
    // 启用
    ENABLE(1, "启用"),
    // 禁用
    DISABLE(2, "禁用");

    int code;
    String message;

    RoleStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
