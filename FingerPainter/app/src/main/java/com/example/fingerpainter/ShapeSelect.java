package com.example.fingerpainter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import static android.graphics.Paint.Cap.ROUND;
import static android.graphics.Paint.Cap.SQUARE;

public class ShapeSelect extends AppCompatActivity {

    SeekBar brushSz;
    private String brush;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_select);
        SetDefault();
        Button Round = findViewById(R.id.Round);
        Round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brush = "ROUND";
                SendBrush();
            }
        });
        Button Square = findViewById(R.id.Square);
        Square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brush = "SQUARE";
                SendBrush();
            }
        });
    }

    public void SendBrush(){
        brushSz = findViewById(R.id.BrushWidth);
        int size= brushSz.getProgress();
        Intent ReturnBrush = new Intent(this,MainActivity.class);
        ReturnBrush.putExtra("Size", size);
        ReturnBrush.putExtra("Shape",brush);
        setResult(RESULT_OK,ReturnBrush);
    }
    public void Back(View View){
        SendBrush();
        finish();
    }
    private void SetDefault(){
        if(MainActivity.defaultSize!= 0){
            brushSz = findViewById(R.id.BrushWidth);
            brushSz.setProgress(MainActivity.defaultSize);
        }
        if(MainActivity.defaultShape!= ""){
            brush = MainActivity.defaultShape;
        }
    }
}
