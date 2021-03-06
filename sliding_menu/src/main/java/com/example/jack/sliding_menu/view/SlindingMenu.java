package com.example.jack.sliding_menu.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

import junit.framework.TestResult;

/**
 * Created by 72408 on 2016/12/23.
 */

public class SlindingMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mScreenWidth;
    private int mMenuWidth;

    private boolean isOpen;

    //dp
    private int mMenuRightPadding = 50 ;

    private boolean once  = false ;


    /**
     *未使用自定义组件时使用
     * @param context
     * @param attrs
     */
    public SlindingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;


       //dp转化为PX
       mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP , 50 ,context.getResources().getDisplayMetrics());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        if (!once){

            mWapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            mContent = (ViewGroup) mWapper.getChildAt(1);

            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 设置偏移量 ， 将MENU隐藏
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        super.onLayout(changed, l, t, r, b);

        if (changed){
            this.scrollTo(mMenuWidth , 0);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
                int scroIIX =getScrollX();

                if (scroIIX > mMenuWidth /2){
                    this.smoothScrollTo(mMenuWidth , 0 );
                    isOpen  =false;
                }else
                {
                    this.smoothScrollTo(0 , 0);
                    isOpen = true;
                }
                return true;

        }


        return super.onTouchEvent(ev);

    }
    public void opmenMenu(){
        if (isOpen)return;

        this.smoothScrollTo( 0 , 0);
        isOpen = true;
    }
    public void closeMenu(){
        if (!isOpen)return;
        this.smoothScrollTo(mMenuWidth , 0 );
        isOpen = false;
    }

    public void toggle(){

        if (isOpen)closeMenu();
        else opmenMenu();
    }

    /**
     * 滚动发生时调用
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        super.onScrollChanged(l, t, oldl, oldt);
//        float scale =l * 1.0f / mMenuWidth ;//l ~ 0
//        //调用属性动画设置TranslationX
//        ViewHelper.setTranslationX(mMenu  , mMenuWidth*scale);




    }
}
