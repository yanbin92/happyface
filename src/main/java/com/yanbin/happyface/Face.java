package com.yanbin.happyface;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by yanbin on 2016/1/9.
 */
public class Face {

    Path mouthPath;
    Paint facePaint;
      Paint mPaint;

      float radius;
      float adjust;

      float eyeLeftX, eyeTopY, eyeBottomY, eyeRightx;
      RectF eyeLeftRectF;
      RectF eyeRightRectF;



    public Face(float radius) {
        this.radius=radius;

        facePaint=new Paint();
        facePaint.setColor(Color.YELLOW);
        facePaint.setAntiAlias(true);
        //设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        facePaint.setDither(true);
        ////设置结合处的样子，Miter:结合处为锐角， Round:结合处为圆弧：BEVEL：结合处为直线。
        facePaint.setStrokeJoin(Paint.Join.ROUND);
        // 画笔笔刷类型 如影响画笔开始末端
        facePaint.setStrokeCap(Paint.Cap.ROUND);
        //设置绘制路径的效果，如点画线等
//        1）CornerPathEffect  可以使用圆角来代替尖锐的角从而对基本图形的形状尖锐的边角进行平滑。
//        2）DashPathEffect  可以使用DashPathEffect来创建一个虚线的轮廓(短横线/小圆点)，而不是使用实线。你还可以指定任意的虚/实线段的重复模式。
//        3）DiscretePathEffect 与DashPathEffect相似，但是添加了随机性。
//          当绘制它的时候，需要指定每一段的长度和与原始路径的偏离度。
//        4）PathDashPathEffect  这种效果可以定义一个新的形状(路径)并将其用作原始路径的轮廓标记。
//        下面的效果可以在一个Paint中组合使用多个Path Effect。
//        1）SumPathEffect 顺序地在一条路径中添加两种效果，这样每一种效果都可以应用到原始路径中，而且两种结果可以结合起来。
//        2）ComposePathEffect  将两种效果组合起来应用，先使用第一种效果，然后在这种效果的基础上应用第二种效果。
        facePaint.setPathEffect(new CornerPathEffect(10));
        // 在图形下面设置阴影层，产生阴影效果，radius为阴影的角度，dx和dy为阴影在x轴和y轴上的距离，color为阴影的颜色
        facePaint.setShadowLayer(4,2,2,0x80000000);

        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        //设置画笔的样式，为FILL，FILL_OR_STROKE，或STROKE
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setPathEffect(new CornerPathEffect(10));
        mPaint.setStrokeWidth(radius / 14.0f);


        adjust=radius/3.2f;


        //left eye
        eyeLeftX=radius-(radius*0.43f);
        eyeTopY = radius-(radius*0.5f);
        eyeRightx = eyeLeftX+ (radius*0.3f);
        eyeBottomY = eyeTopY + (radius*0.4f);

        eyeLeftRectF = new RectF(eyeLeftX+adjust,eyeTopY+adjust,eyeRightx+adjust,eyeBottomY+adjust);

        // Right Eye
        eyeLeftX = eyeRightx + (radius*0.3f);
        eyeRightx = eyeLeftX + (radius*0.3f);

        eyeRightRectF = new RectF(eyeLeftX+adjust,eyeTopY+adjust,eyeRightx+adjust,eyeBottomY+adjust);

        // Smiley Mouth
        float mouthLeftX = radius-(radius*0.5f);
        float mouthRightX = mouthLeftX+ radius;
        float mouthTopY = radius - (radius*0.2f);
        float mouthBottomY = mouthTopY + (radius*0.5f);

        mouthRectF = new RectF(mouthLeftX+adjust,mouthTopY+adjust,mouthRightX+adjust,mouthBottomY+adjust);

        mouthPath=new Path();
        mouthPath.arcTo(mouthRectF,30,120,true);
    }

    RectF mouthRectF;
    public void draw(Canvas canvas) {
        //1.draw face
        //被二层覆盖的区域没必要绘制
        //我们可以通过canvas.clipRect()来帮助系统识别那些可见的区域。
        // 这个方法可以指定一块矩形区域，只有在这个区域内才会被绘制，其他的区域会被忽视。
        // 这个API可以很好的帮助那些有多组重叠组件的自定义View来控制显示的区域。
        // 同时clipRect方法还可以帮助节约CPU与GPU资源
//        canvas.clipPath(mouthPath);
//        canvas.clipRect(eyeLeftRectF);
//        canvas.clipRect(eyeRightRectF);
        canvas.drawCircle(radius+adjust,radius+adjust,radius,facePaint);

        // 2. draw mouth
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mouthPath, mPaint);

        // 3. draw eyes
        mPaint.setStyle(Paint.Style.FILL);
        //绘制椭圆
        canvas.drawArc(eyeLeftRectF,0,360,true,mPaint);
        canvas.drawArc(eyeRightRectF,0,360,true,mPaint);

    }
}
