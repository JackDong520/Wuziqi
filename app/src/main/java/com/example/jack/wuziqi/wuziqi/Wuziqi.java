package com.example.jack.wuziqi.wuziqi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.jack.wuziqi.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 72408 on 2016/12/10.
 */

public class Wuziqi extends View {

    private int mPanelWidth;
    private float mLineHeight;
    private int MAX_LINE = 10;
    private int MAX_COUNT_IN_LINE = 5;

    private Paint mPaint = new Paint();

    private Bitmap mWhitePiece;
    private Bitmap mBlackPiece;

    private float ratioPieceOfLineHeight = 3 * 1.0f / 4;

    //白棋先手，当前轮到白棋
    private boolean ismWhtie = true;
    private ArrayList<Point> mWhiteArray = new ArrayList<Point>();
    private ArrayList<Point> mBlackArray = new ArrayList<Point>();

    private boolean isGameOver;
    private boolean isWhiteWinner;

    private boolean isYour = true ;



    public Wuziqi(Context context, AttributeSet attrs) {
        super(context, attrs);

//        setBackgroundColor(0x44ff0000);

        //初始化Paint
        init();
    }

    private void init() {
        mPaint.setColor(0x88000000);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mWhitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w2);
        mBlackPiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize, heightSize);

        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }

        setMeasuredDimension(width, width);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mPanelWidth = w;
        mLineHeight = mPanelWidth * 1.0f / MAX_LINE;

        int pieceWidth = (int) (mLineHeight * ratioPieceOfLineHeight);
        mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece, pieceWidth, pieceWidth, false);
        mBlackPiece = Bitmap.createScaledBitmap(mBlackPiece, pieceWidth, pieceWidth, false);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (isGameOver) return false;

        if (!isYour)return false;


        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {

            int x = (int) event.getX();
            int y = (int) event.getY();

            Point point = getValidPoint(x, y);

            if (mWhiteArray.contains(point) || mBlackArray.contains(point)) {
                return false;
            }

            if (ismWhtie) {
                mWhiteArray.add(point);
            } else {
                mBlackArray.add(point);
            }
            isYour = !isYour;

            invalidate();
        }

        return true;
    }

    private Point getValidPoint(int x, int y) {
        return new Point((int) (x / mLineHeight), (int) (y / mLineHeight));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBoard(canvas);

        drawPieces(canvas);

        checkGameOver();
    }

    private void getSocket() {






    }

    private void checkGameOver() {

        boolean whiteWin = checkFiveInLine(mWhiteArray);
        boolean blackWin = checkFiveInLine(mBlackArray);

        if (whiteWin || blackWin) {

            isGameOver = true;
            isWhiteWinner = whiteWin;

            String text = isWhiteWinner ? "白棋胜利" : "黑棋胜利";

            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

        }

    }

    private boolean checkFiveInLine(List<Point> points) {

        for (Point p : points) {

            int x = p.x;
            int y = p.y;

            boolean win = checkHorizontal(x, y, points);
            if (win) return true;

            win = checkVertical(x, y, points);
            if (win) return true;

            win = checkLeftDiagonal(x, y, points);
            if (win) return true;

            win = checkRightDiagonal(x, y, points);
            if (win) return true;

        }

        return false;

    }

    //检查横向
    private boolean checkHorizontal(int x, int y, List<Point> points) {

        int count = 1;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x - i, y))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_IN_LINE) return true;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x + i, y))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_IN_LINE) return true;

        return false;

    }

    //检查纵向
    private boolean checkVertical(int x, int y, List<Point> points) {

        int count = 1;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x, y + i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_IN_LINE) return true;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x, y - i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_IN_LINE) return true;

        return false;

    }

    //检查左斜
    private boolean checkLeftDiagonal(int x, int y, List<Point> points) {

        int count = 1;

        //上
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x + i, y - i))) {
                count++;
            } else {
                break;
            }
        }

        if (count == MAX_COUNT_IN_LINE) return true;

        //下
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x - i, y + i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_IN_LINE) return true;

        return false;

    }

    //检查右斜
    private boolean checkRightDiagonal(int x, int y, List<Point> points) {

        int count = 1;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x - i, y - i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_IN_LINE) return true;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x + i, y + i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_IN_LINE) return true;

        return false;

    }

    private void drawPieces(Canvas canvas) {

        for (int i = 0, size = mWhiteArray.size(); i < size; i++) {

            Point whitePoint = mWhiteArray.get(i);

            canvas.drawBitmap(mWhitePiece,
                    ((whitePoint.x + (1 - ratioPieceOfLineHeight) / 2) * mLineHeight),
                    ((whitePoint.y + (1 - ratioPieceOfLineHeight) / 2) * mLineHeight), null);

        }

        for (int i = 0, size = mBlackArray.size(); i < size; i++) {

            Point blackPoint = mBlackArray.get(i);

            canvas.drawBitmap(mBlackPiece,
                    ((blackPoint.x + (1 - ratioPieceOfLineHeight) / 2) * mLineHeight),
                    ((blackPoint.y + (1 - ratioPieceOfLineHeight) / 2) * mLineHeight), null);

        }

    }

    private void drawBoard(Canvas canvas) {

        int w = mPanelWidth;
        float lineHeight = mLineHeight;

        for (int i = 0; i < MAX_LINE; i++) {

            int startX = (int) (lineHeight / 2);
            int endX = (int) (w - lineHeight / 2);
            int y = (int) ((0.5 + i) * lineHeight);

            canvas.drawLine(startX, y, endX, y, mPaint);
            canvas.drawLine(y, startX, y, endX, mPaint);

        }
    }


}