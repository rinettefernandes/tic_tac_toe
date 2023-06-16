package com.example.mini2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private String player1Name;
    private String player2Name;
    // 0: yellow, 1: red, 2: empty
    private int activePlayer = 0;
    private boolean gameIsActive = true;
    // 2 means unplayed
    private int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // winning positions
    private int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
            {0, 4, 8}, {2, 4, 6} // diagonals
    };



    public void dropIn(View view) {
        ImageView counter = (ImageView) view;



        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.circle1);
                activePlayer = 1;
                TextView Message = findViewById(R.id.turn);
                Message.setText("Turn: "+player2Name+"(0)");


            } else {
                TextView Message = findViewById(R.id.turn);
                Message.setText("Turn: "+player1Name+"(X)");

                counter.setImageResource(R.drawable.circle2);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    // Someone has won
                    gameIsActive = false;
                    String winner;

                    if (activePlayer == 1)
                        winner = player1Name+"(X)";
                    else
                        winner = player2Name+"(0)";
                    LinearLayout linearLayout = findViewById(R.id.winner);
                    linearLayout.setVisibility(View.VISIBLE);

                    TextView winnerMessage = findViewById(R.id.winner1);
                    winnerMessage.setText(winner + " has won!");


                }
            }

            boolean isBoardFull = true;
            for (int state : gameState) {
                if (state == 2) {
                    isBoardFull = false;
                    break;
                }
            }

            if (isBoardFull && gameIsActive) {
                // It's a draw
                gameIsActive = false;

                TextView winnerMessage = findViewById(R.id.winner1);
                winnerMessage.setText("It's a draw!");

                LinearLayout layout = findViewById(R.id.winner);
                layout.setVisibility(View.VISIBLE);
            }
        }
    }
    public void finishButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        finish(); // Optional: If you want to finish the current activity after navigating to the NameEntryActivity
    }

    public void playAgain(View view) {
        activePlayer = 1;
        gameIsActive = true;
        int count = 0;
        LinearLayout linearLayout = findViewById(R.id.winner);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Reset the game state
        Arrays.fill(gameState, 2);

        linearLayout.setVisibility(View.INVISIBLE);

        // Clear the images in the grid layout
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        player1Name = intent.getStringExtra("player1Name");
        player2Name = intent.getStringExtra("player2Name");
    }
}

