package com.example.jack.wuziqi.drawerlayout;
import android.os.Bundle;
import android.support.annotation.Nullable;
import  android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jack.wuziqi.R;

/**
 * Created by 72408 on 2016/12/15.
 */

public class ContenFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_content ,null);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        String text = getArguments().getString("text");
        tv.setText(text);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
