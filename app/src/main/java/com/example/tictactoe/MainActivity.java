package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    public TextView message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variables
        message = findViewById(R.id.message);
        assignButtonValues();

        //Create players and assign first turn
        x = new Player("Player X");
        x.isTurn = true;
        o = new Player("Player O");
        o.isTurn = false;
        displayPlayerTurn();
    }

    public void onClick(View v){

    }

    public void changeTurn(){
        if(x.isTurn){
            x.isTurn = false;
            o.isTurn = true;
        }
        else{
            x.isTurn = true;
            o.isTurn = false;
        }
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
        Player currPlayer;
        if(x.isTurn){
            currPlayer = x;
        }
        else{
            currPlayer = o;
        }
        message.setText(currPlayer.name + "'s turn");
    }
}

class Player{
    public String name;
    public boolean isTurn;

    public Player(String name){
        this.name = name;
    }
}
