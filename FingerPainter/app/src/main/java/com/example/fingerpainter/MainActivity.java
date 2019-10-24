package com.example.fingerpainter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    //public static int Size = 0;
    private static final int ColorRequest = 1;
    private static final int BrushRequest = 2;
    public static int defaultSize = 0;
    public static String defaultShape = "";
    public static int defaultColour = Color.BLACK;
    private  FingerPainterView FingerPainter;
    private int Colour;
    private int Size;
    private String Shape;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FingerPainter = findViewById(R.id.FingerPainter);
        Button ColourChoose = findViewById(R.id.ColourChoose);
        ColourChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColor();
            }
        });
        Button ShapeSelect = findViewById(R.id.Shape);
        ShapeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShape();
            }
        });
        Intent intent =getIntent();
        FingerPainter.load(intent.getData());
        if(savedInstanceState!= null){
            Colour = savedInstanceState.getInt("ColorCode");
            FingerPainter.setColour(Colour);
            Size = savedInstanceState.getInt("Size");
            FingerPainter.setBrushWidth(Size);
            Shape = savedInstanceState.getString("Shape");
            FingerPainter.setBrush(Paint.Cap.valueOf(Shape));
        }
    }
    public void openColor(){
        Intent intent = new Intent(this, ColorChooseView.class);
        startActivityForResult(intent,ColorRequest);
    }
    public void openShape(){
        Intent intent = new Intent(this, ShapeSelect.class);
        startActivityForResult(intent,BrushRequest);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == ColorRequest) &&
                (resultCode == RESULT_OK)) {
            FingerPainterView FingerPainter = findViewById(R.id.FingerPainter);
            if (data!=null) {
                Colour = data.getIntExtra("ColorCode", Color.BLACK);
                FingerPainter.setColour(Colour);
                defaultColour = Colour;
            }
        }
        else if ((requestCode == BrushRequest) &&
                (resultCode == RESULT_OK)) {
            FingerPainterView FingerPainter = findViewById(R.id.FingerPainter);
            if (data!=null) {
                Size = data.getIntExtra("Size", 25);
                //Log.d("size",Float.toString(Size));
                FingerPainter.setBrushWidth(Size);
                defaultSize = Size;
                Shape = data.getStringExtra("Shape");
                if(Shape!=null){
                    FingerPainter.setBrush(Paint.Cap.valueOf(Shape));
                    defaultShape = Shape;
                }
            }
        }
    }
    public void Refresh(View View){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("ColorCode", Colour);
        outState.putInt("Size", Size);
        outState.putString("Shape", Shape);
    }
}
