package com.wuchen.module_main.application;

import com.blankj.utilcode.util.Utils;

import com.kingja.loadsir.core.LoadSir;

import com.orhanobut.logger.Logger;
import com.wuchen.library_base.base.BaseApplication;
import com.wuchen.library_base.loadsir.*;
import com.wuchen.library_network.EasyHttp;
import com.wuchen.library_network.cache.converter.GsonDiskConverter;
import com.wuchen.library_network.cache.model.CacheMode;
import com.wuchen.library_public.IModuleInit;
import com.wuchen.library_public.adapter.ScreenAutoAdapter;


/**
 * 应用模块: main
 * <p>
 * 类描述: main组件的业务初始化
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-26
 */
public class MainModuleInit implements IModuleInit
{
    @Override
    public boolean onInitAhead(BaseApplication application)
    {
        ScreenAutoAdapter.setup(application);
        EasyHttp.init(application);
        if (application.issDebug())
        {
            EasyHttp.getInstance().debug("easyhttp", true);
        }
        EasyHttp.getInstance()
            .setBaseUrl("http://baobab.kaiyanapp.com")
            .setReadTimeOut(15 * 1000)
            .setWriteTimeOut(15 * 1000)
            .setConnectTimeout(15 * 1000)
            .setRetryCount(3)
            .setCacheDiskConverter(new GsonDiskConverter())
            .setCacheMode(CacheMode.FIRSTREMOTE);
        LoadSir.beginBuilder()
            .addCallback(new ErrorCallback())
            .addCallback(new LoadingCallback())
            .addCallback(new EmptyCallback())
            .addCallback(new TimeoutCallback())
            .setDefaultCallback(LoadingCallback.class)
            .commit();
         Utils.init(application);
        Logger.i("main组件初始化完成 -- onInitAhead");
        return false;
    }
    
    @Override
    public boolean onInitLow(BaseApplication application)
    {
        return false;
    }
}
