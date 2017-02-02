package com.example.sudhir.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Thread.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mainLayout;
    MyButton button[][];
    LinearLayout rows[];
    boolean player1Turn;
    boolean gameOver;
    public final static int NO_PLAYER = 0;
    public final static int PLAYER1 = 1;
    public final static int PLAYER2 = 2;
    public final static int PLAYER_1_WINS = 1;
    public final static int PLAYER_2_WINS = 2;
    public final static int DRAW = 3;
    public final static int INCOMPLETE = 4;
    int n;
    int count = 0;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (LinearLayout) findViewById(R.id.activity_main);
        setUpBoard(3);

    }

    private void setUpBoard(int g1) {
        n=g1;
        player1Turn = true;
        button = new MyButton[n][n];
        rows = new LinearLayout[n];
        gameOver = false;
        mainLayout.removeAllViews();
        for (int i = 0; i < n; i++){
           rows[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            params.setMargins(5,5,5,5);
            rows[i].setLayoutParams(params);
            rows[i].setOrientation(LinearLayout.HORIZONTAL);
            mainLayout.addView(rows[i]);
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                button[i][j] = new MyButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                params.setMargins(5,5,5,5);
                button[i][j].setLayoutParams(params);
                button[i][j].setText("");
                button[i][j].setOnClickListener(this);
                button[i][j].setPlayer(0);
                rows[i].addView(button[i][j]);
            }
        }
    }



    public void onClick(View v)
    {
        MyButton b = (MyButton) v;
        if(b.getPlayer()!=0)
        {
            Toast.makeText(this,"Illegal Move",Toast.LENGTH_SHORT).show();
        }
        else
        {
            count++;
            if(player1Turn)
            {
                b.setText("X");
                b.setPlayer(1);

            }
            else
            {
                b.setText("O");
                b.setPlayer(2);

            }
            player1Turn = !player1Turn;
            int status = gameStatus();
            if(status == PLAYER_1_WINS || status == PLAYER_2_WINS){
                Toast.makeText(this, "Player "+status+" wins !!", Toast.LENGTH_SHORT).show();
                gameOver = true;
                return;
            }

            if(status == DRAW){
                Toast.makeText(this, "Draw !!", Toast.LENGTH_SHORT).show();
                gameOver = true;
                return;
            }

        }
    }

    private int gameStatus() {


        // Rows
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (button[i][j].getPlayer() == NO_PLAYER || button[i][0].getPlayer() != button[i][j].getPlayer()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (button[i][0].getPlayer() == PLAYER1) {
                    return PLAYER_1_WINS;
                } else {
                    return PLAYER_2_WINS;
                }
            }

        }

// Columns
        for (int j = 0; j < n; j++) {
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (button[i][j].getPlayer() == NO_PLAYER || button[0][j].getPlayer() != button[i][j].getPlayer()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (button[0][j].getPlayer() == PLAYER1) {
                    return PLAYER_1_WINS;
                } else {
                    return PLAYER_2_WINS;
                }
            }

        }

        // Diagonal 1
        boolean flag = true;
        for(int i = 0; i < n; i++){
            if (button[i][i].getPlayer() == NO_PLAYER || button[0][0].getPlayer() != button[i][i].getPlayer()) {
                flag = false;
                break;
            }
        }
        if (flag) {
            if (button[0][0].getPlayer() == PLAYER1) {
                return PLAYER_1_WINS;
            } else {
                return PLAYER_2_WINS;
            }
        }

        // Diagonal 2
        flag = true;
        for(int i = n - 1; i >= 0; i--){
            int col = n - 1 - i;
            if (button[i][col].getPlayer() == NO_PLAYER || button[n - 1][0].getPlayer() != button[i][col].getPlayer()) {
                flag = false;
                break;
            }
        }
        if (flag) {
            if (button[n - 1][0].getPlayer() == PLAYER1) {
                return PLAYER_1_WINS;
            } else {
                return PLAYER_2_WINS;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(button[i][j].getPlayer() == NO_PLAYER){
                    return INCOMPLETE;
                }
            }
        }
        return DRAW;
    }



}
