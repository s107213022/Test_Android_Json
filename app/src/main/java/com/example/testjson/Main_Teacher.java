package com.example.testjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Main_Teacher extends AppCompatActivity {
    JSONArray B = new JSONArray();
    TextView nameword;
    TextView member; // 把視圖的元件宣告成全域變數
    Button button;
    String result;
    int ram ;
    private Runnable mutiThread = new Runnable() {
        public void run() {
            try {
                Intent intent = new Intent(Main_Teacher.this,Draw_lots.class);
                startActivity(intent);
                /*int count = 0;
                B = Getdata.getstu("http://192.168.72.41/android_test/getdata.php?id=1");
                Random rand = new Random();
                int int_random = rand.nextInt(B.length());
                member.setText(int_random);
                JSONObject jsonObject = B.getJSONObject(int_random);
                result = jsonObject.getString("name");
                ram = int_random;
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理


                 */
            } catch (Exception e) {
                result = e.toString(); // 如果出事，回傳錯誤訊息
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__teacher);
        nameword = findViewById(R.id.name);
        Bundle bundle = getIntent().getExtras();
        String nameuser = bundle.getString("name");
        nameword.setText(nameuser);
        Toast.makeText(this, "歡迎登入", Toast.LENGTH_LONG).show();

        button = findViewById(R.id.button);
        member = findViewById(R.id.member);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            // 按鈕事件
            public void onClick(View view) {
                // 按下之後會執行的程式碼
                // 宣告執行緒
                Thread thread = new Thread(mutiThread);
                thread.start(); // 開始執行
            }
        });
    }
}