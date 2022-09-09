package com.lunz.training.inceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.lunz.kernel.exceptions.HttpResponseException;
import com.lunz.kernel.exceptions.ValidationException;
import com.lunz.kernel.exceptions.core.BaseException;
import com.lunz.kernel.exceptions.util.GlobalExceptionUtil;
import com.lunz.training.constance.Constance;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理类
 *
 * @author haha
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 参数校验错误拦截处理
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<JSONObject> handleConstraintViolationException(ConstraintViolationException e) {

        log.warn("参数校验异常：{}", e.getMessage());

        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        List<String> messages = constraintViolations.stream()
                .map(constraintViolation -> String.format(" '%s' %s",
                        constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
                .distinct().collect(Collectors.toList());

        JSONObject exceptionResult = ExceptionResultUtil.getBaseException(
                new ValidationException(StringUtils.join(messages, ",")));
        return new ResponseEntity<>(exceptionResult, HttpStatus.BAD_REQUEST);
    }

    /**
     * 实体校验错误拦截处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JSONObject> handlerMethodArgumentException(MethodArgumentNotValidException e) {

        log.warn("实体校验异常：{}", e.getMessage());

        BindingResult bindingResult = e.getBindingResult();
        StringBuffer message = new StringBuffer();
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.stream()
                    .collect(Collectors.groupingBy(
                            FieldError::getField,
                            HashMap::new,
                            Collectors.mapping(
                                    DefaultMessageSourceResolvable::getDefaultMessage,
                                    Collectors.joining("|"))))
                    .forEach((k, v) -> message.append(k).append(":[").append(v).append("]").append(";"));
        }
        JSONObject exceptionResult = ExceptionResultUtil.getBaseException(
                new ValidationException(message.toString()));
        return new ResponseEntity<>(exceptionResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<JSONObject> handlerMethodArgumentException(FeignException e) {

        log.warn("RPC通信异常：{}", e.contentUTF8());
        JSONObject exceptionResult = ExceptionResultUtil.getBaseException(
                new HttpResponseException(e.contentUTF8()));
        return new ResponseEntity<>(exceptionResult, HttpStatus.valueOf(e.status()));
    }


    /**
     * 运行时异常错误处理
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<JSONObject> handleRuntimeException(RuntimeException e) {

        log.error("运行时异常：{}", e);

        Pair<BaseException, Integer> exceptionMessage = GlobalExceptionUtil.getExceptionMessage(e, true, Constance.ERROR_FIX, null);
        Object messageObject = exceptionMessage.getLeft();
        JSONObject exceptionResult = JSONObject.parseObject(JSONObject.toJSONString(messageObject), Feature.OrderedField);

        return new ResponseEntity<>(exceptionResult, HttpStatus.valueOf(exceptionMessage.getRight()));

    }

    /**
     * 未知异常错误处理
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {

        log.error("服务器异常：{}", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
