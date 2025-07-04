package com.example.common.enums;

public enum StatusEnum {
    CHECKING("审核中"),
    CHECK_OK("审核通过"),
    CHECK_ON("审核不通过")
    ;

    public String status;

    StatusEnum(String status) {
        this.status = status;
    }
}
