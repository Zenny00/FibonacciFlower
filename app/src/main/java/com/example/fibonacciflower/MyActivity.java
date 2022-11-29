package com.example.fibonacciflower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MyActivity extends AppCompatActivity {
    //Private data members to hold view components
    private ArrayList<ImageView> allPetals;
    private LayoutInflater layoutInflater;

    private Button pinkBtn;
    private Button goldBtn;
    private Button clearBtn;
    private RelativeLayout relativeLayout;

    //Model
    private Flower myFlower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Create new flower
        myFlower = new Flower();
        //Create new array list to hold flower petals
        allPetals = new ArrayList<ImageView>();

        //Setup generation
        initialize();

        //Create the layout inflator
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Setup references to view components
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout1);
        pinkBtn = (Button) findViewById(R.id.button1);
        goldBtn = (Button) findViewById(R.id.button2);
        clearBtn = (Button) findViewById(R.id.button3);

        //Setup click listeners
        pinkBtn.setOnClickListener(addPetal);
        goldBtn.setOnClickListener(addPetal);
        clearBtn.setOnClickListener(clearPetals);

        //Setup coordinates
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        myFlower.set_xCenter(metrics.widthPixels / 3); //Note: Dividing by 3 cause of a weird bug on my phone
        myFlower.set_yCenter(metrics.heightPixels / 2);
    }

    public void initialize()
    {
        //Setup first petal
        myFlower.setRotate(0);
        myFlower.setScaleX((float) 0.3);
        myFlower.setScaleY((float) 0.3);
        myFlower.setDegenerate((float) 1.001);
        myFlower.initializeAngle();
    }

    //Click listeners
    //Add petal listener
    private View.OnClickListener addPetal = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ImageView petal;

            //Down cast view to button
            String buttonText = ((Button) view).getText().toString();

            //Add the correct petal to the layout
            if (buttonText.equals("Add Pink"))
                petal = (ImageView) layoutInflater.inflate(R.layout.petal_pink, null);
            else
                petal = (ImageView) layoutInflater.inflate(R.layout.petal_gold, null);

            //Setup visuals
            petal.setX(myFlower.get_xCenter());
            petal.setY(myFlower.get_yCenter());
            petal.setPivotY(0);
            petal.setPivotX(100);
            petal.setScaleX(myFlower.getScaleX());
            petal.setScaleY(myFlower.getScaleY());
            petal.setRotation(myFlower.getRotate());

            //Add petal to view and array list
            relativeLayout.addView(petal, 0);
            allPetals.add(petal);

            //Update the model
            myFlower.updatePetalValues();
        }
    };

    //Clear listener, removes all petals from the view
    private View.OnClickListener clearPetals = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Iterate through the list of petals and remove each one from the view
            for (int i = 0; i < allPetals.size(); i++)
            {
                ImageView petal = allPetals.get(i);
                relativeLayout.removeView(petal);
            }

            //Clear array list and reset
            allPetals.clear();
            initialize();
        }
    };
}