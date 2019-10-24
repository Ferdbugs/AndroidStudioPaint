package com.example.fingerpainter;

import androidx.appcompat.app.AppCompatActivity;
import yuku.ambilwarna.AmbilWarnaDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

public class ColorChooseView extends AppCompatActivity {

    int ColorCode=MainActivity.defaultColour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_choose_view);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
    }
    public void GoBack(View View){
        finish();
    }
    public void ColorPick(View View){
        openDialog(false);
    }
    private void openDialog (boolean supportAlpha){
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, ColorCode, supportAlpha, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                finish();
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                ColorCode = color;
                Intent ReturnColor = new Intent(ColorChooseView.this,MainActivity.class);
                ReturnColor.putExtra("ColorCode", ColorCode);
                setResult(RESULT_OK,ReturnColor);
                finish();
            }
        });
        dialog.show();
    }

}
