
package com.lunz.training.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liangbing
 * @description 返回消息体 data为T泛型
 * @date 2022/8/29
 */
@ApiModel(value = "OperationTResult对象", description = "公用返回对象（泛型）OperationTTResult")
@Data
public class OperationTResult<T>  {

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
    private T data;

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
    public Boolean isSucceeded() {
        return new Integer(OperationResultType.Success.code).equals(this.resultType);
    }

    // 重写error的get方法
    public Boolean getError() {
        return !new Integer(OperationResultType.Success.code).equals(this.resultType);
    }

    /**
     * 无参构造器
     */
    public OperationTResult() {
        super();
    }

    /**
     * 只返回状态，状态码，消息
     *
     * @param resultType
     * @param message
     */
    public OperationTResult(Integer resultType, String message) {
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
    public OperationTResult(Integer resultType, T data) {
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
    public OperationTResult(Integer resultType, String message, T data) {
        super();
        this.resultType = resultType;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回成功
     * @param message 消息内容
     * @param data 消息数据
     * @param <T> 返回消息类型
     * @return
     */
    public static <T> OperationTResult success(String message, T data) {
        return new OperationTResult(OperationResultType.Success.code, message, data);
    }

    /**
     * 返回成功
     * @param data 消息数据
     * @param <T> 返回消息类型
     * @return
     */
    public static <T> OperationTResult succeeded(T data) {
        return OperationTResult.success("", data);
    }

    /**
     * 返回失败
     * @param message
     * @return
     */
    public static OperationTResult fail(String message) {
        return fail(message, OperationResultType.Error);
    }

    public static OperationTResult fail(String message, OperationResultType operationResultType) {
        return new OperationTResult(operationResultType.code, message);
    }

}

