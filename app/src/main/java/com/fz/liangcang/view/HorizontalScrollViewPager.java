package com.fz.liangcang.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @FileName:com.fz.beijingnews.view.HorizontalScrollViewPager.java
 * @author：方志
 * @date: 2016-12-14 19:58
 * @QQ：459119626
 * @微信：15549433151
 * @function <自定义ViewPager，实现父类子类事件传递冲突>
 */

public class HorizontalScrollViewPager extends ViewPager {

    public HorizontalScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private float startX;
    private float startY;

    /**
     * 上下滑动
     * getParent().requestDisallowInterceptTouchEvent(false);
     * 水平方向滑动
     * 1.第0个位置，并且是从左到右滑动
     * getParent().requestDisallowInterceptTouchEvent(false);
     * 2.最后一个位置,并且是从右到左的滑动
     * getParent().requestDisallowInterceptTouchEvent(false);
     * 3.中间位置
     * getParent().requestDisallowInterceptTouchEvent(true);
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //把事件传递给HorizontalScrollViewPager类
        switch (ev.getAction()) {
            case  MotionEvent.ACTION_DOWN:
                //记录按下的坐标
                startX = ev.getX();
                startY = ev.getY();
                break;
            case  MotionEvent.ACTION_MOVE:
                float endX = ev.getX();
                float endY = ev.getY();
                //计算移动的距离
                float dx = Math.abs(endX - startX);
                float dy = Math.abs(endY - startY);

                //判断方向
                if(dx > dy) {//水平方向滑动
                    // 1.第0个位置，并且是从左到右滑动
                    if(getCurrentItem() == 0 && endX - startX >0) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }else if(getCurrentItem() == 0 && endX - startX < 0){
                        // 2.最后一个位置,并且是从右到左的滑动
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }else{
                        //3.中间位置,要求父类不拦截当前控件的事件
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }

                }else{//竖直方向滑动
                    getParent().requestDisallowInterceptTouchEvent(false);
                }

                break;
            case  MotionEvent.ACTION_UP:

                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
