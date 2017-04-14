package com.example.kaine.loginwithreg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.she_class);
        Button button_8 = (Button) findViewById(R.id.button8);
        Button button_7 = (Button) findViewById(R.id.button7);
        Button button_9 = (Button) findViewById(R.id.button9);
        button_8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this, xuetang_web.class);
                startActivity(intent);
            }
        });
        button_7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this,qingxing_web.class);
                startActivity(intent);
            }
        });
        button_9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this, zhixing_web.class);
                startActivity(intent);
            }
        });
    }
}
