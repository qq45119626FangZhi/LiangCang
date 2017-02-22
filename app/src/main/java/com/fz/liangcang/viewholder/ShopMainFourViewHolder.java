package com.fz.liangcang.viewholder;

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
 * @FileName:com.fz.liangcang.viewholder.ShopMainFourViewHolder.java
 * @author：方志
 * @date: 2017-01-12 15:46
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class ShopMainFourViewHolder extends RecyclerView.ViewHolder {

    private final Context mContext;
    @BindView(R.id.one_imageView)
    ImageView oneImageView;
    @BindView(R.id.two_imageView)
    ImageView twoImageView;
    @BindView(R.id.three_imageView)
    ImageView threeImageView;
    @BindView(R.id.four_imageView)
    ImageView fourImageView;


    public ShopMainFourViewHolder(Context mContext, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mContext = mContext;
    }

    public void setData(ShopMainBean.DataBean.ItemsBean.ListBeanX listBeanX) {
        Glide.with(mContext).load(listBeanX.getOne().getPic_url()).into(oneImageView);
        Glide.with(mContext).load(listBeanX.getTwo().getPic_url()).into(twoImageView);
        Glide.with(mContext).load(listBeanX.getThree().getPic_url()).into(threeImageView);
        Glide.with(mContext).load(listBeanX.getFour().getPic_url()).into(fourImageView);
    }


}
