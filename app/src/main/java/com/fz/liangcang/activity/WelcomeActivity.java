package com.fz.liangcang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.fz.liangcang.R;
import com.fz.liangcang.utils.CacheUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 欢迎页面
 */
public class WelcomeActivity extends AppCompatActivity {


    private static final int MESSAGE_SUCCESS = 1;
    @BindView(R.id.iv_welcome)
    ImageView ivWelcome;
    private long duration;//gif动画时长

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case  1:
                    //是否进入主页面，读取缓存的向导页面记录
                    boolean isStartMain = CacheUtils.getBoolean(WelcomeActivity.this, GuideActivity.START_MAIN);
                    if(isStartMain) {
                        //进入主页面
                        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    }else {
                        //进入引导页
                        startActivity(new Intent(WelcomeActivity.this,GuideActivity.class));
                    }
                    //结束当前页面
                    finish();
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        Glide.with(this)
                .load(R.drawable.loading_start)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                .listener(new RequestListener<Integer, GifDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<GifDrawable> target, boolean isFirstRsource) {
                        Log.e("TAG", "WelcomeActivity : " + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Integer model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        // 计算动画时长
                        GifDrawable drawable = (GifDrawable) resource;
                        GifDecoder decoder = drawable.getDecoder();
                        for (int i = 0; i < drawable.getFrameCount(); i++) {
                            duration += decoder.getDelay(i);
                        }
                        //发送延时消息，通知动画结束
                        handler.sendEmptyMessageDelayed(MESSAGE_SUCCESS,
                                duration);
                        return false;
                    }
                })
                .into(ivWelcome); //仅仅加载一次gif动画

    }

}
