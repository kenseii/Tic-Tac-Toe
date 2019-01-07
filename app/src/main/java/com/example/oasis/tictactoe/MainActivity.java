package com.example.oasis.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //this array holds the game status by knowing which spots are playable,
    // 2 represents an empty spot

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // array of arrays of winning positions
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    //to set the active player 0 for yellow, 1 for red
    int activePlayer = 0;

    // variable to indicate whether or not the match is ongoing
    boolean gameActive = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // check whether there are empty spots
    public static boolean contains(int[] arr, int item) {

        for (int n : arr) {
            if (item == n) {
                return true;
            }
        }
        return false;
    }

    // method to reset the game

    public void playAgain(View view) {
        //Hide the button again
        Button new_game = findViewById(R.id.new_game);
        new_game.setVisibility(View.INVISIBLE);

        //Loop through the elements of the grid layout
        android.support.v7.widget.GridLayout grid = findViewById(R.id.gridLayout);
        for (int i = 0; i < grid.getChildCount(); i++) {
            // get the contents of the gridlayout
            ImageView counters = (ImageView) grid.getChildAt(i);
            // set the content of those placeholders to null
            counters.setImageDrawable(null);
        }

        //Resetting the variables to original value
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        //to set the active player 0 for yellow, 1 for red
        activePlayer = 0;

        // variable to indicate whether or not the match is ongoing
        gameActive = true;
    }

    public void dropIn(View view) {
        // get the tapped placeholder
        ImageView counter = (ImageView) view;

        // gets the tag of the tapped button
        int tappedCounter = Integer.parseInt(view.getTag().toString());
        // validate if the tapped spot was empty before putting in something and also check that the match is still ongoing
        if (gameState[tappedCounter] == 2 && gameActive) {
            //Hide the image off the screen
            counter.setTranslationY(-1500);
            //check active player and from there know which image to show
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            //bring the image from the top of the screen rotating it
            counter.animate().translationYBy(1500).rotation(3600).setDuration(500);
            // log the played move's number
            Log.i("Tapped tag", view.getTag().toString());

            // update the game state array index = tappedCounter with the active player y/r
            gameState[tappedCounter] = activePlayer;

            //winner variable
            String winner = "";
            // loop in winning positions array and see if there is a winning one
            for (int[] winningPosition : winningPositions) {
                // verify if the same values(1:red, 0:yellow) are inside the winning positions
                // and ensure none is 2(empty)
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    // There is a winner
                    if (activePlayer == 1) {
                        winner = "Ki wo kachimasu";
                    } else {
                        winner = "Aka wo kachimasu";
                    }
                    Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
                    gameActive = false;
                    Button new_game = findViewById(R.id.new_game);
                    new_game.setVisibility(View.VISIBLE);

                }


            }
            //  Check if all the positions are filled without a winner if so return its a draw

            Log.i("Content", Arrays.toString(gameState));
            boolean container = contains(gameState, 2);
            Log.i("true 7 times at least", Boolean.toString(container));
            if (!container) {
                Toast.makeText(this, "Its a draw", Toast.LENGTH_SHORT).show();
                gameActive = false;
                Button new_game = findViewById(R.id.new_game);
                new_game.setVisibility(View.VISIBLE);

            }



        }
    }
}
