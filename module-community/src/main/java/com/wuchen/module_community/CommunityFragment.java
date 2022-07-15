package com.wuchen.module_community;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.google.android.material.tabs.TabLayout;
import com.wuchen.library_base.fragment.MvvmLazyFragment;
import com.wuchen.library_base.viewmodel.IMvvmBaseViewModel;
import com.wuchen.library_public.router.RouterFragmentPath;
import com.wuchen.module_community.adapter.CommunityFragmentPageAdapter;
import com.wuchen.module_community.attention.AttentionFragment;
import com.wuchen.module_community.databinding.CommunityFragmentCommunityBinding;
import com.wuchen.module_community.recommend.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用模块:
 * <p>
 * 类描述: 社区
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-16
 */

@Route(path = RouterFragmentPath.Community.PAGER_COMMUNITY)
public class CommunityFragment extends MvvmLazyFragment<CommunityFragmentCommunityBinding, IMvvmBaseViewModel> {

    private CommunityFragmentPageAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.community_fragment_community;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        initView();
        initData();
    }

    private void initView() {
        viewDataBinding.tabLayout.setupWithViewPager(viewDataBinding.vpHomeContent);
        viewDataBinding.vpHomeContent.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(viewDataBinding.tabLayout));
        viewDataBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewDataBinding.vpHomeContent.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        adapter = new CommunityFragmentPageAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        viewDataBinding.vpHomeContent.setAdapter(adapter);
    }

    private void initData() {
        List<Fragment> data = new ArrayList<>();
        data.add(RecommendFragment.newInstance());
        data.add(AttentionFragment.newInstance());
        adapter.setData(data);
    }



    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected IMvvmBaseViewModel getViewModel() {
        return null;
    }


    @Override
    protected void onRetryBtnClick() {

    }
}
