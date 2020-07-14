package com.cookandroid.school_schedule;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "tgb02087.cafe24.com";
    private static String TAG = "phptest";
    private ArrayAdapter adater;

    private EditText sb_name, sb_professor, sb_credit;
    private Button sb_addBtn;
    private RadioGroup sb_unGroup;
    private Spinner termSpinner;
    private String SBname, SBprofessor, SBcredit, term, userID, SBmj;
    private int monday,tuesday,wednesday, thursday, friday;
    private String sbtime;

    ArrayList<String> mondaylist, tuesdaylist, wednesdaylist, thursdaylist, fridaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.sc_toobar);
        setSupportActionBar(toolbar);
        // 액션바에 홈버튼 표시 여부
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); 뒤로가기버튼

        // 액션바에 제목 표시
        getSupportActionBar().setTitle("JWU Schedule Management");

        final Button calculatorButton = (Button)findViewById(R.id.calculatorButton);
        final Button scheduleButton = (Button)findViewById(R.id.scheduleButton);
        final Button listButton = (Button)findViewById(R.id.ListButton);


        final Spinner mon_spinner1=(Spinner)findViewById(R.id.monday_spinner1);
        final Spinner mon_spinner2=(Spinner)findViewById(R.id.monday_spinner2);
        final Spinner mon_spinner3=(Spinner)findViewById(R.id.monday_spinner3);
        final Spinner tues_spinner1=(Spinner)findViewById(R.id.tuesday_spinner1);
        final Spinner tues_spinner2=(Spinner)findViewById(R.id.tuesday_spinner2);
        final Spinner tues_spinner3=(Spinner)findViewById(R.id.tuesday_spinner3);
        final Spinner wednes_spinner1=(Spinner)findViewById(R.id.wednesday_spinner1);
        final Spinner wednes_spinner2=(Spinner)findViewById(R.id.wednesday_spinner2);
        final Spinner wednes_spinner3=(Spinner)findViewById(R.id.wednesday_spinner3);
        final Spinner thurs_spinner1=(Spinner)findViewById(R.id.thursday_spinner1);
        final Spinner thurs_spinner2=(Spinner)findViewById(R.id.thursday_spinner2);
        final Spinner thurs_spinner3=(Spinner)findViewById(R.id.thursday_spinner3);
        final Spinner fri_spinner1=(Spinner)findViewById(R.id.friday_spinner1);
        final Spinner fri_spinner2=(Spinner)findViewById(R.id.friday_spinner2);
        final Spinner fri_spinner3=(Spinner)findViewById(R.id.friday_spinner3);


        final CheckBox chk1 = (CheckBox)findViewById(R.id.chk_monday);
        final CheckBox chk2 = (CheckBox)findViewById(R.id.chk_tuesday);
        final CheckBox chk3 = (CheckBox)findViewById(R.id.chk_wednesday);
        final CheckBox chk4 = (CheckBox)findViewById(R.id.chk_thursday);
        final CheckBox chk5 = (CheckBox)findViewById(R.id.chk_friday);
        final RadioButton major = (RadioButton)findViewById(R.id.major);


        sb_name = (EditText)findViewById(R.id.sb_name);
        sb_professor = (EditText)findViewById(R.id.sb_professor);
        sb_credit = (EditText)findViewById(R.id.sb_credit);
        sb_unGroup = (RadioGroup)findViewById(R.id.sb_unGroup);
        termSpinner = (Spinner)findViewById(R.id.termSpinner);

        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chk1.isChecked()){      // 체크되었을시
                    mon_spinner1.setVisibility(View.VISIBLE);
                    mon_spinner2.setVisibility(View.VISIBLE);
                    mon_spinner3.setVisibility(View.VISIBLE);
                    monday = 1;
                }
                else{       // 체크되지않으면
                    mon_spinner1.setVisibility(View.INVISIBLE);
                    mon_spinner2.setVisibility(View.INVISIBLE);
                    mon_spinner3.setVisibility(View.INVISIBLE);
                    monday = 0;
                }
            }
        });
        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chk2.isChecked()){      // 체크되었을시
                    tues_spinner1.setVisibility(View.VISIBLE);
                    tues_spinner2.setVisibility(View.VISIBLE);
                    tues_spinner3.setVisibility(View.VISIBLE);
                    tuesday = 1;
                }
                else{       // 체크되지않으면
                    tues_spinner1.setVisibility(View.INVISIBLE);
                    tues_spinner2.setVisibility(View.INVISIBLE);
                    tues_spinner3.setVisibility(View.INVISIBLE);
                    tuesday = 0;
                }
            }
        });
        chk3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chk3.isChecked()){      // 체크되었을시
                    wednes_spinner1.setVisibility(View.VISIBLE);
                    wednes_spinner2.setVisibility(View.VISIBLE);
                    wednes_spinner3.setVisibility(View.VISIBLE);
                    wednesday = 1;
                }
                else{       // 체크되지않으면
                    wednes_spinner1.setVisibility(View.INVISIBLE);
                    wednes_spinner2.setVisibility(View.INVISIBLE);
                    wednes_spinner3.setVisibility(View.INVISIBLE);
                    wednesday = 0;
                }
            }
        });
        chk4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chk4.isChecked()){      // 체크되었을시
                    thurs_spinner1.setVisibility(View.VISIBLE);
                    thurs_spinner2.setVisibility(View.VISIBLE);
                    thurs_spinner3.setVisibility(View.VISIBLE);
                    thursday = 1;
                }
                else{       // 체크되지않으면
                    thurs_spinner1.setVisibility(View.INVISIBLE);
                    thurs_spinner2.setVisibility(View.INVISIBLE);
                    thurs_spinner3.setVisibility(View.INVISIBLE);
                    thursday = 0;
                }
            }
        });
        chk5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chk5.isChecked()){      // 체크되었을시
                    fri_spinner1.setVisibility(View.VISIBLE);
                    fri_spinner2.setVisibility(View.VISIBLE);
                    fri_spinner3.setVisibility(View.VISIBLE);
                    friday = 1;
                }
                else{       // 체크되지않으면
                    fri_spinner1.setVisibility(View.INVISIBLE);
                    fri_spinner2.setVisibility(View.INVISIBLE);
                    fri_spinner3.setVisibility(View.INVISIBLE);
                    friday = 0;
                }
            }
        });

        sb_addBtn = (Button)findViewById(R.id.sb_addBtn);
        sb_addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SBname = sb_name.getText().toString();
                SBprofessor = sb_professor.getText().toString();
                SBcredit = sb_credit.getText().toString();

                int SB_un = sb_unGroup.getCheckedRadioButtonId();   // getCheckedRadioButtonId의 리턴값은 선택된 radiobutton의 id 값.
                RadioButton SB_mj = (RadioButton)findViewById(SB_un);
                SBmj = SB_mj.getText().toString();   // 문자열로 id값의 값을 받아온다.

                term = termSpinner.getSelectedItem().toString();    // Spinner값 받아오기
                userID = LoginInfor.getUserID();        // 유저아이디 받아오기.

                // 여기서 부터 과목 시간 기능.
                sbtime = "";
                mondaylist= new ArrayList<>();
                tuesdaylist= new ArrayList<>();
                wednesdaylist= new ArrayList<>();
                thursdaylist= new ArrayList<>();
                fridaylist= new ArrayList<>();

                if(monday == 1){
                    mondaylist.add(mon_spinner1.getSelectedItem().toString());  //스피너값 monday리스트에저장
                    mondaylist.add(mon_spinner2.getSelectedItem().toString());
                    mondaylist.add(mon_spinner3.getSelectedItem().toString());

                    String[] monday = new String[mondaylist.size()];

                    sbtime = sbtime+"월:";
                    for(int i=0; i<mondaylist.size();i++){  // 시간을 [1] 형태로 저장
                        if(!(mondaylist.get(i).equals("없음"))){  // 시간이 없음이 아닐시.
                            monday[i] = "[" + mondaylist.get(i) + "]";
                            sbtime = sbtime+monday[i];
                        }
                    }
                }
                if(tuesday == 1){
                    tuesdaylist.add(tues_spinner1.getSelectedItem().toString());  //스피너값 monday리스트에저장
                    tuesdaylist.add(tues_spinner2.getSelectedItem().toString());
                    tuesdaylist.add(tues_spinner3.getSelectedItem().toString());

                    String[] tuesday = new String[tuesdaylist.size()];

                    sbtime = sbtime+"화:";
                    for(int i=0; i<tuesdaylist.size();i++){  // 시간을 [1] 형태로 저장
                        if(!(tuesdaylist.get(i).equals("없음"))){
                            tuesday[i] = "[" + tuesdaylist.get(i) + "]";
                            sbtime = sbtime+tuesday[i];
                        }
                    }
                }
                if(wednesday == 1){
                    wednesdaylist.add(wednes_spinner1.getSelectedItem().toString());  //스피너값 monday리스트에저장
                    wednesdaylist.add(wednes_spinner2.getSelectedItem().toString());
                    wednesdaylist.add(wednes_spinner3.getSelectedItem().toString());


                    sbtime = sbtime+"수:";
                    for(int i=0; i<wednesdaylist.size();i++){  // 시간을 [1] 형태로 저장
                        if(!(wednesdaylist.get(i).equals("없음"))){
                            sbtime =sbtime + "[" + wednesdaylist.get(i) + "]";
                        }
                    }
                }
                if(thursday == 1){
                    thursdaylist.add(thurs_spinner1.getSelectedItem().toString());  //스피너값 monday리스트에저장
                    thursdaylist.add(thurs_spinner2.getSelectedItem().toString());
                    thursdaylist.add(thurs_spinner3.getSelectedItem().toString());


                    sbtime = sbtime+"목:";
                    for(int i=0; i<thursdaylist.size();i++){  // 시간을 [1] 형태로 저장
                        if(!(thursdaylist.get(i).equals("없음"))){
                            sbtime =sbtime + "[" + thursdaylist.get(i) + "]";
                        }
                    }
                }
                if(friday == 1){
                    fridaylist.add(fri_spinner1.getSelectedItem().toString());  //스피너값 monday리스트에저장
                    fridaylist.add(fri_spinner2.getSelectedItem().toString());
                    fridaylist.add(fri_spinner3.getSelectedItem().toString());


                    sbtime = sbtime+"금:";
                    for(int i=0; i<fridaylist.size();i++){  // 시간을 [1] 형태로 저장
                        if(!(fridaylist.get(i).equals("없음"))){
                            sbtime =sbtime + "[" + fridaylist.get(i) + "]";
                        }
                    }
                }
                Log.e("결과",sbtime);



                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/Subject.php",
                        SBname,SBprofessor,SBcredit,SBmj,term,userID,sbtime);

                sb_name.setText("");    // 값을 비우기.
                sb_professor.setText("");
                sb_credit.setText("");
                major.setChecked(true);
                chk1.setChecked(false);
                chk2.setChecked(false);
                chk3.setChecked(false);
                chk4.setChecked(false);
                chk5.setChecked(false);
                mon_spinner1.setSelection(0);
                mon_spinner2.setSelection(0);
                mon_spinner3.setSelection(0);
                tues_spinner1.setSelection(0);
                tues_spinner2.setSelection(0);
                tues_spinner3.setSelection(0);
                wednes_spinner1.setSelection(0);
                wednes_spinner2.setSelection(0);
                wednes_spinner3.setSelection(0);
                thurs_spinner1.setSelection(0);
                thurs_spinner2.setSelection(0);
                thurs_spinner3.setSelection(0);
                fri_spinner1.setSelection(0);
                fri_spinner2.setSelection(0);
                fri_spinner3.setSelection(0);
            }
        });


        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼을 클릭 했을시 색상을 변경한다.
                calculatorButton.setBackgroundColor(getResources().getColor(R.color.button_color));
                Intent intent = new Intent(MainActivity.this, Calculator.class);
                startActivity(intent);

            }
        });
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼을 클릭 했을시 색상을 변경한다.
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.button_color));
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(intent);


            }
        });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼을 클릭 했을시 색상을 변경한다.

                listButton.setBackgroundColor(getResources().getColor(R.color.button_color));
                Intent intent = new Intent(MainActivity.this, Subject_List.class);
                startActivity(intent);

            }
        });
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            alertBuilder
                    .setTitle("알림")
                    .setMessage(result)
                    .setCancelable(true)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //finish();
                        }
                    });
            AlertDialog dialog = alertBuilder.create();
            dialog.show();
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String SBname = (String)params[1];
            String SBprofessor = (String)params[2];
            String SBcredit = (String)params[3];
            String SBmj = (String)params[4];
            String term = (String)params[5];
            String userID = (String)params[6];
            String sbtime = (String)params[7];

            // 1. php파일을 실행시킬 수 있는 주소와 전송할 데이터를 준비합니다.
            // POST 방식으로 데이터 전달시에는 데이터가 주소에 직접 입력되지 않습니다.

            String serverURL = (String)params[0];

            // HTTP 메시지 본문에 포함되어 전송되기 때문에 따로 데이터를 준비해야 합니다.
            // 전송할 데이터는 "이름=값" 형식이며 여러 개를 보내야 할 경우에는 항목 사이에 &를 추가합니다.
            // 여기에 적어준 이름을 나중에 PHP에서 사용하여 값을 얻게 됩니다.

            String postParameters = "SBname=" + SBname + "&SBprofessor=" + SBprofessor + "&SBcredit=" +SBcredit+
                    "&SBmj="+ SBmj + "&term=" + term + "&userID=" + userID + "&sbtime=" + sbtime;


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
