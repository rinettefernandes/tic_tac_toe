package com.example.mini2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity2 extends AppCompatActivity {

        private EditText player1EditText;
        private EditText player2EditText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);

            player1EditText = findViewById(R.id.player1EditText);
            player2EditText = findViewById(R.id.player2EditText);
        }

        public void startGame(View view) {
            String player1Name = player1EditText.getText().toString();
            String player2Name = player2EditText.getText().toString();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("player1Name", player1Name);
            intent.putExtra("player2Name", player2Name);
            startActivity(intent);
        }
    }




