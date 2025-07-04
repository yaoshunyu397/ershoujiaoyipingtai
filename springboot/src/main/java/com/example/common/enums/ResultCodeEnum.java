/**
 * 该枚举类定义了系统中各种操作结果的状态码和对应的提示信息，
 * 用于统一管理和返回操作结果，提高代码的可读性和可维护性。
 */
package com.example.common.enums;

/**
 * 操作结果状态码枚举类，包含系统各种操作结果的状态码和提示信息。
 */
public enum ResultCodeEnum {
    /**
     * 操作成功，状态码为 200，提示信息为 "成功"。
     */
    SUCCESS("200", "成功"),

    /**
     * 参数异常，状态码为 400，提示信息为 "参数异常"。
     */
    PARAM_ERROR("400", "参数异常"),
    /**
     * Token 无效，状态码为 401，提示信息为 "无效的token"。
     */
    TOKEN_INVALID_ERROR("401", "无效的token"),
    /**
     * Token 验证失败，状态码为 401，提示信息为 "token验证失败，请重新登录"。
     */
    TOKEN_CHECK_ERROR("401", "token验证失败，请重新登录"),
    /**
     * 参数缺失，状态码为 4001，提示信息为 "参数缺失"。
     */
    PARAM_LOST_ERROR("4001", "参数缺失"),

    /**
     * 系统异常，状态码为 500，提示信息为 "系统异常"。
     */
    SYSTEM_ERROR("500", "系统异常"),
    /**
     * 用户名已存在，状态码为 5001，提示信息为 "用户名已存在"。
     */
    USER_EXIST_ERROR("5001", "用户名已存在"),
    /**
     * 用户未登录，状态码为 5002，提示信息为 "用户未登录"。
     */
    USER_NOT_LOGIN("5002", "用户未登录"),
    /**
     * 账号或密码错误，状态码为 5003，提示信息为 "账号或密码错误"。
     */
    USER_ACCOUNT_ERROR("5003", "账号或密码错误"),
    /**
     * 用户不存在，状态码为 5004，提示信息为 "用户不存在"。
     */
    USER_NOT_EXIST_ERROR("5004", "用户不存在"),
    /**
     * 原密码输入错误，状态码为 5005，提示信息为 "原密码输入错误"。
     */
    PARAM_PASSWORD_ERROR("5005", "原密码输入错误"),
    /**
     * 商品已收藏，状态码为 5006，提示信息为 "您已收藏过该商品！"。
     */
    COLLECT_ALREADY_ERROR("5006","您已收藏过该商品！"),
    /**
     * 余额不足，状态码为 5007，提示信息为 "余额不足，请充值！"。
     */
    BALANCE_NOT_ENOUGH_ERROR("5007","余额不足，请充值！"),
    /**
     * 状态转换错误，状态码为 5008，提示信息为 "状态转换错误"。
     */
    STATUS_ERROR("5008","状态转换错误"),
    ;

    /**
     * 操作结果的状态码
     */
    public String code;
    /**
     * 操作结果的提示信息
     */
    public String msg;

    /**
     * 枚举的构造方法，用于初始化状态码和提示信息。
     *
     * @param code 操作结果的状态码
     * @param msg 操作结果的提示信息
     */
    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
