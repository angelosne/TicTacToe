package com.angelos.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private void writeXorO(Button button) {
        if (whichPlayerIsCurrentlyPlaying == 1) {
            button.setText("X");
        } else {
            button.setText("O");
        }
    }

    private void changeTurn() {

        if (whichPlayerIsCurrentlyPlaying == 1) {
            nextPlayer.setText(player2name + " you are next!");
            whichPlayerIsCurrentlyPlaying = 2;
        } else {
            whichPlayerIsCurrentlyPlaying = 1;
            nextPlayer.setText(player1name + " you are next!");
            ;
        }
    }

    private boolean isEmpty(Button button) {
        if (button.getText().length() != 0) {
            return false;
        } else {
            return true;
        }
    }

    private void showToast() {
        Toast toast = Toast.makeText(this, "This button has been played. Choose another one", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void play(Button button) {
        if (isEmpty(button)) {
            writeXorO(button);
            changeTurn();
        } else {
            showToast();
        }
    }

}
