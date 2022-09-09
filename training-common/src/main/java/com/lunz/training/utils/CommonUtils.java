package com.lunz.training.utils;

import com.google.common.collect.Lists;
import com.lunz.training.constance.Constance;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 公共方法工具类
 *
 * @author puritykid
 */
public class CommonUtils {

    private CommonUtils() {
    }

    public static String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constance.DATE_PATTERN_19));
    }

    /**
     * Base64加密
     */
    public static String base64Encrypt(String original) throws ValidationException {
        if (StringUtils.isEmpty(original)) {
            throw new ValidationException("传入原始字段为空!");
        }
        return Base64.getEncoder().encodeToString(original.getBytes());
    }

    /**
     * Base64解密
     */
    public static String base64Decrypt(String base64Str) throws ValidationException {
        if (StringUtils.isEmpty(base64Str)) {
            throw new ValidationException("传入加密Base64字段为空!");
        }
        byte[] decode = Base64.getDecoder().decode(base64Str);
        return new String(decode);
    }


    /**
     * 转换 2007-12-03T10:15:30 -> YYYY-MM-dd HH:mm:ss
     */
    public static String formatDate(String dateText) {

        if (StringUtils.isNotEmpty(dateText)) {
            return LocalDateTime.parse(dateText).format(DateTimeFormatter.ofPattern(Constance.DATE_PATTERN_19));
        }
        return dateText;
    }

    /**
     * 转换 指定日期格式 -> YYYY-MM-dd HH:mm:ss
     */
    public static String formatDate(String dateText, String datePattern) {

        if (StringUtils.isNotEmpty(dateText)) {
            return LocalDateTime.parse(dateText, DateTimeFormatter.ofPattern(datePattern)).format(DateTimeFormatter.ofPattern(Constance.DATE_PATTERN_19));
        }
        return dateText;
    }


    /**
     * 脱敏-字符长度至少是8
     *
     * @param text  脱敏字段
     * @param limit 限制脱敏长度
     * @param start 开始脱敏位置
     * @param end   末尾保留位数
     * @return 脱敏值
     */
    public static String hideVal(String text, int limit, int start, int end) {
        if (StringUtils.isBlank(text)) {
            return "";
        }
        if (StringUtils.length(text) <= limit) {
            return text;
        }

        int index = StringUtils.length(text) - end;
        return StringUtils.rightPad(StringUtils.left(text, start), 7, "*").concat(StringUtils.mid(text, index, StringUtils.length(text)));
    }

    /**
     * 获取带时区时间
     */
    public static String nowZone() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constance.PATTERN_DATE_ZONE));
    }

    /**
     * 获取带时区时间
     */
    public static String dateZone(String dateText) {
        return LocalDateTime.parse(dateText, DateTimeFormatter.ofPattern(Constance.DATE_PATTERN_19)).format(DateTimeFormatter.ofPattern(Constance.PATTERN_DATE_ZONE));
    }


    /**
     * 判断对象是否为null和空
     */
    public static boolean isObjectNull(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map) object).isEmpty();
        } else if (object instanceof String) {
            return ((String) object).trim().length() == 0;
        }
        return false;
    }

    /**
     * 生成UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }


    /**
     * 分批集合
     * @param list 大集合
     * @param batchSize 每批次大小
     */
    public static <E> List<List<E>> splitBatchList(List<E> list,int batchSize) {
        return  Lists.partition(list, batchSize);
    }

}
