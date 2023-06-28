package com.example.binarysearchvisual;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class ButtonFunctions {

    private MainActivity mainActivity;

    private Button firstbtn;

    public final int ROW = 1;
    public final int ROW_AND_COL = 2;
    public int whichButtonId(int id){
        switch (id){
            case R.id.buton0:
                return 0;
            case R.id.buton1:
                return 1;
            case R.id.buton2:
                return 2;
            case R.id.buton3:
                return 3;
            case R.id.buton4:
                return 4;
            case R.id.buton5:
                return 5;
            case R.id.buton6:
                return 6;
            case R.id.buton7:
                return 7;
            case R.id.buton8:
                return 8;
            case R.id.buton9:
                return 9;
            case R.id.buton10:
                return 10;
            case R.id.buton11:
                return 11;
            case R.id.buton12:
                return 12;
            case R.id.buton13:
                return 13;
            case R.id.buton14:
                return 14;
            case R.id.buton15:
                return 15;
            case R.id.buton16:
                return 16;
            case R.id.buton17:
                return 17;
            case R.id.buton18:
                return 18;
            case R.id.buton19:
                return 19;
            case R.id.buton20:
                return 20;
            case R.id.buton21:
                return 21;
            case R.id.buton22:
                return 22;
            case R.id.buton23:
                return 23;
            case R.id.buton24:
                return 24;
            default:
                return -1;
        }
    }

    public void fillMatrixAfterSkipButtonClicked(int skipRowOrCol){
        int temp = 0;
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                firstbtn = mainActivity.findViewById(mainActivity.getIds()[temp]);
                temp++;
                if(skipRowOrCol == ROW)
                    firstbtn.setText(String.valueOf(mainActivity.getBinary().getItemAtIndexFromRowWiseSortedMatrix(i , j)));
                else
                    firstbtn.setText(String.valueOf(mainActivity.getBinary().getItemAtIndexFromOriginalMatrix(i , j)));
                firstbtn.setBackgroundColor(Color.BLUE);
                firstbtn.setTextColor(Color.WHITE);
            }
        }
    }

    public void fillRandomNumberIntoButtons(){
        for(int btnId: mainActivity.getIds()){
            Button btn = mainActivity.findViewById(btnId);
            int nextRandom = (new Random().nextInt(101))+100;
            //setting value into internal matrix... or logic matrix
            mainActivity.getBinary().setMatrix(btnId , nextRandom);
            btn.setText(String.valueOf(nextRandom));
            btn.setBackgroundColor(Color.BLUE);
            btn.setTextColor(Color.WHITE);
        }
        //sorting according to row wise
        for(int row = 0; row < 5; row++){
            mainActivity.getBinary().sortRowWise(row);
        }
//         sorting according to col wise
        for(int col = 0; col<5; col++){
            mainActivity.getBinary().sortColWise(col);
        }
    }

    public ButtonFunctions(){}

    public ButtonFunctions(MainActivity mainActivity){
        this.mainActivity  = mainActivity;
    }

}
