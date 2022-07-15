package com.wuchen.library_base.activity;

public interface IBasePagingView extends IBaseView{
    /**
     * 加载更多失败
     * */
    void onLoadMoreFailure(String message);

    /**
     * 没有更多了
     * */
    void onLoadMoreEmpty();
}
