package com.wuchen.module_community.recommend.adapter;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.wuchen.library_public.contract.BaseCustomViewModel;
import com.wuchen.module_community.recommend.adapter.provider.CommunityCardProvider;
import com.wuchen.module_community.recommend.adapter.provider.IRecommendItemType;
import com.wuchen.module_community.recommend.adapter.provider.SquareCardProvider;
import com.wuchen.module_community.recommend.bean.HorizontalScrollCard;
import com.wuchen.module_community.recommend.bean.viewmodel.CloumnsCardViewModel;


import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-17
 */
public class ProviderRecommendAdapter
    extends BaseProviderMultiAdapter<BaseCustomViewModel>
{

    public ProviderRecommendAdapter()
    {
        super();
        addItemProvider(new SquareCardProvider());
        addItemProvider(new CommunityCardProvider());

        
    }
    
    @Override
    protected int getItemType(@NotNull List<? extends BaseCustomViewModel> data,
        int position)
    {
        if (data.get(position) instanceof HorizontalScrollCard)
        {

            return IRecommendItemType.SQUARE_CARD_VIEW;
        }
        else if (data.get(position) instanceof CloumnsCardViewModel)
        {

            return IRecommendItemType.COMMUNITY_CARD_VIEW;
        }
        return 0;
    }
}
