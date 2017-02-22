package com.fz.liangcang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fz.liangcang.R;
import com.fz.liangcang.utils.CacheUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 引导页面
 */
public class GuideActivity extends AppCompatActivity {

    public static final String START_MAIN = "start_main";

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.bt_to_main)
    Button btToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);


        //获取图片地址的集合
        final List imageUrl = new ArrayList();
        imageUrl.add(R.drawable.feature1);
        imageUrl.add(R.drawable.feature2);
        imageUrl.add(R.drawable.feature3);
        imageUrl.add(R.drawable.feature4);
        imageUrl.add(R.drawable.feature5);

        //加载数据
        banner.setImages(imageUrl)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                })
                .start();

        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == imageUrl.size()) {
                    btToMain.setVisibility(View.VISIBLE);
                } else {
                    btToMain.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.bt_to_main)
    public void onClick() {
        //1.保存记录已经进入主页
        CacheUtils.putBoolean(this,START_MAIN,true);
        //2.进入主页
        startActivity(new Intent(this,MainActivity.class));
        //3.关闭引导页面
        finish();
    }
}
