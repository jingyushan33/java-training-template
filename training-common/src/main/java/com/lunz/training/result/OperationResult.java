package com.lunz.training.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liangbing
 * @description 返回消息体 data为Object类型
 * @date 2022/8/29
 */
@ApiModel(value = "OperationResult对象", description = "公用返回对象OperationResult")
@Data
public class OperationResult implements Serializable {
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private Integer resultType;

    /**
     * 消息
     */
    @ApiModelProperty(value = "消息")
    private String message;

    /**
     * 数据对象
     */
    @ApiModelProperty(value = "数据对象")
    private Object data;

    /**
     * 是否成功
     */
    @ApiModelProperty(value = "是否成功")
    private Boolean succeeded;

    /**
     * 是否有错误
     */
    @ApiModelProperty(value = "是否有错误")
    private Boolean error;

    // 重写succeeded的get方法
    public Boolean getSucceeded() {
        return new Integer(OperationResultType.Success.code).equals(this.resultType);
    }

    // 重写error的get方法
    public Boolean getError() {
        return !new Integer(OperationResultType.Success.code).equals(this.resultType);
    }

    /**
     * 无参构造器
     */
    public OperationResult() {
        super();
    }

    public OperationResult(OperationResultType operationResultType) {
        super();
        this.resultType = operationResultType.code;
        this.message = operationResultType.message;
    }

    public OperationResult data(Object data) {
        this.data = data;
        return this;
    }

    public OperationResult message(String message) {
        this.message = message;
        return this;
    }

    /**
     * 只返回状态，状态码，消息
     *
     * @param resultType
     * @param message
     */
    public OperationResult(Integer resultType, String message) {
        super();
        this.resultType = resultType;
        this.message = message;
    }

    /**
     * 只返回状态，状态码，数据对象
     *
     * @param resultType
     * @param data
     */
    public OperationResult(Integer resultType, Object data) {
        super();
        this.resultType = resultType;
        this.data = data;
    }

    /**
     * 返回全部信息即状态，状态码，消息，数据对象
     *
     * @param resultType
     * @param message
     * @param data
     */
    public OperationResult(Integer resultType, String message, Object data) {
        super();
        this.resultType = resultType;
        this.message = message;
        this.data = data;
    }

    public static OperationResult success() {
        return new OperationResult(OperationResultType.Success.code, "");
    }

    public static OperationResult success(String message) {
        return new OperationResult(OperationResultType.Success.code, message);
    }

    public static OperationResult success(String message, Object data) {
        return new OperationResult(OperationResultType.Success.code, message, data);
    }

    public static OperationResult success(Object data) {
        return new OperationResult(OperationResultType.Success.code, data);
    }

    public static OperationResult fail(String message) {
        return new OperationResult(OperationResultType.Error.code, message);
    }
}

