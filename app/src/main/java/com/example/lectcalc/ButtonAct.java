package com.example.lectcalc;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ButtonAct extends MainActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
    }

}

