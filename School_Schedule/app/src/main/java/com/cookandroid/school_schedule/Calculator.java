package com.cookandroid.school_schedule;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.text.InputType.TYPE_CLASS_NUMBER;

public class Calculator extends AppCompatActivity {

    private EditText sb_count;
    private Button sb_addBtn;
    private int count;
    private LinearLayout layout1;
    Toast toast;
    Context context= this;

    String result[];
    List<Spinner> allEd = new ArrayList<Spinner>();
    List<EditText> allgrade = new ArrayList<EditText>();

    String calbtn = "calbtn"; // 계산버튼 아이디





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);



        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("4.5");
        spinnerArray.add("4.0");
        spinnerArray.add("3.5");
        spinnerArray.add("3.0");
        spinnerArray.add("2.5");
        spinnerArray.add("2.0");
        spinnerArray.add("1.5");
        spinnerArray.add("1.0");
        spinnerArray.add("0");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);

        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Calculator.this);

        Toolbar toolbar = findViewById(R.id.sc_toobar);
        setTitle("계산기");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sb_count = (EditText) findViewById(R.id.sb_count);
        sb_addBtn = (Button) findViewById(R.id.sb_addBtn);

        final LinearLayout layout1 = (LinearLayout) findViewById(R.id.sbaddLayout);

        sb_addBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                String test = sb_count.getText().toString();
                test=test.trim();

                if (test.getBytes().length<=0) {    // 과목수 입력칸이 비어있을시 메세지출력.
                    toast.makeText(Calculator.this, "입력 값이 비었습니다.", Toast.LENGTH_SHORT)
                            .show();
                    Log.e("RESULT", "입력값빔");
                }
                else {
                    sb_addBtn.setEnabled(false);
                    sb_addBtn.setBackgroundColor(R.color.CHKbutton_color);

                    LinearLayout layout3 = new LinearLayout(context);
                    layout3.setOrientation(LinearLayout.VERTICAL);
                    layout1.addView(layout3);

                    count = Integer.parseInt(sb_count.getText().toString());
                    for (int j = 0; j < count; j++) {

                        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        params1.gravity = Gravity.CENTER;
                        params1.topMargin = 20;

                        LinearLayout layout2 = new LinearLayout(context);
                        layout2.setLayoutParams(params1);
                        layout2.setOrientation(LinearLayout.HORIZONTAL);
                        layout1.addView(layout2);

                        Spinner spinner1 = new Spinner(context);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     // 스피너 아이템 생성
                        spinner1.setAdapter(adapter);
                        spinner1.setId(j);
                        allEd.add(spinner1);
                        layout2.addView(spinner1);


                        EditText editText2 = new EditText(context);   // 이수학점 edit 만들기
                        editText2.setHint("이수학점" + String.valueOf(j));
                        editText2.setId(j);
                        editText2.setInputType(TYPE_CLASS_NUMBER);
                        allgrade.add(editText2);
                        layout2.addView(editText2);



                    }
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.topMargin = 20;
                    params.width =900;
                    params.gravity = Gravity.CENTER;

                    Button button = new Button(context);    // 계산을 위한 버튼 만들기
                    button.setText("계산하기");
                    button.setLayoutParams(params);
                    button.setTextAppearance(context, R.style.addButton);
                    button.setBackgroundColor(Color.rgb(153,0,76));
                    layout3.addView(button);



                    Button button1 = new Button(context);    // 초기화를 위한 버튼 만들기
                    button1.setText("초기화");
                    button1.setLayoutParams(params);
                    button1.setTextAppearance(context, R.style.addButton);
                    button1.setBackgroundColor(Color.rgb(153,0,76));
                    layout3.addView(button1);

                    Log.e("RESULT", "버튼클릭");

                    button1.setOnClickListener(new View.OnClickListener() {         //초기화 버튼을 누르면 다시 계산기 어플을 실행
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Calculator.this, Calculator.class);
                            startActivity(intent);
                        }
                    });

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            double hj3 = 0, hj2 = 0, hj1 = 0, hjadd = 0;
                            double hjcl = 0;

                            /*for(int i=0; i<allEd.size();i++){       // 성적 입력칸이 비었을시 메세지 출력
                                if(allgrade.get(i).getText().toString().equals("")){    //  이수학점값이 비었을시
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }

                            }*/

                            String[] item1 = new String[allEd.size()];
                            //double[] item2 = new double[allgrade.size()];
                            String[] item2 = new String[allgrade.size()];
                            for (int i = 0; i < allEd.size(); i++) {
                                    item1[i] = allEd.get(i).getSelectedItem().toString();   // 스피너값 item에 넣기
                                    item2[i] = allgrade.get(i).getText().toString();
                            }
                            for (int i = 0; i < item2.length; i++) {
                                if (item2[i].equals("3")) {
                                    hj3 = hj3 + Double.parseDouble(item1[i]);
                                    hjadd = hjadd + 3.0;
                                } else if (item2[i].equals("2")) {
                                    hj2 = hj2 + Double.parseDouble(item1[i]);
                                    hjadd = hjadd + 2.0;
                                } else if (item2[i].equals("1")) {
                                    hj1 = hj1 + Double.parseDouble(item1[i]);
                                    hjadd = hjadd + 1.0;
                                }else{
                                    alertBuilder
                                            .setTitle("알림")
                                            .setMessage("이수학점이 올바른 형식이 아닙니다")
                                            .setCancelable(true)
                                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(Calculator.this, Calculator.class);
                                                    startActivity(intent);
                                                }
                                            });
                                    AlertDialog dialog = alertBuilder.create();
                                    dialog.show();
                                    finish();
                                }
                            }
                            hjcl = ((hj3 * 3.0) + (hj2 * 2.0) + (hj1)) / hjadd;
                            double cl = (Math.round(hjcl*100)/100.0);   //소수점 반올림

                            /*alertBuilder
                                    .setTitle("계산 결과")
                                    .setMessage("이수학점 : "+ Math.round(hjadd) +" 총학점 : " + cl)
                                    .setCancelable(true)
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            for(int i=0; i<allEd.size(); i++) {
                                                allEd.get(i).setSelection(0);
                                                allgrade.get(i).setText("");
                                            }
                                        }
                                    });
                            AlertDialog dialog = alertBuilder.create();
                            dialog.show();*/

                            /*Toast toast = Toast.makeText(context, "이수학점 : "+ Math.round(hjadd) +" 총학점 : " + cl, Toast.LENGTH_SHORT);
                            toast.show();*/

                            LinearLayout layout3 = new LinearLayout(context);     // 계산 결과 textview에 추가
                            layout3.setOrientation(LinearLayout.HORIZONTAL);
                            layout1.addView(layout3);
                            TextView textView = new TextView(context);
                            textView.setText("이수학점 : "+ Math.round(hjadd) +" 총학점 : " + cl);
                            textView.setTextSize(30);
                            layout3.addView(textView);
                            for(int i=0; i<allEd.size(); i++){
                                allEd.get(i).setSelection(0);
                                allgrade.get(i).setText("");
                            }
                        }
                    });
                }

            }
        });
    }

}
