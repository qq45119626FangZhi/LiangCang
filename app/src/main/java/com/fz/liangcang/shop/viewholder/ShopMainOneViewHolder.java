package com.fz.liangcang.shop.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fz.liangcang.R;
import com.fz.liangcang.shop.bean.ShopMainBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.liangcang.viewholder.ShopMainOneViewHolder.java
 * @author：方志
 * @date: 2017-01-12 16:43
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class ShopMainOneViewHolder extends RecyclerView.ViewHolder {


    private final Context mContext;
    @BindView(R.id.image_view)
    ImageView imageView;

    public ShopMainOneViewHolder(Context mContext, View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.mContext = mContext;
    }

    public void setData(ShopMainBean.DataBean.ItemsBean.ListBeanX listBeanX) {
        Glide.with(mContext).load(listBeanX.getOne().getPic_url()).into(imageView);
    }
}
