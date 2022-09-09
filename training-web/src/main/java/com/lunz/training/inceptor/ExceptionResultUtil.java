package com.lunz.training.inceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.lunz.training.constance.Constance;
import com.lunz.kernel.exceptions.core.BaseException;
import com.lunz.kernel.exceptions.util.GlobalExceptionUtil;


/**
 * 异常处理工具
 * @author haha
 */
public class ExceptionResultUtil {

    private ExceptionResultUtil(){

    }

    public static JSONObject getBaseException(Exception e) {
        BaseException baseException = new BaseException();
        baseException.setErrorCode(Constance.ERROR_FIX + e.getCause().getMessage());
        baseException.setErrorMessage(new String[]{e.getMessage()});
        baseException.setStackTrace(GlobalExceptionUtil.getStackTrace(e, true));
        return JSONObject.parseObject(JSONObject.toJSONString(baseException), Feature.OrderedField);
    }
}
