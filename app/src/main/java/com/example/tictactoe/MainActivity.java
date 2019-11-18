package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    Button btnShowSettings;
    public Player x;
    public Player o;
    Player currPlayer;

    public TextView message;
    public Button[] tiles;
    public int[] winningCombos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variables
        assignButtonValues();
        message = findViewById(R.id.message);
        btnShowSettings = findViewById(R.id.btnShowSettings);
        btnShowSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, new SettingsActivity())
                        .commit();
            }
        });
        winningCombos = new int[]{123, 456, 789, 147, 258, 369, 159, 357};

        startGame();
    }

    public void startGame(){
        x = new Player("X");
        currPlayer = x;
        o = new Player("O");
        displayPlayerTurn();
    }

    public void selectSquare(View v){
        Button selSquare = findViewById(v.getId());
        selSquare.setText(currPlayer.name);
        selSquare.setEnabled(false);

        // Assign square to the player that touched it
        String buttonValue = selSquare.getTag().toString();
        int square = Integer.parseInt(buttonValue);
        currPlayer.squaresPlayed.add(square);

        // Check the state of the game
        if(isWinner()){
            gameOver();
        }
        else if(isTie())
            tieGame();
        else
            changeTurn();
    }

    private void tieGame() {
        setMessageText("Good job you two... No one won.");
        for(Button b : tiles)
            b.setTextColor(Color.RED);
    }

    public boolean isTie(){
        for(Button b : tiles){
            if(b.getText().length() == 0)
                return false;
        }
        return true;
    }

    private void gameOver(){
        setMessageText("Player " + currPlayer.name + " WINS!!!!!!!!!");

        for(Button b : tiles)
            b.setEnabled(false);    // Disable tiles
    }

    private boolean isWinner() {
        int combo;
        int matchingSquares = 0;
        for(int i = 0; i < winningCombos.length; i++){
            combo = winningCombos[i];
            for(int m = 0; m < 3; m++){
                if (currPlayer.squaresPlayed.contains(combo % 10)){
                    matchingSquares++;
                }
                combo /= 10;
            }
            if(matchingSquares == 3){
                colorWinningSquares(i);
                return true;
            }
            matchingSquares = 0;
        }
        return false;
    }

    public void colorWinningSquares(int index){
        Button curr;
        int winningCombo = winningCombos[index];

        for(int i = 0; i < 3; i++){
            curr = tiles[(winningCombo % 10) - 1];
            curr.setTextColor(Color.GREEN);
            winningCombo /= 10;
        }
    }

    public void changeTurn(){
        if(currPlayer == x){
            currPlayer = o;
        }
        else{
            currPlayer = x;
        }
        displayPlayerTurn();
    }

    public void assignButtonValues(){
        tiles = new Button[]{findViewById(R.id.button1)
                            , findViewById(R.id.button2)
                            , findViewById(R.id.button3)
                            , findViewById(R.id.button4)
                            , findViewById(R.id.button5)
                            , findViewById(R.id.button6)
                            , findViewById(R.id.button7)
                            , findViewById(R.id.button8)
                            , findViewById(R.id.button9)};
    }

    public void displayPlayerTurn(){
        setMessageText("Player " + currPlayer.name + "'s turn");
    }

    public void setMessageText(String msg){
        message.setText(msg);
    }



    public void newGame(View v){
        for(Button b : tiles) {     // Set all tiles back to initial state
            b.setEnabled(true);
            b.setText("");
            b.setTextColor(Color.BLACK);
        }
        startGame();
    }

    public void onClick(View v){
    }
}

class Player{
    String name;
    List<Integer> squaresPlayed;

    Player(String name){
        this.name = name;
         squaresPlayed = new ArrayList<>();
    }
}
