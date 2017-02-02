package com.example.sudhir.tictactoe;

import android.content.Context;
import android.widget.Button;

/**
 * Created by sudhir on 31/01/17.
 */

public class MyButton extends Button {
    private int player;
    public MyButton(Context context)
    {
        super(context);
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
