package com.fz.liangcang.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fz.liangcang.R;
import com.fz.liangcang.base.BaseFragment;
import com.fz.liangcang.daren.DaRenFragment;
import com.fz.liangcang.magazine.MagazineFragment;
import com.fz.liangcang.me.MeFragment;
import com.fz.liangcang.share.ShareFragment;
import com.fz.liangcang.shop.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_frame_layout)
    FrameLayout mainFrameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    //fragment集合
    private List<BaseFragment> fragments;
    //记录当前fragment角标
    private int position;
    //缓存的fragment
    private Fragment tempFragment;
    public FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //添加fragment
        initFragment();
        //设置RadioGroup的监听
        initListener();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ShopFragment());//商品
        fragments.add(new MagazineFragment());//杂志
        fragments.add(new DaRenFragment());//达人
        fragments.add(new ShareFragment());//分享
        fragments.add(new MeFragment());//个人

    }

    //监听radioButton
    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_shop://商品
                        position = 0;
                        break;
                    case R.id.rb_magazine://杂志
                        position = 1;
                        break;
                    case R.id.rb_daren://达人
                        position = 2;
                        break;
                    case R.id.rb_share://分享
                        position = 3;
                        break;
                    case R.id.rb_me://个人
                        position = 4;
                        break;
                }
                //切换到当前要显示的fragment
                switchFragment(getFragment(position));
            }
        });

        //默认选中商品页面
        rgMain.check(R.id.rb_shop);
    }

    /**
     * 获取当前的fragment
     *
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换fragment
     * @param currentFragemnt
     */
    private void switchFragment(Fragment currentFragemnt) {
        //判断缓存的fragmen 和当前的fragment，不一样
        if (tempFragment != currentFragemnt) {
            //Fragment管理
            ft = getSupportFragmentManager().beginTransaction();
            //当前的fragment没有添加
            if (!currentFragemnt.isAdded()) {
                //把上一个隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //添加当前的fragment,并提交
                ft.add(R.id.main_frame_layout, currentFragemnt).commit();
            } else {//当前的fragment已经添加过
                //把上一个隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //直接显示
                ft.show(currentFragemnt).commit();
            }
            //赋值
            tempFragment = currentFragemnt;
        }
    }

    /**
     * 消息处理器
     */
    public Handler handler = new Handler();

    private boolean flag = false;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //返回true不能退出
        if (position != 0) {
            position = 0;
            rgMain.check(R.id.rb_shop);
            return true;
        } else if (!flag) {
            flag = true;
            Toast.makeText(MainActivity.this, "再按一次退出界面", Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    flag = false;
                }
            }, 2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

    }



}
