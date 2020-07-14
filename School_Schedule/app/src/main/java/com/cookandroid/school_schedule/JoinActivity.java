package com.cookandroid.school_schedule;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class JoinActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "tgb02087.cafe24.com";
    private static String TAG = "phptest";

    private EditText IDText;
    private EditText PasswordText;
    private EditText ClassNumber;
    private  Button joinbutton;

    private AlertDialog dialog;     //알림창을 보여주는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        IDText = (EditText)findViewById(R.id.idText);
        PasswordText = (EditText)findViewById(R.id.passwordText);
        ClassNumber = (EditText)findViewById(R.id.classnumber);

        joinbutton=(Button)findViewById(R.id.joinButton);

        joinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = IDText.getText().toString();
                String password = PasswordText.getText().toString();
                String classnumber = ClassNumber.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/Join.php", id, password, classnumber);


                IDText.setText("");
                PasswordText.setText("");
                ClassNumber.setText("");

            }
        });
    }



    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(JoinActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //mTextViewResult.setText(result);
            AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
            if(result.equals("새로운 사용자를 추가했습니다.")){  // 성공시
                dialog = builder.setMessage(result)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {    // 확인을 누르면 로그인창으로 돌아감
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .create();
                dialog.show();
                Log.d(TAG, "POST response  - " + result);
            }
            else{   // 성공 외에
                dialog = builder.setMessage(result)
                        .setPositiveButton("확인", null)
                        .create();
                dialog.show();
                Log.d(TAG, "POST response  - " + result);
            }

        }


        @Override
        protected String doInBackground(String... params) {

            String id = (String)params[1];
            String password = (String)params[2];
            String classnumber = (String)params[3];

            // 1. php파일을 실행시킬 수 있는 주소와 전송할 데이터를 준비합니다.
            // POST 방식으로 데이터 전달시에는 데이터가 주소에 직접 입력되지 않습니다.

            String serverURL = (String)params[0];

            // HTTP 메시지 본문에 포함되어 전송되기 때문에 따로 데이터를 준비해야 합니다.
            // 전송할 데이터는 "이름=값" 형식이며 여러 개를 보내야 할 경우에는 항목 사이에 &를 추가합니다.
            // 여기에 적어준 이름을 나중에 PHP에서 사용하여 값을 얻게 됩니다.

            String postParameters = "id=" + id + "&password=" + password + "&classnumber=" + classnumber;


            try {

                // 2. HttpURLConnection 클래스를 사용하여 POST 방식으로 데이터를 전송합니다.

                URL url = new URL(serverURL);   // 주소가 저장된 변수를 이곳에 입력합니다.
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);     // 5초안에 응답 오지 않으면 예외가 발생.
                httpURLConnection.setConnectTimeout(5000);  // 5초안에 연결이 안되면 예외가 발생.
                httpURLConnection.setRequestMethod("POST"); // 요청 방식을 POST로 합니다.
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                // 전송할 데이터가 저장된 변수를 이곳에 입력. 인코딩을 고려해줘야 합니다.
                outputStream.flush();
                outputStream.close();

                // 3. 응답을 읽습니다.
                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {   // 정상적인 응답 데이터
                    inputStream = httpURLConnection.getInputStream();
                }
                else{                                                   // 에러 발생
                    inputStream = httpURLConnection.getErrorStream();
                }

                // 4. StringBuilder를 사용하여 수신되는 데이터를 저장합니다.
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();

                // 5. 저장된 데이터를 스트링으로 변환하여 리턴합니다.
                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }


}
