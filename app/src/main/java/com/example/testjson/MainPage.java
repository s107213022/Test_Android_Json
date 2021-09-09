package com.example.testjson;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainPage extends AppCompatActivity {

    /*TextView textView; // 把視圖的元件宣告成全域變數
    Button button;
    String result; // 儲存資料用的字串

     */
    TextView nameword;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        nameword = findViewById(R.id.name);
        TextView Acc = findViewById(R.id.acc);
        Bundle bundle = getIntent().getExtras();
        String nameuser = bundle.getString("name");
        String passuser = bundle.getString("password");
        String accuser = bundle.getString("account");
        Acc.setText(accuser);
        nameword.setText(nameuser);
        Toast.makeText(this, "歡迎登入", Toast.LENGTH_LONG).show();

    }
}