package com.example.clockwidgetjava;

import android.appwidget.AppWidgetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorShape;

public class MainActivity extends AppCompatActivity {
    NumberPicker npFontSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        npFontSize = (NumberPicker) findViewById(R.id.npFontSize);
        npFontSize.setMinValue(8);
        npFontSize.setMaxValue(20);

        //Изменяем цвет шрифта виджета.
        findViewById(R.id.btColorChange).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createColorPickerDialog(1);
                }
            }
        );
    }
    private void createColorPickerDialog(Integer id) {
        ColorPickerDialog.newBuilder()
                .setColor(Color.RED)
                .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                .setAllowCustom(true)
                .setAllowPresets(true)
                .setColorShape(ColorShape.SQUARE)
                .setDialogId(id)
                .show(this);
    }

    @Override public void onColorSelected(int dialogId, int color) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        val remoteViews = RemoteViews(context.packageName, R.layout.new_app_widget)
        val thisWidget = ComponentName(context, NewAppWidget::class.java)
        //remoteViews.setTextViewText(R.id.appwidget_text, "myText" + System.currentTimeMillis())
        remoteViews.setInt(R.id.appwidget_text, "setTextColor", color)
        appWidgetManager.updateAppWidget(thisWidget, remoteViews)
    }
}