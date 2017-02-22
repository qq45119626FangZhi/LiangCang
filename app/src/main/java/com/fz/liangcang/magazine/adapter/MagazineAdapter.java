package com.fz.liangcang.magazine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fz.liangcang.R;
import com.fz.liangcang.magazine.bean.MagazineItemBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.liangcang.magazine.adapter.MagazineAdapter.java
 * @author：方志
 * @date: 2017-01-13 20:00
 * @QQ：459119626
 * @微信：15549433151
 * @function <杂志的适配器>
 */

public class MagazineAdapter extends BaseAdapter {


    private final Context mContext;
    private final List<List<MagazineItemBean>> datas;
    private final LayoutInflater inflater;
    private final List<String> keysArr;

    public MagazineAdapter(Context mContext, List<String> keysArr, List<List<MagazineItemBean>> datas) {
        this.mContext = mContext;
        this.keysArr = keysArr;
        this.datas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_magazine, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置标题日期
        if (position == 0) {
            viewHolder.tvMagazineTitle.setVisibility(View.GONE);
        } else {
            viewHolder.tvMagazineTitle.setVisibility(View.VISIBLE);
        }
        //日期字符串截取，及设置
        String date = keysArr.get(position).toString();
        String substring = date.substring(5, date.length());
        viewHolder.tvMagazineTitle.setText("— " + substring + " —");

        //根据位置得到item数据
        List<MagazineItemBean> magazineItemBeen = datas.get(position);
        //动态设置图片及图片描述
        viewHolder.llMagazine.removeAllViews();
        for(int i = 0; i < magazineItemBeen.size(); i++) {
            View view = View.inflate(mContext,R.layout.item_magazine_image,null);
            ImageView ivMagazineImage = (ImageView) view.findViewById(R.id.iv_magazine_image);
            TextView tvMagazineTop = (TextView) view.findViewById(R.id.tv_magazine_top);
            TextView tvMagazineBottom = (TextView) view.findViewById(R.id.tv_magazine_bottom);

            //设置数据
            Glide.with(mContext).load(magazineItemBeen.get(i).getCover_img_new()).into(ivMagazineImage);
            tvMagazineTop.setText(magazineItemBeen.get(i).getTopic_name());
            tvMagazineBottom.setText(magazineItemBeen.get(i).getCat_name());
            viewHolder.llMagazine.addView(view);
        }


        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_magazine_title)
        TextView tvMagazineTitle;
        @BindView(R.id.ll_magazine)
        LinearLayout llMagazine;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
