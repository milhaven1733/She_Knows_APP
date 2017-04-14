package com.example.kaine.loginwithreg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaine.loginwithreg.db.MySqliteHelper;

/**
 * 登录注册
 * 涉及到sqlite，sharedprefrence俩大存储方式的用法
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_reg,btn_log;
    private EditText edit_name,edit_pwd;
    private MySqliteHelper helper;
    private CheckBox checkbox;
    private String name ,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        checkIsRemenber();
    }

    //判断记住密码是否选中
    private void checkIsRemenber() {
        SharedPreferences sp = getSharedPreferences("user", 0);
        boolean isRemenber = sp.getBoolean("isCheck", true);
        if(isRemenber){
            checkbox.setChecked(true);
            edit_name.setText(sp.getString("name",""));
            edit_pwd.setText(sp.getString("pwd",""));
        }
    }

    //初始化View
    private void initView() {
        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_log = (Button) findViewById(R.id.btn_log);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        //记住密码
        checkbox = (CheckBox) findViewById(R.id.checkBox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences sp = getSharedPreferences("user", 0);
                SharedPreferences.Editor edit = sp.edit();
                if(isChecked){
                    edit.putBoolean("isCheck",true);
                    edit.commit();
                }else{
                    edit.putBoolean("isCheck",false);
                    edit.commit();
                }
            }
        });
        btn_reg.setOnClickListener(this);
        btn_log.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //注册
            case R.id.btn_reg:
                insert();
                break;
            //登录
            case R.id.btn_log:
                select();;
                break;
        }
    }

    //注册
    private void insert(){
        helper = new MySqliteHelper(MainActivity.this );
        SQLiteDatabase db = helper.getWritableDatabase();
        //查询用户名是否重复
        String sql = "select * from users";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            name = cursor.getString(1);
            pwd = cursor.getString(2);
        }

        if(edit_name.getText().toString().equals(name)){
            Toast.makeText(this,"已存在此用户，请重新注册",Toast.LENGTH_SHORT).show();
        }else{
            if (edit_name.getText().toString().trim() == null ||
                    edit_name.getText().toString().trim().equals("") ||
                    edit_pwd.getText().toString().trim().equals("") ||
                    edit_pwd.getText().toString().trim().equals("")) {
                Toast.makeText(this,"用户名与密码不能为空",Toast.LENGTH_SHORT).show();

            }else{
                String sql2 = "insert into users(name,pwd) values ('"+edit_name.getText().toString()+"','"+edit_pwd.getText().toString()+"')";
                db.execSQL(sql2);
                Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
                edit_name.setText("");
                edit_pwd.setText("");
            }
        }
    }

    //登录
    public void select() {

        helper = new MySqliteHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "select * from users";

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            //第一列为id
            name = cursor.getString(1); //获取第2列的值,第一列的索引从0开始
            pwd = cursor.getString(2);//获取第3列的值

        }

        checkNameWithPwd(name,pwd);

        cursor.close();
        db.close();
        //Toast.makeText(this, "已经关闭数据库", Toast.LENGTH_SHORT).show();
    }

    //判断用户名与密码是否匹配
    private void checkNameWithPwd(String name, String pwd) {
        if ((edit_name.getText().toString().equals(name)) && (edit_pwd.getText().toString().equals(pwd))) {
            SharedPreferences sp = getSharedPreferences("user",0);
            SharedPreferences.Editor edit = sp.edit();
            if(checkbox.isChecked()){//勾选记住密码
                edit.putBoolean("isCheck",true);
                edit.putString("name",name);
                edit.putString("pwd",pwd);
                edit.commit();
            }else{
                edit.clear();
                edit.commit();
            }
            Toast.makeText(this, "用户登录成功", Toast.LENGTH_SHORT).show();
            Intent MainActivity = new Intent();
            MainActivity.setClass(this, LogSuccessActivity.class);
            this.startActivity(MainActivity);
            finish();//退出
        } else {
            Toast.makeText(this, "账号或者密码错误,请重新输入", Toast.LENGTH_SHORT).show();
        }
    }
}
