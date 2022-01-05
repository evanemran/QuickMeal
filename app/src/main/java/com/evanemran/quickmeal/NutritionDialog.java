package com.evanemran.quickmeal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

public class NutritionDialog extends Dialog implements View.OnClickListener {

    public String id;
    public Dialog dialog;
    public ImageView nutrition_imageView;

    public NutritionDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.nutrition_dialog);
        nutrition_imageView = (ImageView) findViewById(R.id.imageView_nutrition);
        Picasso.get().load("https://api.spoonacular.com/recipes/641166/nutritionLabel.png");
        nutrition_imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
