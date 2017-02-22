package com.fz.liangcang.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fz.liangcang.R;
import com.fz.liangcang.shop.bean.ShopMainBean;
import com.fz.liangcang.shop.viewholder.ShopMainBannerViewHolder;
import com.fz.liangcang.shop.viewholder.ShopMainFourViewHolder;
import com.fz.liangcang.shop.viewholder.ShopMainOneViewHolder;
import com.fz.liangcang.shop.viewholder.ShopMainTwoViewHolder;

import java.util.List;

/**
 * @FileName:com.fz.liangcang.adapter.ShopMainAdapter.java
 * @author：方志
 * @date: 2017-01-12 14:03
 * @QQ：459119626
 * @微信：15549433151
 * @function <商品--首页分类型适配器>
 */

public class ShopMainAdapter extends RecyclerView.Adapter {

    private final List<ShopMainBean.DataBean.ItemsBean.ListBeanX> datas;
    private final Context mContext;

    //定义4中类型
    private final static int BANNER_TYPE = 0;//广告条
    private final static int FOUR_TYPE = 1;//4张图片的布局
    private final static int ONE_TYPE = 2;//1张图片布局
    private final static int TWO_TYPE = 3;//2张图片布局
    private final LayoutInflater inflater;

    public ShopMainAdapter(Context mContext, List<ShopMainBean.DataBean.ItemsBean.ListBeanX> datas) {
        this.mContext = mContext;
        this.datas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        int CURRENT_TYPE = -1;
        //根据位置获取type
        int home_type = datas.get(position).getHome_type();
        if (6 == home_type) {
            CURRENT_TYPE = BANNER_TYPE;
        } else if (4 == home_type) {
            CURRENT_TYPE = FOUR_TYPE;
        } else if (1 == home_type) {
            CURRENT_TYPE = ONE_TYPE;
        } else if (2 == home_type) {
            CURRENT_TYPE = TWO_TYPE;
        }
        return CURRENT_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER_TYPE) {
            return new ShopMainBannerViewHolder(mContext, inflater.inflate(R.layout.adapter_banner_shop_main, parent, false));
        } else if (viewType == FOUR_TYPE) {
            return new ShopMainFourViewHolder(mContext, inflater.inflate(R.layout.adapter_four_shop_main, parent, false));
        } else if (viewType == ONE_TYPE) {
            return new ShopMainOneViewHolder(mContext, inflater.inflate(R.layout.adapter_one_shop_main, parent, false));
        } else if (viewType == TWO_TYPE) {
            return new ShopMainTwoViewHolder(mContext, inflater.inflate(R.layout.adapter_two_shop_main, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER_TYPE:
                ShopMainBannerViewHolder bannerHolder = (ShopMainBannerViewHolder) holder;
                bannerHolder.setData(datas.get(position));
                break;
            case FOUR_TYPE:
                ShopMainFourViewHolder fourHolder = (ShopMainFourViewHolder) holder;
                fourHolder.setData(datas.get(position));
                break;
            case ONE_TYPE:
                ShopMainOneViewHolder oneHolder = (ShopMainOneViewHolder) holder;
                oneHolder.setData(datas.get(position));
                break;
            case TWO_TYPE:
                ShopMainTwoViewHolder twoHolder = (ShopMainTwoViewHolder) holder;
                twoHolder.setData(datas.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }


}
