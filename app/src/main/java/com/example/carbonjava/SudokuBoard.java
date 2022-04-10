package com.example.carbonjava;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SudokuBoard extends View {
    private final int boardColor;
    private final int cellFillColor;
    private final int cellsHightlightColor;
    private int cellSize;

    private final int letter;
    private final int letterPlaced;
    private final int letterPlacedMistake;

    private final Paint letterColorPaint = new Paint();
    private final Paint letterColorPlacedPaint = new Paint();
    private final Rect letterPaintBounds = new Rect();
    private final Paint paint = new Paint();
    private final Paint cellFillColorPaint = new Paint();
    private final Paint cellsHightlightColorPaint = new Paint();

    private SudokuLogic sudocku = new SudokuLogic();

    public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard,0,0);
    try{
        this.boardColor = a.getInteger(R.styleable.SudokuBoard_boardSColor,0);
        this.cellFillColor = a.getInteger(R.styleable.SudokuBoard_cellFillColor,0);
        this.cellsHightlightColor = a.getInteger(R.styleable.SudokuBoard_cellsHighlightColor,0);
        this.letter = a.getInteger(R.styleable.SudokuBoard_letterColor,0);
        this.letterPlaced = a.getInteger(R.styleable.SudokuBoard_letterColorPlaced,0);
        this.letterPlacedMistake = a.getInteger(R.styleable.SudokuBoard_letterColorPlacedMistakes,0);

    }finally {
        a.recycle();
    }
    }
    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(16);
        paint.setColor(boardColor);
        paint.setAntiAlias(true);

        cellFillColorPaint.setStyle(Paint.Style.FILL);
        cellFillColorPaint.setColor(cellFillColor);
        cellFillColorPaint.setStrokeWidth(16);

        cellsHightlightColorPaint.setStyle(Paint.Style.FILL);
        cellsHightlightColorPaint.setColor(cellsHightlightColor);
        cellsHightlightColorPaint.setStrokeWidth(16);

        letterColorPaint.setAntiAlias(true);
        letterColorPaint.setStyle(Paint.Style.FILL);
        letterColorPaint.setColor(letter);

        colorCell(canvas,sudocku.getSelectedRow(),sudocku.getSelectedColumn());
        canvas.drawRect(0,0,getWidth(),getWidth(),paint);
        drawBoard(canvas);
        drawNumbers(canvas);
    }
    private void drawNumbers(Canvas canvas){
        letterColorPaint.setTextSize(cellSize);
        for(int r =0;r<9;r++){
            for(int c =0;c<9;c++){
                if(sudocku.getSudoku()[r][c]!=0){
                    String text =Integer.toString(sudocku.getSudoku()[r][c]);
                    float width, height;

                    letterColorPaint.getTextBounds(text,0,text.length(),letterPaintBounds);//to get width of text
                    width = letterColorPaint.measureText(text);
                    height = letterColorPaint.measureText(text);

                    canvas.drawText(text,(c*cellSize)+((cellSize-width)/2),(r*cellSize+cellSize)-((cellSize-height)/2),letterColorPaint);

                }
            }
        }
        letterColorPaint.setColor(letterPlaced);

        for(ArrayList<Object> letter : sudocku.getEmptyBoxIndex()){
            int r = (int)letter.get(0);
            int c = (int)letter.get(1);
            String text =Integer.toString(sudocku.getSudoku()[r][c]);
            float width, height;

            letterColorPaint.getTextBounds(text,0,text.length(),letterPaintBounds);
            width = letterColorPaint.measureText(text);
            height = letterColorPaint.measureText(text);

            canvas.drawText(text,(c*cellSize)+((cellSize-width)/2),(r*cellSize+cellSize)-((cellSize-height)/2),letterColorPaint);

        }
        letterColorPaint.setColor(letterPlacedMistake);

        for(ArrayList<Object> letter : sudocku.getEmptyBoxIndexMistakes()){
            int r = (int)letter.get(0);
            int c = (int)letter.get(1);
            String text =Integer.toString(sudocku.getSudoku()[r][c]);
            float width, height;

            letterColorPaint.getTextBounds(text,0,text.length(),letterPaintBounds);
            width = letterColorPaint.measureText(text);
            height = letterColorPaint.measureText(text);

            canvas.drawText(text,(c*cellSize)+((cellSize-width)/2),(r*cellSize+cellSize)-((cellSize-height)/2),letterColorPaint);
        }
    }

    @Override
    protected void onMeasure(int width,int height){
        super.onMeasure(width,height);

        int dimension = Math.min(this.getMeasuredWidth(),this.getMeasuredHeight());
        this.cellSize = dimension / 9;

        setMeasuredDimension(dimension,dimension);
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isValid;

        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            sudocku.setSelectedRow((int)Math.ceil(y/cellSize));
            sudocku.setSelectedColumn((int)Math.ceil(x/cellSize));
            isValid = true;
        }
        else{
            isValid = false;
        }
        return isValid;
    }

    private void colorCell(Canvas canvas,int r,int c){
        if(sudocku.getSelectedColumn() != -1 && sudocku.getSelectedRow() != -1){
            canvas.drawRect((c-1)*cellSize,0,c*cellSize,cellSize*9,cellsHightlightColorPaint);

            canvas.drawRect(0,(r-1)*cellSize,cellSize*9,r*cellSize,cellsHightlightColorPaint);
            canvas.drawRect((c-1)*cellSize,(r-1)*cellSize,c*cellSize,r*cellSize,cellsHightlightColorPaint);
        }
        invalidate();
    }

    private void drawBoard(Canvas canvas){
        for(int c =0;c<10;c++){ //colum
            if(c%3 == 0){
                drawThickLine();
            }else{
                drawThinLine();
            }
            canvas.drawLine(cellSize*c,0,cellSize*c,getWidth(),paint);
        }
        for(int r =0;r<10;r++){ // row
            if(r%3 == 0){
                drawThickLine();
            }else{
                drawThinLine();
            }
            canvas.drawLine(0,cellSize*r,getWidth(),cellSize*r,paint);
        }
    }
    private void drawThinLine(){
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(boardColor);
    }

    private void drawThickLine(){
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(boardColor);
    }
    public SudokuLogic getSudocku(){
        return this.sudocku;
    }
}
