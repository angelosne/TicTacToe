package com.angelos.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startGameButton = findViewById(R.id.buttonStartGame);
        startGameButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        EditText player1name = findViewById(R.id.EditTextPlayer1);
        EditText player2name = findViewById(R.id.EditTextPlayer2);
        if (v.getId() == R.id.buttonStartGame) {
            if (player1name.getText().length()!=0 && player2name.length()!=0) {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("PLAYER_1_NAME",player1name.getText().toString());
                intent.putExtra("PLAYER_2_NAME",player2name.getText().toString());
                startActivity(intent);
            }
            else {
                Toast toast = Toast.makeText(this,"Please fill the names",Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
