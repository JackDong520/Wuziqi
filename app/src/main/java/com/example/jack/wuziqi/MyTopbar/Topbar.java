package com.example.jack.wuziqi.MyTopbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jack.wuziqi.R;

import java.security.PublicKey;

/**
 * Created by 72408 on 2016/12/17.
 */

public class Topbar extends RelativeLayout {

    private Button leftButton , rightButton;
    private TextView tvTitle ;

    private int leftTextColor ;
    private Drawable leftBackgroud;
    private String leftText;

    private int rightTextColor ;
    private Drawable rightBackgroud;
    private String rightText;

    private float titletextSize;
    private int titleTextColor;
    private  String title;

    private topbarClickListener listener;

    private LayoutParams leftParams , rightParams , titleParams ;

    public interface topbarClickListener{
        public void leftClick();
        public void rightClick();

    }
    public void setOnTopbarClickListener(topbarClickListener listener){
        this.listener =listener;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Topbar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs , R.styleable.Topbar);

        leftTextColor =ta.getColor(R.styleable.Topbar_leftTextColor , 0 );
        leftBackgroud =ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftText = ta.getString(R.styleable.Topbar_leftText);

        rightTextColor =ta.getColor(R.styleable.Topbar_rightTextColor , 0 );
        rightBackgroud =ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightText = ta.getString(R.styleable.Topbar_rightText);

        titletextSize = ta.getDimension(R.styleable.Topbar_titleTextSize , 0);
        titleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor , 0);
        title = ta.getString(R.styleable.Topbar_title);

        ta.recycle();

        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackgroud);
        leftButton.setText(leftText);

        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackgroud);
        rightButton.setText(rightText);

        tvTitle.setTextSize(titletextSize);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);

        setBackgroundColor(0XFFF89563);

        leftParams = new LayoutParams( LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT , TRUE);

        addView(leftButton , leftParams);

        rightParams = new LayoutParams( LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT , TRUE);

        addView(rightButton , rightParams);


        titleParams = new LayoutParams( LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT , TRUE);

        addView(tvTitle , titleParams);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();

            }
        });
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               listener.rightClick();

            }
        });

    }
    public void setLeftIsvisable(boolean flag){
        if(flag){
            leftButton.setVisibility(View.VISIBLE);
        }else{
            leftButton.setVisibility(View.GONE);
        }

    }
    public void setrightIsvisable(boolean flag){
        if(flag){
            rightButton.setVisibility(View.VISIBLE);
        }else{
            rightButton.setVisibility(View.GONE);
        }

    }





}
















