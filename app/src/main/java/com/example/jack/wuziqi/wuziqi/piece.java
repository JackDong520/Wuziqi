package com.example.jack.wuziqi.wuziqi;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Parcel;

/**
 * Created by 72408 on 2016/12/10.
 */

public class piece extends Point {

    protected Bitmap mPiecePicture;

    public void setmmPiecePicture(Bitmap mPiecePicture) {
        this.mPiecePicture = mPiecePicture;
    }

    public Bitmap getmmPiecePicture() {
        return mPiecePicture;
    }
}

