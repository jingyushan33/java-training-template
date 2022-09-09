package com.lunz.training.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lunz.training.utils.CommonUtils;
import com.lunz.training.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * 默认字段填充处理
 *
 * @author haha
 */
@Slf4j
@Component
public class DefaultMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");

        Object createdById = getFieldValByName("createdById", metaObject);
        Object createdAt = getFieldValByName("createdAt", metaObject);
        Object deleted = getFieldValByName("deleted", metaObject);

        if (StringUtils.isEmpty((String) createdById)) {
            this.strictInsertFill(metaObject, "createdById", String.class, UserUtils.getCurrentUserId());
        }
        if (StringUtils.isEmpty((String) createdAt)) {
            this.strictInsertFill(metaObject, "createdAt", String.class, CommonUtils.now());
        }
        if (deleted == null) {
            this.strictInsertFill(metaObject, "deleted", Integer.class, 0);
        }


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");

        Object updatedById = getFieldValByName("updatedById", metaObject);
        Object updatedAt = getFieldValByName("updatedAt", metaObject);

        if (StringUtils.isEmpty((String)updatedById)) {
            this.setFieldValByName("updatedById", UserUtils.getCurrentUserId(), metaObject);
        }
        if (StringUtils.isEmpty((String)updatedAt)) {
            this.setFieldValByName("updatedAt", CommonUtils.now(), metaObject);
        }
    }

    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        Object obj = fieldVal.get();
        if (Objects.nonNull(obj)) {
            metaObject.setValue(fieldName, obj);
        }
        return this;
    }
}