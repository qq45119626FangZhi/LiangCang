package com.fz.liangcang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @FileName:com.fz.liangcang.utils.MyGridView.java
 * @author：方志
 * @date: 2017-01-12 10:32
 * @QQ：459119626
 * @微信：15549433151
 * @function <自定义gridview >
 */

public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 测量设置高度
     * @param widthSpec
     * @param heightSpec
     */
    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);
    }
}
