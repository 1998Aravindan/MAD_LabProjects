package com.example.aravindan.touch;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchScreen extends View {
    Paint paint = new Paint();
    Path path = new Path();
    public TouchScreen(Context context, AttributeSet attributeset){
        super(context,attributeset);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.STROKE);
    }
    protected void onDraw(Canvas canvas){
        canvas.drawPath(path,paint);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
        paint.setTextSize(50);
        paint.setColor(Color.BLACK);
        canvas.drawText("Rectangle",520,250,paint);
        canvas.drawRect(500,200,1150,700,paint);
        canvas.drawText("Circle",182,164,paint);
        canvas.drawCircle(160,150,170,paint);
        canvas.drawText("Oval",142,1522,paint);
        canvas.drawOval(140,720,350,1470,paint);
    }
    public boolean onTouchEvent(MotionEvent event) {
        float X = event.getX();
        float Y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(X, Y);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(X, Y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
}
