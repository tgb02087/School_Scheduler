package com.cookandroid.school_schedule;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScheduleActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "tgb02087.cafe24.com";
    private static String TAG = "phptest";
    private String mJsonString, userID;

    private Button schedule_viewBtn;
    private Spinner schedule_spinner;

    private AutoResizeTextView monday[] = new AutoResizeTextView[14];
    private AutoResizeTextView tuesday[] = new AutoResizeTextView[14];
    private AutoResizeTextView wednesday[] = new AutoResizeTextView[14];
    private AutoResizeTextView thursday[] = new AutoResizeTextView[14];
    private AutoResizeTextView friday[] = new AutoResizeTextView[14];
    private Schedule schedule;




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Toolbar toolbar = findViewById(R.id.sc_toobar);
        setTitle("시간표");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        schedule_viewBtn = (Button)findViewById(R.id.schedule_viewBtn);
        schedule_spinner = (Spinner)findViewById(R.id.schedule_spinner);

        monday[0] = (AutoResizeTextView)findViewById(R.id.monday0);
        monday[1] = (AutoResizeTextView)findViewById(R.id.monday1);
        monday[2] = (AutoResizeTextView)findViewById(R.id.monday2);
        monday[3] = (AutoResizeTextView)findViewById(R.id.monday3);
        monday[4] = (AutoResizeTextView)findViewById(R.id.monday4);
        monday[5] = (AutoResizeTextView)findViewById(R.id.monday5);
        monday[6] = (AutoResizeTextView)findViewById(R.id.monday6);
        monday[7] = (AutoResizeTextView)findViewById(R.id.monday7);
        monday[8] = (AutoResizeTextView)findViewById(R.id.monday8);
        monday[9] = (AutoResizeTextView)findViewById(R.id.monday9);
        monday[10] = (AutoResizeTextView)findViewById(R.id.monday10);
        monday[11] = (AutoResizeTextView)findViewById(R.id.monday11);
        monday[12] = (AutoResizeTextView)findViewById(R.id.monday12);
        monday[13] = (AutoResizeTextView)findViewById(R.id.monday13);

        tuesday[0] = (AutoResizeTextView)findViewById(R.id.tuesday0);
        tuesday[1] = (AutoResizeTextView)findViewById(R.id.tuesday1);
        tuesday[2] = (AutoResizeTextView)findViewById(R.id.tuesday2);
        tuesday[3] = (AutoResizeTextView)findViewById(R.id.tuesday3);
        tuesday[4] = (AutoResizeTextView)findViewById(R.id.tuesday4);
        tuesday[5] = (AutoResizeTextView)findViewById(R.id.tuesday5);
        tuesday[6] = (AutoResizeTextView)findViewById(R.id.tuesday6);
        tuesday[7] = (AutoResizeTextView)findViewById(R.id.tuesday7);
        tuesday[8] = (AutoResizeTextView)findViewById(R.id.tuesday8);
        tuesday[9] = (AutoResizeTextView)findViewById(R.id.tuesday9);
        tuesday[10] = (AutoResizeTextView)findViewById(R.id.tuesday10);
        tuesday[11] = (AutoResizeTextView)findViewById(R.id.tuesday11);
        tuesday[12] = (AutoResizeTextView)findViewById(R.id.tuesday12);
        tuesday[13] = (AutoResizeTextView)findViewById(R.id.tuesday13);

        wednesday[0] = (AutoResizeTextView)findViewById(R.id.wednesday0);
        wednesday[1] = (AutoResizeTextView)findViewById(R.id.wednesday1);
        wednesday[2] = (AutoResizeTextView)findViewById(R.id.wednesday2);
        wednesday[3] = (AutoResizeTextView)findViewById(R.id.wednesday3);
        wednesday[4] = (AutoResizeTextView)findViewById(R.id.wednesday4);
        wednesday[5] = (AutoResizeTextView)findViewById(R.id.wednesday5);
        wednesday[6] = (AutoResizeTextView)findViewById(R.id.wednesday6);
        wednesday[7] = (AutoResizeTextView)findViewById(R.id.wednesday7);
        wednesday[8] = (AutoResizeTextView)findViewById(R.id.wednesday8);
        wednesday[9] = (AutoResizeTextView)findViewById(R.id.wednesday9);
        wednesday[10] = (AutoResizeTextView)findViewById(R.id.wednesday10);
        wednesday[11] = (AutoResizeTextView)findViewById(R.id.wednesday11);
        wednesday[12] = (AutoResizeTextView)findViewById(R.id.wednesday12);
        wednesday[13] = (AutoResizeTextView)findViewById(R.id.wednesday13);

        thursday[0] = (AutoResizeTextView)findViewById(R.id.thursday0);
        thursday[1] = (AutoResizeTextView)findViewById(R.id.thursday1);
        thursday[2] = (AutoResizeTextView)findViewById(R.id.thursday2);
        thursday[3] = (AutoResizeTextView)findViewById(R.id.thursday3);
        thursday[4] = (AutoResizeTextView)findViewById(R.id.thursday4);
        thursday[5] = (AutoResizeTextView)findViewById(R.id.thursday5);
        thursday[6] = (AutoResizeTextView)findViewById(R.id.thursday6);
        thursday[7] = (AutoResizeTextView)findViewById(R.id.thursday7);
        thursday[8] = (AutoResizeTextView)findViewById(R.id.thursday8);
        thursday[9] = (AutoResizeTextView)findViewById(R.id.thursday9);
        thursday[10] = (AutoResizeTextView)findViewById(R.id.thursday10);
        thursday[11] = (AutoResizeTextView)findViewById(R.id.thursday11);
        thursday[12] = (AutoResizeTextView)findViewById(R.id.thursday12);
        thursday[13] = (AutoResizeTextView)findViewById(R.id.thursday13);

        friday[0] = (AutoResizeTextView)findViewById(R.id.friday0);
        friday[1] = (AutoResizeTextView)findViewById(R.id.friday1);
        friday[2] = (AutoResizeTextView)findViewById(R.id.friday2);
        friday[3] = (AutoResizeTextView)findViewById(R.id.friday3);
        friday[4] = (AutoResizeTextView)findViewById(R.id.friday4);
        friday[5] = (AutoResizeTextView)findViewById(R.id.friday5);
        friday[6] = (AutoResizeTextView)findViewById(R.id.friday6);
        friday[7] = (AutoResizeTextView)findViewById(R.id.friday7);
        friday[8] = (AutoResizeTextView)findViewById(R.id.friday8);
        friday[9] = (AutoResizeTextView)findViewById(R.id.friday9);
        friday[10] = (AutoResizeTextView)findViewById(R.id.friday10);
        friday[11] = (AutoResizeTextView)findViewById(R.id.friday11);
        friday[12] = (AutoResizeTextView)findViewById(R.id.friday12);
        friday[13] = (AutoResizeTextView)findViewById(R.id.friday13);

        schedule_viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spinnerdata = schedule_spinner.getSelectedItem().toString();
                userID = LoginInfor.getUserID();    //유저아이디 정보 받아오기
                schedule = new Schedule();
                schedule.Schedule_chk(monday, tuesday, wednesday, thursday, friday, ScheduleActivity.this);

                GetData task = new GetData();
                task.execute( "http://" + IP_ADDRESS + "/Schedule.php", userID, spinnerdata);

            }
        });


    }
    private class GetData extends AsyncTask<String, Void, String> {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ScheduleActivity.this);

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ScheduleActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //mTextViewResult.setText(result);
            Log.d(TAG, "response - " + result);

            if (result == null){
                Log.e(TAG, "에러 :" + result);
                alertBuilder
                        .setTitle("알림")
                        .setMessage("과목이 없습니다.")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                AlertDialog dialog = alertBuilder.create();
                dialog.show();
            }
            else {

                mJsonString = result;
                Log.e(TAG, "response code - " + result);
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {


            String userID = (String)params[1];
            String sbterm = (String)params[2];

            String serverURL = (String)params[0];

            String postParameters = "userID=" + userID + "&sbterm=" + sbterm;

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line= null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();
                return sb.toString();

            } catch (Exception e) {

                Log.d(TAG, "GetData : Error ", e);
                errorString = e.toString();
                return null;
            }

        }
    }


    private void showResult(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        String TAG_JSON="webnautes";
        String TAG_NAME = "SBname";
        String TAG_PROFESSOR ="SBprofessor";
        String TAG_MJ = "SBmj";
        String TAG_TIME = "sbtime";



        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String name = item.getString(TAG_NAME);
                String professor = item.getString(TAG_PROFESSOR);
                String mj = item.getString(TAG_MJ);
                String time = item.getString(TAG_TIME);

                if(!schedule.validate(time)){
                    alertBuilder
                            .setTitle("알림")
                            .setMessage("중복된 시간표입니다. 시간을 수정해주세요.")
                            .setCancelable(true)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(ScheduleActivity.this, Subject_List.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = alertBuilder.create();
                    dialog.show();
                }
                schedule.Schedule_chk(monday, tuesday, wednesday, thursday, friday, this);
                schedule.addSchedule(name,mj,time);
                schedule.setting(monday, tuesday, wednesday, thursday, friday, this);

            }



        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}
