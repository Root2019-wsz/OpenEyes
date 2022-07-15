package com.wuchen.library_base.activity;

/**
 * 应用模块：activity
 * 界面UI显示切换
 */
public interface IBaseView {
    /**
     * 显示内容
     */
    void showContent();

    /**
     * 显示加载提示
     */
    void showLoading();

    /**
     * 显示空页面
     */
    void showEmpty();

    /**
     * 刷新失败
     */
    void showFailure(String message);

}
