package com.fz.liangcang.magazine;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fz.liangcang.R;
import com.fz.liangcang.base.BaseFragment;
import com.fz.liangcang.interfaces.OnGetNetListener;
import com.fz.liangcang.magazine.adapter.MagazineAdapter;
import com.fz.liangcang.magazine.bean.MagazineBean;
import com.fz.liangcang.magazine.bean.MagazineItemBean;
import com.fz.liangcang.utils.DensityUtil;
import com.fz.liangcang.utils.GetNet;
import com.fz.liangcang.utils.MyConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @FileName:com.fz.liangcang.fragment.MagazineFragment.java
 * @author：方志
 * @date: 2017-01-11 17:02
 * @QQ：459119626
 * @微信：15549433151
 * @function <杂志页面>
 */

public class MagazineFragment extends BaseFragment {


    @BindView(R.id.tv_magezine)
    TextView tvMagezine;
    @BindView(R.id.rl_mgz_title)
    RelativeLayout rlMgzTitle;
    @BindView(R.id.text_switcher)
    TextSwitcher textSwitcher;
    @BindView(R.id.list_view)
    ListView listView;

    private List<String> keysArr;
    private List<List<MagazineItemBean>> datas;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_magazine, null);
        ButterKnife.bind(this, view);
        //引用textSwitcher，设置参数
        initTextSwitcher();
        return view;
    }

    @Override
    public void initData() {
        getDatafromNet();
    }


    /**
     * 联网请求数据
     */
    private void getDatafromNet() {
        GetNet.getNetData(MyConstants.MAGAZINE, null, new OnGetNetListener() {
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
     * 使用fastjson手动解析数据
     *
     * @param json
     */
    private void analysisData(String json) {
        MagazineBean magazineBean = new MagazineBean();
        JSONObject jsonObject = JSON.parseObject(json);

        String data = jsonObject.getString("data");
        JSONObject jsonObject1 = JSONObject.parseObject(data);
        boolean has_more = jsonObject1.getBoolean("has_more");
        magazineBean.setHas_more(has_more);

        Integer num_items = jsonObject1.getInteger("num_items");
        magazineBean.setNum_items(num_items);

        //key值解析
        String items = jsonObject1.getString("items");
        JSONObject jsonObject2 = JSONObject.parseObject(items);
        String keys = jsonObject2.getString("keys");
        //阿里fastjson
        keysArr = JSON.parseArray(keys, String.class);
        magazineBean.setKeys(keysArr);
        Log.e("TAG", "key:=====" + magazineBean.getKeys().get(0));

        /**
         * 各个itemlist的数据
         */
        String infos = jsonObject2.getString("infos");
        JSONObject jsonObject3 = JSONObject.parseObject(infos);
        datas = new ArrayList<>();
        for (int i = 0; i < keysArr.size(); i++) {
            //获取key值
            String string = jsonObject3.getString(keysArr.get(i));
            List<MagazineItemBean> magazineItem = JSON.parseArray(string, MagazineItemBean.class);
            datas.add(magazineItem);
        }
        magazineBean.setMagazineItemBeens(datas);
        Log.e("TAG", "手动解析数据===" + magazineBean.getKeys().size() + " =====" + magazineBean.getMagazineItemBeens().size());


        //设置适配器装载数据
        setDataofRecyclerView();

    }

    private void setDataofRecyclerView() {
        if (null != keysArr && keysArr.size() > 0 && null != datas && datas.size() > 0) {
            textSwitcher.setText(keysArr.get(0));

            listView.setAdapter(new MagazineAdapter(mContext, keysArr, datas));
        }

        //设置listView的滑动监听，改变TextSwitcher的值
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            int position;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(position != firstVisibleItem) {
                    textSwitcher.setText(keysArr.get(firstVisibleItem));
                    position = firstVisibleItem;
                }

            }
        });

    }

    /**
     * 设置TextSwitcher参数
     */
    private void initTextSwitcher() {
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(mContext);
                textView.setSingleLine();//单行显示
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setTextSize(DensityUtil.dp2px(mContext, 12));
                textView.setTextColor(Color.parseColor("#688EB6"));
                textView.setGravity(Gravity.CENTER);
                //设置布局
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2, -2);//包裹类型
                textView.setLayoutParams(lp);//关联布局
                return textView;
            }
        });
    }


    @OnClick(R.id.rl_mgz_title)
    public void onClick() {
        Toast.makeText(mContext, "杂志下拉页面", Toast.LENGTH_SHORT).show();
    }

}
