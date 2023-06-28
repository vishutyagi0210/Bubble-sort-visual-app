package com.example.binarysearchvisual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.TimeUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button allInOneButton , binarySearchButton;
    private CardView timerCardView;
    private BinarySearchAlgo binary;
    private ButtonFunctions buttonFunctions;

    private TextView explanationText , timer;

    private Animations animations;

    private boolean animationSkip = false;

    private AnimationInflator animationInflator;
    private EditText enterYourNumber;

    private int [] ids = new int[]
            {R.id.buton0 , R.id.buton1 , R.id.buton2 , R.id.buton3 , R.id.buton4, R.id.buton5, R.id.buton6, R.id.buton7, R.id.buton8,
            R.id.buton9, R.id.buton10, R.id.buton11, R.id.buton12, R.id.buton13, R.id.buton14, R.id.buton15, R.id.buton16,
            R.id.buton17, R.id.buton18, R.id.buton19, R.id.buton20, R.id.buton21, R.id.buton22, R.id.buton23, R.id.buton24};

    private int[] transposeIds = new int[]
            {R.id.buton0 , R.id.buton5 , R.id.buton10 , R.id.buton15 , R.id.buton20 ,
            R.id.buton1 , R.id.buton6 , R.id.buton11 , R.id.buton16 , R.id.buton21,
            R.id.buton2 , R.id.buton7 , R.id.buton12 , R.id.buton17 , R.id.buton22 ,
            R.id.buton3 , R.id.buton8 , R.id.buton13 , R.id.buton18 , R.id.buton23 ,
            R.id.buton4 , R.id.buton9 , R.id.buton14 , R.id.buton19 , R.id.buton24};


    //few variables to make our single button multi tasking.

    boolean isAutoFillClicked = true;
    boolean isStartAnimationClicked = false;
    boolean isSkipAnimationClicked = false;
    Drawable circleDrawable;
    boolean nextButtonFirstClick = true;
    boolean nextButtonSecondClick = false;
    boolean nextButtonThirdClick = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setIds();

        allInOneButton.setOnClickListener(new AllInOneButtonClicks());

        binarySearchButton.setOnClickListener(new BinarySearchButton());

    }

    public void setIds(){
        allInOneButton = findViewById(R.id.ButtonFillAuto);
        binary = new BinarySearchAlgo();
        buttonFunctions = new ButtonFunctions(MainActivity.this);
        explanationText = findViewById(R.id.ExplanationTextView);
        animations = new Animations(MainActivity.this);
        binarySearchButton = findViewById(R.id.nextButton);
        animationInflator = new AnimationInflator(MainActivity.this);
        timer = findViewById(R.id.timerText);
        timerCardView = findViewById(R.id.timerTextCardView);
        enterYourNumber = findViewById(R.id.enterYourNumberEditText);
    }

       private class AllInOneButtonClicks implements View.OnClickListener {

           @Override
           public void onClick(View v) {
               if (isAutoFillClicked) {
                   buttonFunctions.fillRandomNumberIntoButtons();
                   explanationText.setText("All the values set\nNow, we are applying bubble sort for sorting this\n2D matrix");
                   allInOneButton.setText("Start Animation");
                   isStartAnimationClicked = true;
                   isAutoFillClicked = false;

               } else if (isStartAnimationClicked) {
                   explanationText.setText("first we are sorting every row \none by one.");
                   allInOneButton.setText("Skip Animation");
                   Handler handler = new Handler();
                   timer.setVisibility(View.VISIBLE);
                   timerCardView.setVisibility(View.VISIBLE);
                   timer.setText("07 : 06");
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           startTimer(handler, 7, 6);
                       }
                   }, 1000);

                   new Handler().postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           explanationText.setText("Starting.....");
                           Queue<HashMap<Integer, Integer>> rowMap = binary.getQueueRow();
                           int variable = 0;
                           int duration = 16000;
                           int changeRow = 0;
                           allInOneButton.startAnimation(animations.getSingleAnimation(R.anim.lift_up_for_all_in_one_button));
                           animationInflator.inflateAnimation(changeRow, duration, variable, rowMap, ids, animations.getLiftDownAndLeftToRightAnimation(), animations.getliftUpandRightToLeftAnimation());
                       }
                   }, 1000);

                   new Handler().postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           Queue<HashMap<Integer, Integer>> colMap = binary.getQueueCol();
                           int variable = 0;
                           int duration = 16000;
                           int changeRow = 0;
                           allInOneButton.startAnimation(animations.getSingleAnimation(R.anim.lift_up_for_all_in_one_button));
                           animationInflator.inflateAnimation(changeRow, duration, variable, colMap, transposeIds, animations.getLiftDownFromUpToDown(), animations.getLiftUpFromDownToUP());
                       }
                   }, 220000);
                   isStartAnimationClicked = false;
                   isSkipAnimationClicked = true;
               } else if (isSkipAnimationClicked) {
                   animationSkip = true;
                   allInOneButton.clearAnimation();
                   explanationText.setText(R.string.afterAnimationSkip);
                   allInOneButton.setVisibility(View.INVISIBLE);
                   binarySearchButton.setVisibility(View.VISIBLE);
                   enterYourNumber.setVisibility(View.VISIBLE);
                   isSkipAnimationClicked = false;

               }
               else{
                   animationSkip = true;
                   allInOneButton.clearAnimation();
                   allInOneButton.setVisibility(View.INVISIBLE);
               }
           }
       }

       private class BinarySearchButton implements View.OnClickListener{

           @Override
           public void onClick(View v) {
               if(nextButtonFirstClick){
                   if(enterYourNumber.getText().toString().equals("")){
                       Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                   }
                   else{
                       enterYourNumber.setVisibility(View.INVISIBLE);
                       binarySearchButton.setText("Start Binary Search");
                       nextButtonFirstClick = false;
                       nextButtonSecondClick = true;
                   }
               }
               else if(nextButtonSecondClick){
                   binarySearchButton.setText("Start");
                   explanationText.setText("You enter "+enterYourNumber.getText().toString()+" For searching\nlet's search for it with binary search");
                   nextButtonSecondClick = false;
                   nextButtonThirdClick = true;
               }
               else if(nextButtonThirdClick){
                   explanationText.setText("Searching.....");
               }
           }
       }


    public void startTimer(Handler handler, int minutes, int seconds) {
        if ((minutes == 0 && seconds == 0) || getIsAnimationSkip()) {
            timer.setText("00 : 00");
            timerCardView.setVisibility(View.INVISIBLE);
            timer.setVisibility(View.INVISIBLE);
            return;
        } else {
            if (seconds < 10) {
                timer.setText("0" + minutes + " : 0" + seconds);
            } else if (minutes == 0 && seconds < 10) {
                timer.setTextColor(Color.RED);
                timer.setText("00 : " + "0" + seconds);
            } else {
                timer.setText("0" + minutes + " : " + seconds);
            }

            if (minutes > 0 && seconds <= 0) {
                minutes--;
                seconds += 60;
            }
            final int finalMinutes = minutes;
            final int finalSeconds = seconds;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startTimer(handler, finalMinutes, finalSeconds - 1);
                }
            }, 1000);
        }
    }

    public int[] getIds(){
        return ids;
    }
    public TextView getExplanationText(){
        return explanationText;
    }

    public boolean getIsAnimationSkip(){
        return animationSkip;
    }

    public BinarySearchAlgo getBinary(){
        return binary;
    }
}
