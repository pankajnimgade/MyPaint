package irobot.test.assignment.paint.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import irobot.test.assignment.paint.models.MyLine;

/**
 * Created by Pankaj Nimgade on 6/13/2017.
 */

public class MyDrawView extends View {
    private Paint paint;
    private Path path;
    private List<MyLine> myLines = new ArrayList<>();
    float paintStrokeWidth = 15f;


    public MyDrawView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(paintStrokeWidth);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        for (MyLine myLine : myLines) {
            canvas.drawPath(myLine.getPath(), myLine.getPaint());
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xPosition = event.getX();
        float yPosition = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                MyLine myLine = new MyLine(path, paint);
                path.moveTo(xPosition, yPosition);
                myLines.add(myLine);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPosition, yPosition);
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(xPosition, yPosition);
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }


    public void setPaintColor(int paintColor) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(paintStrokeWidth);
        paint.setColor(paintColor);
    }


    public Paint getPaint() {
        return paint;
    }
}
