package com.jye.flying.json.gson;

import com.google.gson.Gson;
import com.jye.flying.json.IConverter;
import com.jye.flying.util.FlyParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 描述：Gson实现的Json转换器
 * </p>
 * 创建人：jye-ixiaojye@163.com
 */
public class GsonConverter implements IConverter {

    private final Gson gson = GsonFactory.getGson();

    public String beanToJson(Object object) {
        return gson.toJson(object);
    }

    public <T> T jsonToBean(String json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }

    public <T> List<T> jsonToList(String json, Class<T> cls) {
        Type type = new FlyParameterizedTypeImpl(cls);
        return gson.fromJson(json, type);
    }

}
