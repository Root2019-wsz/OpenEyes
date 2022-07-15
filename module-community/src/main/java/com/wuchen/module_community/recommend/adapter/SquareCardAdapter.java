package com.wuchen.module_community.recommend.adapter;

import android.annotation.SuppressLint;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wuchen.module_community.R;
import com.wuchen.module_community.databinding.CommunityItemSquareItemCardViewBinding;
import com.wuchen.module_community.recommend.bean.SquareContentCard;


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
public class SquareCardAdapter extends BaseQuickAdapter<SquareContentCard, BaseViewHolder> {
    public SquareCardAdapter(int layoutResId) {
        super(layoutResId);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.getView(R.layout.community_item_square_card_view));
    }
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable SquareContentCard squareContentCard) {
          if (squareContentCard == null){
              return;
          }
        CommunityItemSquareItemCardViewBinding binding = baseViewHolder.getBinding();
          if (binding != null){
              binding.setViewModel(squareContentCard);
              binding.executePendingBindings();
          }
    }
}
