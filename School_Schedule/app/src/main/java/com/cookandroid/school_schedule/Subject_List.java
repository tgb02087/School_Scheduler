package com.cookandroid.school_schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Subject_List extends AppCompatActivity {

    private static String IP_ADDRESS = "tgb02087.cafe24.com";
    private static String TAG = "phptest";

    private ArrayList<ListData> arrayList;
    private ListAdapter listadapter;
    private RecyclerView recyclerView;

    private Button list_viewBtn;
    private Spinner list_spinner;
    private String mJsonString, userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject__list);

        Toolbar toolbar = findViewById(R.id.sc_toobar);
        setTitle("강의목록");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userID = LoginInfor.getUserID();    //유저아이디 정보 받아오기
                list_viewBtn = (Button) findViewById(R.id.list_viewBtn);
        list_spinner = (Spinner) findViewById(R.id.list_sb_spinner);

        recyclerView = (RecyclerView) findViewById(R.id.subject_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();

        listadapter = new ListAdapter(this, arrayList);
        RecyclerDecoration spaceDecoration = new RecyclerDecoration(40);
        recyclerView.addItemDecoration(spaceDecoration);
        recyclerView.setAdapter(listadapter);




        list_viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String term = list_spinner.getSelectedItem().toString();   //spinner값 받아오기.

                arrayList.clear();
                listadapter.notifyDataSetChanged();
                Log.e(TAG, "response code - " + userID);
                Log.e(TAG, "response code - " + term);

                GetData task = new GetData();
                task.execute( "http://" + IP_ADDRESS + "/Subject_List.php", userID, term);
            }
        });
    }

    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Subject_List.this,
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
                //mTextViewResult.setText(errorString);
            }
            else {

                mJsonString = result;
                Log.e(TAG, "response code - " + result);
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {


            //String postParameters = params[1];
            String userID = (String)params[1];
            String sbterm = (String)params[2];
            //String term = (String)params[2];

            String serverURL = (String)params[0];

            //String postParameters = "userID=" + userID + "&term" + term;
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

        String TAG_JSON="webnautes";
        String TAG_NAME = "SBname";
        String TAG_PROFESSOR ="SBprofessor";
        String TAG_CREDIT = "SBcredit";
        String TAG_MJ = "SBmj";
        String TAG_TERM = "term";
        String TAG_TIME = "sbtime";
        String TAG_ID = "id";




        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String name = item.getString(TAG_NAME);
                String professor = item.getString(TAG_PROFESSOR);
                String credit = item.getString(TAG_CREDIT);
                String mj = item.getString(TAG_MJ);
                String term = item.getString(TAG_TERM);
                String time = item.getString(TAG_TIME);
                String id = item.getString(TAG_ID);


                ListData listData = new ListData();

                listData.setSb_name(name);
                listData.setSb_professor(professor);
                listData.setSb_credit(credit);
                listData.setSb_mj(mj);
                listData.setSb_term(term);
                listData.setSb_time(time);
                listData.setId(id);

                arrayList.add(listData);
                listadapter.notifyDataSetChanged();
            }



        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}