package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    public Player x;
    public Player o;
    Player currPlayer;

    public TextView message;
    int[] winningCombos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variables
        message = findViewById(R.id.message);
        assignButtonValues();

        winningCombos = new int[]{123, 456, 789, 147, 258, 369, 159, 357};

        //Create players and assign first turn
        x = new Player("X");
        currPlayer = x;
        o = new Player("O");
        displayPlayerTurn();
    }

    public void selectSquare(View v){
        Button selSquare = findViewById(v.getId());
        selSquare.setText(currPlayer.name);
        String buttonValue = selSquare.getTag().toString();
        int square = Integer.parseInt(buttonValue);

        currPlayer.squaresPlayed.add(square);
        //Toast.makeText(this, Integer.toString(currPlayer.squaresPlayed[square]), Toast.LENGTH_SHORT).show();

        if(checkForWinner()){
            Toast.makeText(this, currPlayer.name + "WINS", Toast.LENGTH_SHORT).show();
        }
        changeTurn();
    }

    private boolean checkForWinner() {
        int combo;
        int square;
        int matchingSquares = 0;
        //combo = winningCombos[num];
        for(int i = 0; i < winningCombos.length; i++){
            combo = winningCombos[i];
            for(int m = 0; m < 3; m++){
                square = combo % 10;
                if (currPlayer.squaresPlayed.contains(square)){
                    matchingSquares++;
                }
                combo /= 10;
            }
            if(matchingSquares == 3)
                return true;
        }
        return false;
    }

    public void onClick(View v){

    }

    public void changeTurn(){
        if(currPlayer == x){
            currPlayer = o;
        }
        else{
            currPlayer = x;
        }
        //displayPlayerTurn();
    }

    public void assignButtonValues(){
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
    }

    public void displayPlayerTurn(){
        setMessageText("Player " + currPlayer.name + "'s turn");
    }

    public void setMessageText(String msg){
        message.setText(msg);
    }
}

class Player{
    public String name;
    public List<Integer> squaresPlayed;

    public Player(String name){
        this.name = name;
         squaresPlayed = new ArrayList<>();
    }
}
