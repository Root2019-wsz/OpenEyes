package com.wuchen.module_community.recommend;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.wuchen.library_base.fragment.MvvmLazyFragment;
import com.wuchen.library_public.contract.BaseCustomViewModel;
import com.wuchen.library_public.recyclerview.RecyclerItemDecoration;
import com.wuchen.library_public.utils.DensityUtils;
import com.wuchen.module_community.databinding.CommunityFragmentRecommendBinding;
import com.wuchen.module_community.recommend.adapter.ProviderRecommendAdapter;
import com.wuchen.module_community.R;

import java.util.ArrayList;

/**
 * 应用模块:
 * <p>
 * 类描述: 社区-推荐
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-16
 */
public class RecommendFragment extends MvvmLazyFragment<CommunityFragmentRecommendBinding, RecommendViewModel>
        implements IRecommendView
{

    private ProviderRecommendAdapter adapter;

    public static RecommendFragment newInstance()
    {
        RecommendFragment fragment = new RecommendFragment();
        return fragment;
    }
    
    @Override
    public int getLayoutId()
    {
        return R.layout.community_fragment_recommend;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        initView();
    }

    private void initView() {
        adapter = new ProviderRecommendAdapter();
        viewDataBinding.rvDailyView.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        viewDataBinding.rvDailyView.setLayoutManager(layoutManager);
        viewDataBinding.rvDailyView.addItemDecoration(new RecyclerItemDecoration(0,0, DensityUtils.dp2px(getContext(),5),DensityUtils.dp2px(getContext(),15)));
        viewDataBinding.rvDailyView.setAdapter(adapter);
        viewDataBinding.refreshLayout
                .setRefreshHeader(new ClassicsHeader(getContext()));
        viewDataBinding.refreshLayout
                .setRefreshFooter(new ClassicsFooter(getContext()));
        viewDataBinding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            viewModel.tryRefresh();
        });
        viewDataBinding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            viewModel.loadMore();
        });
        setLoadSir(viewDataBinding.refreshLayout);
        showLoading();
        viewModel.initModel();
    }

    @Override
    public int getBindingVariable()
    {
        return 0;
    }
    
    @Override
    protected RecommendViewModel getViewModel()
    {
        return ViewModelProviders.of(this).get(RecommendViewModel.class);
    }
    
    @Override
    protected void onRetryBtnClick()
    {
        
    }
    
    @Override
    public void onLoadMoreFailure(String message)
    {
        viewDataBinding.refreshLayout.finishLoadMore(false);
    }
    
    @Override
    public void onLoadMoreEmpty()
    {
        viewDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void onDataLoadFinish(ArrayList<BaseCustomViewModel> viewModels, boolean isFirstPage) {
        if (isFirstPage){
            adapter.setNewData(viewModels);
            showContent();
            viewDataBinding.refreshLayout.finishRefresh(true);

        }else {
            adapter.addData(viewModels);
            showContent();
            viewDataBinding.refreshLayout.finishLoadMore(true);

        }

    }
}
