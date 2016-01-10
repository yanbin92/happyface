package com.yanbin.happyface;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yanbin on 2016/1/9.
 */
public class FaceView extends View {

    private static final String TAG = "FaceView"    ;
    private final float radius;
    private final Face face;

    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        attrs.getAttributeBooleanValue()
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FaceView, 0, 0);
        try {
            radius = typedArray.getDimension(R.styleable.FaceView_radius, 20.0f);
        }finally {
            typedArray.recycle();
        }
        face=new Face(radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        face.draw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int desiredWidth= (int) (radius*2+(int)Math.ceil(radius/1.7f));
        int desiredHeight= (int) (radius*2+(int)Math.ceil(radius/1.7f));

        Log.i(TAG,"desiredWidth:"+desiredWidth+" desiredHeight:"+desiredHeight);

        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        int width;
        int height;
        //Measure Width
        if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }else if(widthMode==MeasureSpec.AT_MOST){
            //wrap_content
            ////Can't be bigger than ...
            width=Math.min(desiredWidth,widthSize);

            Log.d("Width AT_MOST", "width: "+width);
        }else{
            //Be whatever you want
            width=desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }
        //MUST CALL THIS
        setMeasuredDimension(width,height);
    }

    public float getRadius() {
        return radius;
    }

}
