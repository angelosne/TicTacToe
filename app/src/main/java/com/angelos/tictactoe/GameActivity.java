package com.angelos.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    Bundle extras;
    String player1name;
    String player2name;
    int whichPlayerIsCurrentlyPlaying = 1;
    boolean isGameEnded = false;

    Button buttonX0Y0;
    Button buttonX1Y0;
    Button buttonX2Y0;
    Button buttonX0Y1;
    Button buttonX1Y1;
    Button buttonX2Y1;
    Button buttonX0Y2;
    Button buttonX1Y2;
    Button buttonX2Y2;
    TextView nextPlayer;
    TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        intent = getIntent();
        extras = intent.getExtras();
        player1name = extras.getString("PLAYER_1_NAME");
        player2name = extras.getString("PLAYER_2_NAME");
        nextPlayer = findViewById(R.id.textWhoIsPlaying);
        winner = findViewById(R.id.textWinner);


        nextPlayer.setText(player1name + " you are next!");

        buttonX0Y0 = findViewById(R.id.buttonX0Y0);
        buttonX1Y0 = findViewById(R.id.buttonX1Y0);
        buttonX2Y0 = findViewById(R.id.buttonX2Y0);
        buttonX0Y1 = findViewById(R.id.buttonX0Y1);
        buttonX1Y1 = findViewById(R.id.buttonX1Y1);
        buttonX2Y1 = findViewById(R.id.buttonX2Y1);
        buttonX0Y2 = findViewById(R.id.buttonX0Y2);
        buttonX1Y2 = findViewById(R.id.buttonX1Y2);
        buttonX2Y2 = findViewById(R.id.buttonX2Y2);
        buttonX0Y0.setOnClickListener(this);
        buttonX1Y0.setOnClickListener(this);
        buttonX2Y0.setOnClickListener(this);
        buttonX0Y1.setOnClickListener(this);
        buttonX1Y1.setOnClickListener(this);
        buttonX2Y1.setOnClickListener(this);
        buttonX0Y2.setOnClickListener(this);
        buttonX1Y2.setOnClickListener(this);
        buttonX2Y2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonX0Y0) {
            play(buttonX0Y0);
        } else if (v.getId() == R.id.buttonX0Y1) {
            play(buttonX0Y1);
        } else if (v.getId() == R.id.buttonX0Y2) {
            play(buttonX0Y2);
        } else if (v.getId() == R.id.buttonX1Y0) {
            play(buttonX1Y0);
        } else if (v.getId() == R.id.buttonX1Y1) {
            play(buttonX1Y1);
        } else if (v.getId() == R.id.buttonX1Y2) {
            play(buttonX1Y2);
        } else if (v.getId() == R.id.buttonX2Y0) {
            play(buttonX2Y0);
        } else if (v.getId() == R.id.buttonX2Y1) {
            play(buttonX2Y1);
        } else if (v.getId() == R.id.buttonX2Y2) {
            play(buttonX2Y2);
        }

    }

    private void play(Button button) {
        if (isEmpty(button)) {
            writeXorO(button);
            if (isItAWin(button)) {
                winnerIs();
                disableButtons();
            }
            isItDraw();

            changeTurn();
        } else {
            showToast();
        }
    }

    private void isItDraw() {

        if (!buttonX0Y0.getText().toString().isEmpty() &&
                !buttonX0Y1.getText().toString().isEmpty() &&
                !buttonX0Y2.getText().toString().isEmpty() &&
                !buttonX1Y0.getText().toString().isEmpty() &&
                !buttonX1Y1.getText().toString().isEmpty() &&
                !buttonX1Y2.getText().toString().isEmpty() &&
                !buttonX2Y0.getText().toString().isEmpty() &&
                !buttonX2Y1.getText().toString().isEmpty() &&
                !buttonX2Y2.getText().toString().isEmpty()) {

            disableButtons();
            winner.setText("Draw! Play again!");
        }
    }

    private void disableButtons() {
        buttonX0Y0.setEnabled(false);
        buttonX1Y0.setEnabled(false);
        buttonX2Y0.setEnabled(false);
        buttonX0Y1.setEnabled(false);
        buttonX1Y1.setEnabled(false);
        buttonX2Y1.setEnabled(false);
        buttonX0Y2.setEnabled(false);
        buttonX1Y2.setEnabled(false);
        buttonX2Y2.setEnabled(false);
    }

    private boolean isEmpty(Button button) {
        return button.getText().length() == 0;
    }

    private void writeXorO(Button button) {
        if (whichPlayerIsCurrentlyPlaying == 1) {
            button.setText("X");
        } else {
            button.setText("O");
        }
    }

    private boolean isItAWin(Button button) {
        if (button == buttonX0Y0) {
            return hasFirstColumn() || hasFirstRow() || hasCrossForward();
        } else if (button == buttonX0Y1) {
            return hasFirstColumn() || hasSecondRow();
        } else if (button == buttonX0Y2) {
            return hasFirstColumn() || hasThirdRow() || hasCrossBackward();
        } else if (button == buttonX1Y0) {
            return hasSecondColumn() || hasFirstRow();
        } else if (button == buttonX1Y1) {
            return hasSecondColumn() || hasSecondRow() || hasCrossForward() || hasCrossBackward();
        } else if (button == buttonX1Y2) {
            return hasSecondColumn() || hasThirdRow();
        } else if (button == buttonX2Y0) {
            return hasThirdColumn() || hasFirstRow() || hasCrossBackward();
        } else if (button == buttonX2Y1) {
            return hasThirdColumn() || hasSecondRow();
        } else if (button == buttonX2Y2) {
            return hasThirdColumn() || hasThirdRow() || hasCrossForward();
        }
        return false; //Εδώ δεν μπόρεσα να βάλω else

    }


    private void changeTurn() {

        if (whichPlayerIsCurrentlyPlaying == 1) {
            nextPlayer.setText(player2name + " you are next!");
            whichPlayerIsCurrentlyPlaying = 2;
        } else {
            whichPlayerIsCurrentlyPlaying = 1;
            nextPlayer.setText(player1name + " you are next!");
        }
    }

    private void winnerIs() {
        if (whichPlayerIsCurrentlyPlaying == 1)
            winner.setText(player1name + " has won the game!");
        else {
            winner.setText(player2name + " has won the game!");
        }
    }


    private void showToast() {
        Toast toast = Toast.makeText(this, "This button has been played. Choose another one", Toast.LENGTH_SHORT);
        toast.show();
    }


    private boolean hasFirstRow() {
        if (buttonX0Y0.getText().toString().equals(buttonX1Y0.getText().toString()) && buttonX1Y0.getText().toString().equals(buttonX2Y0.getText().toString())) {
            return true;
        } else
            return false;
    }

    private boolean hasSecondRow() {
        if (buttonX0Y1.getText().toString().equals(buttonX1Y1.getText().toString()) && buttonX1Y1.getText().toString().equals(buttonX2Y1.getText().toString())) {
            return true;
        } else
            return false;
    }

    private boolean hasThirdRow() {
        if (buttonX0Y2.getText().toString().equals(buttonX1Y2.getText().toString()) && buttonX1Y2.getText().toString().equals(buttonX2Y2.getText().toString())) {
            return true;
        } else
            return false;
    }

    private boolean hasFirstColumn() {
        if (buttonX0Y0.getText().toString().equals(buttonX0Y1.getText().toString()) && buttonX0Y1.getText().toString().equals(buttonX0Y2.getText().toString())) {
            return true;
        } else
            return false;
    }

    private boolean hasSecondColumn() {
        if (buttonX1Y0.getText().toString().equals(buttonX1Y1.getText().toString()) && buttonX1Y1.getText().toString().equals(buttonX1Y2.getText().toString())) {
            return true;
        } else
            return false;
    }

    private boolean hasThirdColumn() {
        if (buttonX2Y0.getText().toString().equals(buttonX2Y1.getText().toString()) && buttonX2Y1.getText().toString().equals(buttonX2Y2.getText().toString())) {
            return true;
        } else
            return false;
    }

    private boolean hasCrossForward() {
        if (buttonX0Y0.getText().toString().equals(buttonX1Y1.getText().toString()) && buttonX1Y1.getText().toString().equals(buttonX2Y2.getText().toString())) {
            return true;
        } else
            return false;
    }

    private boolean hasCrossBackward() {
        if (buttonX2Y0.getText().toString().equals(buttonX1Y1.getText().toString()) && buttonX1Y1.getText().toString().equals(buttonX0Y2.getText().toString())) {
            return true;
        } else
            return false;
    }


}
