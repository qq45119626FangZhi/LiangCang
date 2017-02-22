package com.fz.liangcang.shop.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fz.liangcang.R;
import com.fz.liangcang.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @FileName:com.fz.liangcang.fragment.ShopFragment.java
 * @author：方志
 * @date: 2017-01-11 16:58
 * @QQ：459119626
 * @微信：15549433151
 * @function <商品页面>
 */

public class ShopFragment extends BaseFragment {


    @BindView(R.id.iv_title_search)
    ImageView ivTitleSearch;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_title_shopping_cart)
    ImageView ivTitleShoppingCart;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private MyPagerAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shop, null);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        //设置适配器
        adapter = new MyPagerAdapter(getChildFragmentManager() );
        viewPager.setAdapter(adapter);
        //设置默认页--首页
        viewPager.setCurrentItem(2);
        //关联ViewPager
        tabLayout.setupWithViewPager(viewPager);


    }


    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.iv_title_search, R.id.iv_title_shopping_cart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_search:
                Toast.makeText(mContext, "进入搜索页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_title_shopping_cart:
                Toast.makeText(mContext, "进入购物车页面", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class MyPagerAdapter extends FragmentPagerAdapter{
        //tabLayout标题栏集合数据
        private String[] titles = {"分类","品牌","首页","专题","礼物"};
        //页面集合
        private List<BaseFragment> fragments;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            initFragment();
        }

        /**
         * 初始化集合数据
         */
        private void initFragment() {
            fragments = new ArrayList<>();
            fragments.add(new ShopTypeFragment());//商品--分类
            fragments.add(new ShopBrandFragment());//商品--品牌
            fragments.add(new ShopMainFragment());//商品--首页
            fragments.add(new ShopTopicFragment());//商品--专题
            fragments.add(new ShopGiftFragment());//商品--礼物

        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
