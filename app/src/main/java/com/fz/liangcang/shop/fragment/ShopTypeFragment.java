package com.fz.liangcang.shop.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.fz.liangcang.R;
import com.fz.liangcang.base.BaseFragment;
import com.fz.liangcang.shop.bean.ShopTypeBean;
import com.fz.liangcang.interfaces.OnGetNetListener;
import com.fz.liangcang.utils.GetNet;
import com.fz.liangcang.utils.MyConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.liangcang.fragment.ShopTypeFragment.java
 * @author：方志
 * @date: 2017-01-11 19:40
 * @QQ：459119626
 * @微信：15549433151
 * @function <商品--分类页面>
 */
public class ShopTypeFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ShopTypeAdapter adapter;
    private List<ShopTypeBean.DataBean.ItemsBean> datas;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shop_type, null);
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
        GetNet.getNetData(MyConstants.SHOP_TYPE, null, new OnGetNetListener() {
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
        ShopTypeBean typeBean = JSON.parseObject(response, ShopTypeBean.class);
        datas = typeBean.getData().getItems();
//        Toast.makeText(mContext, datas.get(0).getCat_name(), Toast.LENGTH_SHORT).show();
        if (datas != null && datas.size() > 0) {
            //设置布局管理器
            recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
            //设置适配器
            adapter = new ShopTypeAdapter();
            recyclerView.setAdapter(adapter);

        }
    }

    class ShopTypeAdapter extends RecyclerView.Adapter<ShopTypeAdapter.ViewHolder> {

        private final LayoutInflater inflater;

        public ShopTypeAdapter() {
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.item_shop_type, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String url = datas.get(position).getNew_cover_img();
            Glide.with(mContext).load(url).into(holder.imageView);

        }

        @Override
        public int getItemCount() {
            return datas == null ? 0 : datas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.image_view)
            ImageView imageView;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, getLayoutPosition() + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

}
