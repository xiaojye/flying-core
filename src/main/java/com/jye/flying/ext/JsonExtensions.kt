package com.jye.flying.ext

import com.jye.flying.json.FlyJsonConverter
import org.json.JSONArray
import org.json.JSONObject

/**
 * 描述：Json相关扩展函数
 * 创建人：jye-ixiaojye@163.com
 */

fun Any.toJson(): String {
    if (this is JSONObject || this is JSONArray) {
        return this.toString()
    }
    return FlyJsonConverter.beanToJson(this)
}

fun <T> Any.toBean(cls: Class<T>): T {
    return this.toJson().toBean(cls)
}

fun <T> String.toBean(cls: Class<T>): T {
    return FlyJsonConverter.jsonToBean(this, cls)
}

fun <T> Any.toList(cls: Class<T>): List<T>? {
    return this.toJson().toList(cls)
}

fun <T> String.toList(cls: Class<T>): List<T> {
    return FlyJsonConverter.jsonToList(this, cls)
}

fun String.toJSONObject(): JSONObject {
    return JSONObject(this)
}

fun String.toJSONArray(): JSONArray {
    return JSONArray(this)
}