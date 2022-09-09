package com.lunz.training.result;

/**
 * @author liangbing
 * @description 返回消息类型枚举
 * @date 2022/8/29
 */
public enum OperationResultType {
    ValidError(0, "输入信息验证失败"),
    QueryNull(1, "指定参数的数据不存在"),
    NoChanged(2, "操作没有引发任何变化"),
    Success(3, "操作成功"),
    Error(4, "操作引发错误"),
    Repeat(5, "数据重复错误"),
    Unauthorized(6, "未验证"),
    Unauthenticated(7, "未授权");

    public int code;
    public String message;

    OperationResultType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

