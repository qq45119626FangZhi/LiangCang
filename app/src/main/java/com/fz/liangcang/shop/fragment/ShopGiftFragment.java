package com.fz.liangcang.shop.fragment;

import android.view.View;
import android.widget.TextView;

import com.fz.liangcang.R;
import com.fz.liangcang.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.liangcang.fragment.ShopGiftFragment.java
 * @author：方志
 * @date: 2017-01-11 19:42
 * @QQ：459119626
 * @微信：15549433151
 * @function <商品--礼物页面>
 */
public class ShopGiftFragment extends BaseFragment {
    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.test, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        tvTest.setText("我是商品--礼物页面");
    }
}
