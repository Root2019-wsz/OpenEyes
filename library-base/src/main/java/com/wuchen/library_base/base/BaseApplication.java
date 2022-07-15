package com.wuchen.library_base.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * @NonNull :参数非空
 * @Nullable :参数可为空
 */

public class BaseApplication extends Application {

    private static BaseApplication sInstance;
    private static boolean sDebug;

    @Override
    public void onCreate() {
        super.onCreate();
        setApplicaton(this);
    }


    /** 当宿主没有继承自该Application时,可以使用set方法进行初始化baseApplication */
    private void setApplicaton(@NonNull BaseApplication application) {
        sInstance = application;
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                AppManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                AppManager.getInstance().removeActivity(activity);
            }
        });
    }

    public static BaseApplication getInstance(){
        if (sInstance == null){
            throw new NullPointerException(
                    "please inherit BaseApplication or call setApplication.");
        }
        return sInstance;
    }

    public void setsDebug(boolean isDebug){
        sDebug = isDebug;
    }

    public boolean issDebug(){
        return sDebug;
    }

    /**
     * 获取进程名
     *
     * @param context
     * @return
     */
    public static String getCurrentProcessName(Context context) {
        ActivityManager am =
                (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps =
                am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo proInfo : runningApps) {
            if (proInfo.pid == android.os.Process.myPid()) {
                if (proInfo.processName != null) {
                    return proInfo.processName;
                }
            }
        }
        return null;
    }
}
