package com.example.binarysearchvisual;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class Animations {
    private final Context context;
    private final AnimationSet animationSet = new AnimationSet(true);
    private Animation floatings;
    private Animation slidings;


    public Animations(Context context) {
        this.context = context;
    }

    public AnimationSet getliftUpandRightToLeftAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        floatings = AnimationUtils.loadAnimation(context, R.anim.lift_up);
        slidings = AnimationUtils.loadAnimation(context, R.anim.from_right_to_left);

        animationSet.addAnimation(floatings);
        animationSet.addAnimation(slidings);

        return animationSet;
    }

    public AnimationSet getLiftDownAndLeftToRightAnimation() {
        // we are setting more than one animation so, for resisting ambiguity between animations
        // we have to make different animationSet in between functions.
        AnimationSet animationSet = new AnimationSet(true);
        floatings = AnimationUtils.loadAnimation(context, R.anim.lift_down);
        slidings = AnimationUtils.loadAnimation(context, R.anim.from_left_to_right);

        animationSet.addAnimation(floatings);
        animationSet.addAnimation(slidings);
        return animationSet;
    }

    public AnimationSet getLiftUpFromDownToUP(){
        AnimationSet animationSet = new AnimationSet(true);

        floatings = AnimationUtils.loadAnimation(context , R.anim.lift_up);
        slidings = AnimationUtils.loadAnimation(context , R.anim.from_down_to_up);
        animationSet.addAnimation(floatings);
        animationSet.addAnimation(slidings);
        return animationSet;
    }

    public AnimationSet getLiftDownFromUpToDown(){
        AnimationSet animationSet = new AnimationSet(true);

        floatings = AnimationUtils.loadAnimation(context , R.anim.lift_down);
        slidings = AnimationUtils.loadAnimation(context , R.anim.from_up_to_down);
        animationSet.addAnimation(floatings);
        animationSet.addAnimation(slidings);
        return animationSet;
    }

    public Animation getSingleAnimation(int animationResId){
        Animation animation = AnimationUtils.loadAnimation(context , animationResId);
        return animation;
    }

    public AnimationSet getLiftDownAnimation(){
        AnimationSet animationSet = new AnimationSet(true);

        floatings = AnimationUtils.loadAnimation(context , R.anim.lift_down);
        animationSet.addAnimation(animationSet);
        return animationSet;
    }

    public void startAnimation(TextView explanationText,Button first , Button second , AnimationSet animationSet , AnimationSet animationSet1){
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                int firstNum = Integer.parseInt(first.getText().toString());
                int secondNum = Integer.parseInt(second.getText().toString());
                explanationText.setText(String.valueOf(firstNum) + " > "+String.valueOf(secondNum)+"\nswapping required.");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                String text = first.getText().toString();
//                System.out.println(text + " "+ second.getText().toString());
                first.setText(second.getText().toString());
                second.setText(text);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        first.startAnimation(animationSet);
        second.startAnimation(animationSet1);
    }
}
