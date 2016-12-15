package com.example.jack.wuziqi.wuziqi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.jack.wuziqi.R;

/**
 * Created by 72408 on 2016/12/13.
 */
public class AnimActivity extends Activity {

    private Button button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animactivity);

        button = (Button) findViewById(R.id.anim_button_1);
        final Animation anim = AnimationUtils.loadAnimation(this , R.anim.grow_from_topright_to_bottomleft);
        anim.setFillAfter(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                button.startAnimation(anim);
            }
        });

    }
}
