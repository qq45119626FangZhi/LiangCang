package com.fz.liangcang.shop.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fz.liangcang.R;
import com.fz.liangcang.shop.bean.ShopMainBean;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.AccordionTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.liangcang.viewholder.ShopMainBannerViewHolder.java
 * @author：方志
 * @date: 2017-01-12 14:33
 * @QQ：459119626
 * @微信：15549433151
 * @function <商品-主页-广告条的viewholder>
 */

public class ShopMainBannerViewHolder extends RecyclerView.ViewHolder {

    private final Context mContext;
    @BindView(R.id.banner)
    Banner banner;
    private List<ShopMainBean.DataBean.ItemsBean.ListBeanX.ListBean> datas;

    public ShopMainBannerViewHolder(Context mContext, View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.mContext = mContext;
    }

    public void setData(ShopMainBean.DataBean.ItemsBean.ListBeanX listBeanX) {
        datas = listBeanX.getList();
        //获取图片地址的集合
        List imageUrl = new ArrayList();
        for (int i = 0; i < datas.size(); i++) {
            imageUrl.add(datas.get(i).getPic_url());
        }

        //加载数据
        banner.setImages(imageUrl)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                })
                .setBannerAnimation(AccordionTransformer.class)//设置手风琴拉伸动画
                .start();

        //点击事件
        banner.setOnBannerClickListener(new OnBannerClickListener() {

            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(mContext, "banner:"+position, Toast.LENGTH_SHORT).show();
            }
        });



    }


}
