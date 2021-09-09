package com.example.testjson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView; // 把視圖的元件宣告成全域變數
    Button button;
    String result; // 儲存資料用的字串
    String name; // 使用者名稱
    String B = null;
    String P;
    String A;

    //Http_Post HP;
    //static Handler handler;


    private Runnable mutiThread = new Runnable() {
        public void run() {
            try {
                // 把edittext數值記錄下來
                EditText account = (EditText) findViewById(R.id.account);
                EditText password = (EditText) findViewById(R.id.password);
                String inputAccount = account.getText().toString();
                String inputPassword = password.getText().toString();

                //HP = new Http_Post();

                //HP.POST(inputAccount,inputPassword,url);
                //textView.setText("result");
/*
                handler = new Handler(){
                    public void handleMessage(Message msg){
                        switch (msg.what){
                            case 123:
                                String ss = (String)msg.obj;
                                Toast.makeText(MainActivity.this, ss, Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                };

 */
                JSONArray B = new JSONArray();
                B = Getdata.get(inputAccount,inputPassword);
                //JSONObject jsonObject = B.getJSONObject(0);
                //result = jsonObject.getString("name");
                //textView.setText("a");
                JSONObject jsonObject = B.getJSONObject(0);
                if(jsonObject.getInt("id")!=-1){
                    name = jsonObject.getString("name");
                    P = jsonObject.getString("password");
                    A = jsonObject.getString("account");
                    if(jsonObject.getInt("identity")==2){
                        result = "歡迎老師";
                    }else{
                        result = "登入成功";
                    }
                }else{
                    //Toast.makeText(this,"登入失敗，請重新輸入",Toast.LENGTH_LONG).show();
                    result = "登入失敗，請重新輸入";
                }
                //result = B;
                /*for(int i = 0;i<B.length();i++){
                    JSONObject jsonObject = B.getJSONObject(i);
                    if(jsonObject.getString("identity").compareTo("1") == 0){
                        result = "歡迎老師";
                    }else if(jsonObject.getString("identity").compareTo("0") == 0){
                        result = "登入成功";
                    }
                }
                /*
                String realaccount = null;
                String realpassword = null;
                int id = 0;
                String ans = null;
                int count = 0;
                for(int i = 0;i < B.length();i++){
                    JSONObject jsonObject = B.getJSONObject(i);
                    realaccount = jsonObject.getString("account");
                    if( realaccount.compareTo(inputAccount) == 0){
                        realpassword = jsonObject.getString("password");
                        if(realpassword.compareTo(inputPassword) == 0){
                            name = jsonObject.getString("name");
                            if(jsonObject.getInt("identity") == 0){
                                ans = "登入成功";
                                count = count + 1;
                            }else{
                                ans = "歡迎老師";
                                count = count + 1;
                            }

                        }
                    }
                    else if(count == 0 && i == B.length()-1){
                        ans = "登入失敗";
                    }
                    //box += line  +"\n";
                    // 每當讀取出一列，就加到存放字串後面
                }

                // lineNObject jsonObject = new JSONObject(builder.toString());
                //String a = jsonObject.getString("id");
                account.setText(null);
                password.setText(null);
                result = ans; // 把存放用字串放到全域變數

                 */
                password.setText("");
                account.setText("");
            } catch (Exception e) {
                result = e.toString();;
            // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } /*catch (Exception e) {
                result = e.toString(); // 如果出事，回傳錯誤訊息
            }*/
            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {
                    textView.setText(result); // 更改顯示文字

                    if(result.compareTo("登入成功") == 0){
                        //textView.setText("閉嘴");
                        Intent intent = new Intent(MainActivity.this,MainPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putString("account", A);
                        bundle.putString("password", P);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        //finish();
                    }else if(result.compareTo("歡迎老師") == 0){
                        Intent intent = new Intent(MainActivity.this,Main_Teacher.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putString("account", A);
                        bundle.putString("password", P);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"登入失敗，請重新輸入",Toast.LENGTH_LONG).show();
                    }
                    result = "cool man";
                    //textView.setText("cool man");
                }
            });
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
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



        /*JSONObject obj = new JSONObject();
        try {
            obj.put("name", "John");
            obj.put("sex", "male");
            obj.put("age", 22);
        } catch (JSONException e) {
            e.printStackTrace();
        }
         tv1 = (TextView)findViewById(R.id.textView);
         tv1.setText(obj.toString());
         */
    }
}