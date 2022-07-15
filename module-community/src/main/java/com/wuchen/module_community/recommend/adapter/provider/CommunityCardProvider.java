package com.wuchen.module_community.recommend.adapter.provider;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wuchen.library_public.contract.BaseCustomViewModel;
import com.wuchen.module_community.databinding.CommunityItemCommunityViewBinding;
import com.wuchen.module_community.recommend.bean.viewmodel.CloumnsCardViewModel;
import com.wuchen.module_community.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-17
 */
public class CommunityCardProvider extends BaseItemProvider<BaseCustomViewModel> {
    @Override
    public int getItemViewType() {
        return IRecommendItemType.COMMUNITY_CARD_VIEW;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.getView(R.layout.community_item_community_view));
    }

    @Override
    public int getLayoutId() {
        return R.layout.community_item_community_view;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable BaseCustomViewModel baseCustomViewModel) {
          if (baseCustomViewModel == null){
              return;
          }
        CommunityItemCommunityViewBinding binding = baseViewHolder.getBinding();
          if (binding != null){
              ViewGroup.LayoutParams layoutParams = binding.ivCoverBg.getLayoutParams();
              CloumnsCardViewModel viewModel = (CloumnsCardViewModel) baseCustomViewModel;
              int itemWidth = ScreenUtils.getScreenWidth() / 2;
             float scale = (itemWidth+0f)/viewModel.imgWidth;
             layoutParams.height = (int) (viewModel.imgHeight*scale);
             binding.ivCoverBg.setLayoutParams(layoutParams);
              binding.setViewModel(viewModel);
              binding.executePendingBindings();
          }
    }
}
