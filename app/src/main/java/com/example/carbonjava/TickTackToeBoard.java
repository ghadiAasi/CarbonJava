package com.example.carbonjava;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.jar.Attributes;

public class TickTackToeBoard extends View {
    private int xColor;
    private int yColor;
    private Canvas canva;
    private final int boardColor;
    private final int winningLineColor;
    private int cellSize = getWidth()/3;//the width of the cardview
    private final Paint paint = new Paint();
    private TicTacToeBrains game;
    private Context context;

    public TickTackToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        this.context = context;
        game = new TicTacToeBrains();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.TickTackToeBoard, 0, 0);
        try{
            boardColor = a.getInteger(R.styleable.TickTackToeBoard_boardColor, 0);
            xColor = a.getInteger(R.styleable.TickTackToeBoard_boardColor, 0);
            yColor = a.getInteger(R.styleable.TickTackToeBoard_boardColor, 0);
            winningLineColor = a.getInteger(R.styleable.TickTackToeBoard_boardColor, 0);
        }finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dimension = Math.min(getMeasuredWidth(),getMeasuredHeight());
        cellSize= dimension/3;
        setMeasuredDimension(dimension,dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);
    }

    private void drawMarkers(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(game.getBoard()[i][j] != 0){
                    if(game.getBoard()[i][j] == 1)

                if(game.getBoard()[i][j] == 2)
                    drawO(canvas,i,j);
                }
            }
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x= event.getX();
        float y= event.getY();
        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){
            int row = (int) (Math.ceil(y/cellSize));
            int col = (int) (Math.ceil(x/cellSize));

            if(game.UpdateGame(row,col)) {
                invalidate();
            }
            return true;
        }else{
            Log.d("TICK", MotionEvent.ACTION_DOWN+" Event");
        }
        return false;
    }
    private void drawGameBoard(Canvas canvas){
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);

        for(int i=1;i<3;i++){
            canvas.drawLine(cellSize*i,0,cellSize*i,canvas.getWidth(),paint);
        }
        for(int r=1;r<3;r++){
            canvas.drawLine(0,cellSize*r,canvas.getWidth(),cellSize*r,paint);
        }
        this.canva=canvas;
        drawX(canvas,1,1);

    }
    public void drawX(Canvas canvas,int row,int cal){
        paint.setColor(xColor);

        canvas.drawLine((float)((cal+1)*cellSize- cellSize*0.2),(float)(row*cellSize+ cellSize*0.2),(float)(cal*cellSize+ cellSize*0.2),(float)((row+1)*cellSize- cellSize*0.2),paint);
        canvas.drawLine((float)(cal*cellSize+ cellSize*0.2),(float)(row*cellSize+ cellSize*0.2),(float)((cal+1)*cellSize- cellSize*0.2),(float)((row+1)*cellSize- cellSize*0.2),paint);


    }
    public void drawO(Canvas canvas,int row,int cal){
        paint.setColor(yColor);
        canvas.drawOval((float)(cal*cellSize + cellSize*0.2),(float)((row*cellSize)+ cellSize*0.2),(float)((cal*cellSize +cellSize)-cellSize*0.2),(float)((row*cellSize +cellSize)-cellSize*0.2),paint);
    }

}
