package com.lunz.training.utils;

import com.lunz.training.annotation.FieldDesc;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 日志提取
 *
 * @author puritykid
 */
@SuppressWarnings("unchecked")
public class LogUtils<T> {


    private static final String CONTENT_SPLIT = ",";

    /**
     * 对象比较器
     * 比较结果eg：1、字段名称loginName,旧值:liu,新值:gu;2、字段名称address,旧值:hunan,新值:neimenggu
     *
     * @param oldBean 修改之前的实体
     * @param newBean 修改之后的实体
     * @return 实体变化信息
     */
    public String compareObject(Object oldBean, Object newBean) {
        StringBuilder str = new StringBuilder();

        T pojo1 = (T) oldBean;
        T pojo2 = (T) newBean;
        try {
            Class<?> clazz = pojo1.getClass();
            Field[] fields = pojo1.getClass().getDeclaredFields();
            for (Field field : fields) {
                // 跳过静态字段比较
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                // 属性比较
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(pojo1);
                Object o2 = getMethod.invoke(pojo2);
                if (o1 == null || o2 == null) {
                    continue;
                }
                if (!o1.toString().equals(o2.toString())) {

                    if (field.isAnnotationPresent(FieldDesc.class)) {
                        FieldDesc fieldDesc = field.getAnnotation(FieldDesc.class);
                        String value = fieldDesc.value();
                        str.append(value)
                                .append("由“")
                                .append(o1)
                                .append("”")
                                .append("改为“")
                                .append(o2)
                                .append("”")
                                .append(CONTENT_SPLIT);
                    } else {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // }
        return StringUtils.substringBeforeLast(str.toString(), CONTENT_SPLIT);
    }

    public static void main(String[] args) {
        UserDetails o1 = new UserDetails();
        o1.setClientId("client1");
        o1.setUserId("111");
        o1.setUserName("haha11");
        o1.setLoginName("cxj");


        UserDetails o2 = new UserDetails();
        o2.setClientId("client2");
        o2.setUserId("222");
        o2.setUserName("haha");
        o2.setLoginName("cxj");

        UserDetails o3 = new UserDetails();
        o3.setClientId("client1");
        o3.setUserId("111");
        o3.setUserName("haha11");
        o3.setLoginName("cxj");

        LogUtils<UserDetails> mapLogUtils = new LogUtils<>();
        String s = mapLogUtils.compareObject(o1, o2);
        System.out.println(s);
    }

}
