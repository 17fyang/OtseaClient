package com.stu.otseaclient.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/6 15:49
 * @Description:
 */
public class JsonUtil {
    public static final ObjectMapper objectMapper = new ObjectMapper();


    static {
        //忽略在json字符串中存在，在java类中不存在字段，防止错误。
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //该属性设置主要是将忽略空bean转json错误
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, false);

//        该属性设置主要是将对象的所有字段全部列入，若有特殊需求，可以进入JsonSerialize.Inclusion该枚举类查看
//        objectMapper.setSerializationInclusion(JsonSerialize.Typing.DEFAULT_TYPING);
//
//        该属性设置主要是取消将对象的时间默认转换timesstamps(时间戳)形式
//        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
//
//
//        所有日期都统一为以下样式：yyyy-MM-dd HH:mm:ss，这里可以不用我的DateTimeUtil.DATE_FORMAT，手动添加
//        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.DATE_FORMAT));
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
