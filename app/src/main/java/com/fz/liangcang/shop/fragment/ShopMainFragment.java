package com.fz.liangcang.shop.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.fz.liangcang.R;
import com.fz.liangcang.shop.adapter.ShopMainAdapter;
import com.fz.liangcang.base.BaseFragment;
import com.fz.liangcang.shop.bean.ShopMainBean;
import com.fz.liangcang.interfaces.OnGetNetListener;
import com.fz.liangcang.utils.GetNet;
import com.fz.liangcang.utils.MyConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @FileName:com.fz.liangcang.fragment.MainFragment.java
 * @author：方志
 * @date: 2017-01-11 19:34
 * @QQ：459119626
 * @微信：15549433151
 * @function <商品--首页>
 */

public class ShopMainFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<ShopMainBean.DataBean.ItemsBean.ListBeanX> datas;
    private ShopMainAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shop_main, null);
        ButterKnife.bind(this, view);
        return view;
    }


    /**
     * 加载数据
     */
    @Override
    public void initData() {
        /**
         * 联网请求数据
         */
        GetNet.getNetData(MyConstants.SHOP_MAIN, null, new OnGetNetListener() {
            @Override
            public void onSuccess(String response) {
                analysisData(response);
            }

            @Override
            public void onError(Exception e) {

                Log.e("TAG", "RecommendPager联网失败" + e.getMessage());
            }
        });


    }

    /**
     * 解析数据，设置数据
     *
     * @param response
     */
    private void analysisData(String response) {
        ShopMainBean shopMainBean = JSON.parseObject(response, ShopMainBean.class);
        datas = shopMainBean.getData().getItems().getList();
//        Toast.makeText(mContext, datas.get(0).getHome_type()+"", Toast.LENGTH_SHORT).show();
        if (datas != null && datas.size() > 0) {

            //设置布局管理器
            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1;
                }
            });
            //设置布局管理器
            recyclerView.setLayoutManager(manager);
            //设置适配器
            adapter = new ShopMainAdapter(mContext,datas);
            recyclerView.setAdapter(adapter);

        }
    }


}
