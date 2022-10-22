package com.lunz.training.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 公共字段定义
 * @author haha
 */
@Data
public class BaseModel {

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdById;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updatedById;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updatedAt;
    /**
     * 删除人
     */
    private String deletedById;
    /**
     * 删除时间
     */
    private String deletedAt;
    /**
     * 删除标志位（0未删除/1已逻辑删）
     */
    //@TableLogic(value = "0", delval = "1")
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
