package com.example.kaine.loginwithreg;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kaine.loginwithreg.db.MySqliteHelper;

public class PersonalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        select();
        Button button9 = (Button) findViewById(R.id.button_exit);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonalActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void select() {
        TextView text = (TextView) findViewById(R.id.text);
        MySqliteHelper helper;
        String name;
        helper = new MySqliteHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select * from users";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            name = cursor.getString(1);
            text.setText(name);
        }
        cursor.close();
        db.close();

    }
}
