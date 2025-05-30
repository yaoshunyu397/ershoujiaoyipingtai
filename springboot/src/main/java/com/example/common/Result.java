// 声明该类所在的包，便于组织和管理代码
package com.example.common;

// 导入自定义的枚举类，该类定义了各种操作结果的状态码和提示信息
import com.example.common.enums.ResultCodeEnum;

/**
 * Result 类用于封装接口返回的结果，包含状态码、提示信息和具体数据。
 * 提供了一系列静态方法，方便快速创建表示成功或失败的结果对象。
 */
public class Result {
    // 操作结果的状态码，用于标识操作执行的状态
    private String code;
    // 操作结果的提示信息，向调用者说明操作的具体情况
    private String msg;
    // 操作结果携带的数据，可能是业务数据、查询结果等
    private Object data;

    /**
     * 私有构造函数，用于创建一个包含数据的 Result 对象。
     * 该构造函数为私有，主要通过静态方法来创建 Result 对象。
     *
     * @param data 操作结果携带的数据
     */
    private Result(Object data) {
        this.data = data;
    }

    /**
     * 无参构造函数，用于创建一个不包含数据的 Result 对象。
     */
    public Result() {
    }

    /**
     * 静态方法，用于创建一个表示操作成功且不包含数据的 Result 对象。
     * 状态码和提示信息使用 ResultCodeEnum 中定义的成功状态。
     *
     * @return 表示操作成功的 Result 对象
     */
    public static Result success() {
        // 创建一个不包含数据的 Result 对象
        Result tResult = new Result();
        // 设置状态码为成功状态码
        tResult.setCode(ResultCodeEnum.SUCCESS.code);
        // 设置提示信息为成功提示信息
        tResult.setMsg(ResultCodeEnum.SUCCESS.msg);
        return tResult;
    }

    /**
     * 静态方法，用于创建一个表示操作成功且包含数据的 Result 对象。
     * 状态码和提示信息使用 ResultCodeEnum 中定义的成功状态。
     *
     * @param data 操作结果携带的数据
     * @return 表示操作成功的 Result 对象
     */
    public static Result success(Object data) {
        // 创建一个包含数据的 Result 对象
        Result tResult = new Result (data);
        // 设置状态码为成功状态码
        tResult.setCode(ResultCodeEnum.SUCCESS.code);
        // 设置提示信息为成功提示信息
        tResult.setMsg(ResultCodeEnum.SUCCESS.msg);
        return tResult;
    }

    /**
     * 静态方法，用于创建一个表示操作失败且不包含额外信息的 Result 对象。
     * 状态码和提示信息使用 ResultCodeEnum 中定义的系统错误状态。
     *
     * @return 表示操作失败的 Result 对象
     */
    public static Result error() {
        // 创建一个不包含数据的 Result 对象
        Result tResult = new Result();
        // 设置状态码为系统错误状态码
        tResult.setCode(ResultCodeEnum.SYSTEM_ERROR.code);
        // 设置提示信息为系统错误提示信息
        tResult.setMsg(ResultCodeEnum.SYSTEM_ERROR.msg);
        return tResult;
    }

    /**
     * 静态方法，用于创建一个表示操作失败且自定义状态码和提示信息的 Result 对象。
     *
     * @param code 自定义的状态码
     * @param msg 自定义的提示信息
     * @return 表示操作失败的 Result 对象
     */
    public static Result error(String code, String msg) {
        // 创建一个不包含数据的 Result 对象
        Result tResult = new Result();
        // 设置为自定义的状态码
        tResult.setCode(code);
        // 设置为自定义的提示信息
        tResult.setMsg(msg);
        return tResult;
    }

    /**
     * 静态方法，用于创建一个表示操作失败且使用 ResultCodeEnum 中枚举状态的 Result 对象。
     *
     * @param resultCodeEnum 包含状态码和提示信息的枚举对象
     * @return 表示操作失败的 Result 对象
     */
    public static Result error(ResultCodeEnum resultCodeEnum) {
        // 创建一个不包含数据的 Result 对象
        Result tResult = new Result();
        // 设置状态码为枚举对象中的状态码
        tResult.setCode(resultCodeEnum.code);
        // 设置提示信息为枚举对象中的提示信息
        tResult.setMsg(resultCodeEnum.msg);
        return tResult;
    }

    /**
     * 获取操作结果的状态码。
     *
     * @return 操作结果的状态码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置操作结果的状态码。
     *
     * @param code 要设置的状态码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取操作结果的提示信息。
     *
     * @return 操作结果的提示信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置操作结果的提示信息。
     *
     * @param msg 要设置的提示信息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取操作结果携带的数据。
     *
     * @return 操作结果携带的数据
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置操作结果携带的数据。
     *
     * @param data 要设置的数据
     */
    public void setData(Object data) {
        this.data = data;
    }
}

