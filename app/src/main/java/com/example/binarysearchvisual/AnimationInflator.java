package com.example.binarysearchvisual;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class AnimationInflator {
    private MainActivity mainActivity;
    private Button firstbtn , secondbtn;

    private Animations animations;

    private BinarySearchAlgo binary = new BinarySearchAlgo();
    private ButtonFunctions buttonFunctions;
    public AnimationInflator(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        animations = new Animations(mainActivity);
        buttonFunctions = new ButtonFunctions(mainActivity);
    }

    public void inflateAnimation(int changeRow , int duration , int variable , Queue<HashMap<Integer , Integer>> Map , int[]ids , AnimationSet animationSet , AnimationSet animationSet1){
        if(Map.isEmpty() || mainActivity.getIsAnimationSkip()){
            if(mainActivity.getIsAnimationSkip()){
                buttonFunctions.fillMatrixAfterSkipButtonClicked(buttonFunctions.ROW_AND_COL);
            }
            else{
                buttonFunctions.fillMatrixAfterSkipButtonClicked(buttonFunctions.ROW);//after completing the whole animation we are using this.. for just cross check.
            }
            return;
        }


        else{
            //calling function which manage the row.
            inflateSpecificRowAndColAnimation(changeRow , duration , variable , Map , ids , animationSet , animationSet1);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //calling this function recursively
                    inflateAnimation(changeRow+5 , duration , variable, Map , ids , animationSet , animationSet1);
                }
            },43000);
        }
    }

    public void inflateSpecificRowAndColAnimation(int changeRow ,int duration , int variable , Queue<HashMap<Integer , Integer>> Map , int[] ids , AnimationSet animationSet , AnimationSet animationSet1){
        //checking condition if user press animationSkip button.
        if(variable < 4 && (!mainActivity.getIsAnimationSkip())){
            //calling function which manages a single element one by one.
            InflateSpecificItemAnimation(changeRow , Map.peek() , ids,animationSet , animationSet1);
            Map.poll();
            //before
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    inflateSpecificRowAndColAnimation(changeRow,duration - 4000, variable+1 , Map , ids , animationSet , animationSet1);
                }
            },duration);
        }


        else{
            if(mainActivity.getIsAnimationSkip()){
                buttonFunctions.fillMatrixAfterSkipButtonClicked(buttonFunctions.ROW_AND_COL);
            }
            else{
                firstbtn.setTextColor(Color.BLACK);
                firstbtn.setBackgroundColor(Color.GREEN);
                mainActivity.getExplanationText().setText(firstbtn.getText().toString() + "\nis on its right place.");
            }
            return;
        }


    }

    public void InflateSpecificItemAnimation(int changeRow , HashMap<Integer,Integer> Map , int[] ids , AnimationSet animationSet , AnimationSet animationSet1){
        if(Map.isEmpty() || mainActivity.getIsAnimationSkip()){
            if(mainActivity.getIsAnimationSkip()){
                buttonFunctions.fillMatrixAfterSkipButtonClicked(buttonFunctions.ROW_AND_COL);
            }
            else{
                secondbtn.setBackgroundColor(Color.GREEN);
                secondbtn.setTextColor(Color.BLACK);
            }
            return;
        }


        Map.Entry<Integer , Integer> firstElement = Map.entrySet().iterator().next();
        int key = firstElement.getKey();
        int value = firstElement.getValue();
        firstbtn = mainActivity.findViewById(ids[key+changeRow]);
        secondbtn = mainActivity.findViewById(ids[key+1+changeRow]);

        if(value == 1){
            animations.startAnimation(mainActivity.getExplanationText(), firstbtn , secondbtn , animationSet, animationSet1);
        }
        else{
            mainActivity.getExplanationText().setText(firstbtn.getText().toString() + " <= "+ secondbtn.getText().toString()+ "\nno swapping required.");
            firstbtn.startAnimation(animations.getSingleAnimation(R.anim.lift_up));
            secondbtn.startAnimation(animations.getSingleAnimation(R.anim.lift_up));
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Map.remove(key , value);
                InflateSpecificItemAnimation(changeRow,Map , ids , animationSet , animationSet1);
            }
        },3500);
    }

}
