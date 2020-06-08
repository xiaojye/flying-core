package com.jye.flying;

import android.content.Context;

import com.jye.flying.cache.FlyCacheManager;
import com.jye.flying.log.FlyLogger;

import org.jetbrains.annotations.NotNull;

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
public final class Flying {

    private static Flying sInstance;

    private Flying() {
    }

    public static Flying getInstance() {
        if (sInstance == null) {
            synchronized (Flying.class) {
                sInstance = new Flying();
            }
        }
        return sInstance;
    }

    /**
     * 初始化（在此初始化内部组件）
     *
     * @param context Context对象（通过getApplicationContext获取Application上下文）
     */
    public void initialize(@NotNull Context context) {
        initialize(context, true);
    }

    /**
     * 初始化（在此初始化内部组件）
     *
     * @param context   Context对象（通过getApplicationContext获取Application上下文）
     * @param enableLog 是否开启日志功能（默认开启）
     */
    public void initialize(@NotNull Context context, boolean enableLog) {
        FlyLogger.getLogConfig().setLogEnable(enableLog);

        //初始化缓存组件
        FlyCacheManager.getInstance().init(context.getApplicationContext());
    }
}
