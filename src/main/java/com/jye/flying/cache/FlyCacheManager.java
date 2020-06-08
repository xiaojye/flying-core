package com.jye.flying.cache;

import android.content.Context;

import androidx.collection.SimpleArrayMap;

import com.jye.flying.cache.acache.ACache;
import com.jye.flying.cache.sharedprefs.SPCache;
import com.jye.flying.cache.sharedprefs.SecuritySharedPreference;

import java.io.File;

/**
 * 描述：缓存管理类
 * 创建人：jye-ixiaojye@163.com
 */
public final class FlyCacheManager {

    //SharedPreferences缓存对象Map表
    private SimpleArrayMap<String, SPCache> mSPCacheMap = new SimpleArrayMap<>();

    private Context mContext;

    private static volatile FlyCacheManager sInstance = null;

    private FlyCacheManager() {
    }

    public static FlyCacheManager getInstance() {
        if (sInstance == null) {
            synchronized (FlyCacheManager.class) {
                if (sInstance == null) {
                    sInstance = new FlyCacheManager();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {
        mContext = context;
    }

    //==============================================================================================
    //SPCache
    //==============================================================================================

    /**
     * 返回指定的SPCache（Mode默认为MODE_PRIVATE，且明文存储）
     *
     * @param name 存储文件名称
     */
    public SPCache spCache(String name) {
        return spCache(name, Context.MODE_PRIVATE, false);
    }

    /**
     * 返回指定的SPCache（Mode默认为MODE_PRIVATE）
     *
     * @param name    存储文件名称
     * @param encrypt 是否需要加密存储
     */
    public SPCache spCache(String name, Boolean encrypt) {
        return spCache(name, Context.MODE_PRIVATE, encrypt);
    }

    /**
     * 返回指定的SPCache，Mode为指定的类型
     *
     * @param name    存储文件名称
     * @param mode    存储模式（默认MODE_PRIVATE）
     * @param encrypt 是否需要加密存储
     */
    public SPCache spCache(String name, int mode, Boolean encrypt) {
        SPCache spCache = mSPCacheMap.get(name + mode);
        if (spCache == null) {
            if (encrypt) {
                spCache = new SPCache(new SecuritySharedPreference(mContext, name, mode));
            } else {
                spCache = new SPCache(mContext.getSharedPreferences(name, mode));
            }
            mSPCacheMap.put(name + mode, spCache);
        }
        return spCache;
    }

    //==============================================================================================
    //ASimpleCache
    //==============================================================================================

    /**
     * 返回ACache实例
     *
     * @param fileName 缓存文件名
     * @return ACache
     */
    public ACache aCache(String fileName) {
        return ACache.get(mContext, fileName);
    }

    /**
     * 返回ACache实例
     *
     * @param fileName 缓存文件名
     * @param maxSize  缓存内存限制
     * @param maxCount 缓存数量限制
     * @return ACache
     */
    public ACache aCache(String fileName, long maxSize, int maxCount) {
        File cacheDir = new File(mContext.getCacheDir(), fileName);
        return ACache.get(cacheDir, maxSize, maxCount);
    }

}
