package com.cookandroid.school_schedule;

import android.content.Context;
import android.widget.TextView;

public class Schedule {
    private String monday[] = new String[14];
    private String tuesday[] = new String[14];
    private String wednesday[] = new String[14];
    private String thursday[] = new String[14];
    private String friday[] = new String[14];

    public Schedule() {
        for (int i = 0; i < 14; i++) {
            monday[i]= "";
            tuesday[i] = "";
            wednesday[i] = "";
            thursday[i] = "";
            friday[i] = "";
        }
    }
    public void Schedule_chk(AutoResizeTextView[] monday, AutoResizeTextView[] tuesday, AutoResizeTextView[] wednesday,
                             AutoResizeTextView[] thursday, AutoResizeTextView[] friday, Context context) {
        for (int i = 0; i < 14; i++) {
            monday[i].setText("");
            tuesday[i].setText("");
            wednesday[i].setText("");
            thursday[i].setText("");
            friday[i].setText("");

        }
    }

    public void addSchedule (String sbname,  String sbmj, String scheduleText){
        int temp;
        // 월:[3][4] 이런식으로 오면 3 4를 파싱한다
        if((temp=scheduleText.indexOf("월")) > -1) {     // scheduleText에 '월' 이라는 단어가 포함되어있으면 그 위치를 temp에 반환.
            temp +=2;
            int starPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++){ // scheduleText 길이보다 작을때 또는 현재위치가 :이 아닐때 까지 반복.
                if(scheduleText.charAt(i)=='['){
                    starPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    monday[Integer.parseInt(scheduleText.substring(starPoint + 1, endPoint))] = "("+sbmj+")"+sbname;
                    // moday 배열안에 현재 넣을 데이터의 교시에 해당하는 숫자 데이터를 넣어준다.
                }
            }
        }
        if((temp=scheduleText.indexOf("화")) > -1) {     // scheduleText에 '월' 이라는 단어가 포함되어있으면 그 위치를 temp에 반환.
            temp +=2;
            int starPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++){ // scheduleText 길이보다 작을때 또는 현재위치가 :이 아닐때 까지 반복.
                if(scheduleText.charAt(i)=='['){
                    starPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    tuesday[Integer.parseInt(scheduleText.substring(starPoint + 1, endPoint))] = "("+sbmj+")"+sbname;
                    // moday 배열안에 현재 넣을 데이터의 교시에 해당하는 숫자 데이터를 넣어준다.
                }
            }
        }
        if((temp=scheduleText.indexOf("수")) > -1) {     // scheduleText에 '월' 이라는 단어가 포함되어있으면 그 위치를 temp에 반환.
            temp +=2;
            int starPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++){ // scheduleText 길이보다 작을때 또는 현재위치가 :이 아닐때 까지 반복.
                if(scheduleText.charAt(i)=='['){
                    starPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    wednesday[Integer.parseInt(scheduleText.substring(starPoint + 1, endPoint))] = "("+sbmj+")"+sbname;
                    // moday 배열안에 현재 넣을 데이터의 교시에 해당하는 숫자 데이터를 넣어준다.
                }
            }
        }
        if((temp=scheduleText.indexOf("목")) > -1) {     // scheduleText에 '월' 이라는 단어가 포함되어있으면 그 위치를 temp에 반환.
            temp +=2;
            int starPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++){ // scheduleText 길이보다 작을때 또는 현재위치가 :이 아닐때 까지 반복.
                if(scheduleText.charAt(i)=='['){
                    starPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    thursday[Integer.parseInt(scheduleText.substring(starPoint + 1, endPoint))] = "("+sbmj+")"+sbname;
                    // moday 배열안에 현재 넣을 데이터의 교시에 해당하는 숫자 데이터를 넣어준다.
                }
            }
        }
        if((temp=scheduleText.indexOf("금")) > -1) {     // scheduleText에 '월' 이라는 단어가 포함되어있으면 그 위치를 temp에 반환.
            temp +=2;
            int starPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++){ // scheduleText 길이보다 작을때 또는 현재위치가 :이 아닐때 까지 반복.
                if(scheduleText.charAt(i)=='['){
                    starPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    friday[Integer.parseInt(scheduleText.substring(starPoint + 1, endPoint))] = "("+sbmj+")"+sbname;
                    // moday 배열안에 현재 넣을 데이터의 교시에 해당하는 숫자 데이터를 넣어준다.
                }
            }
        }
    }
    public  boolean validate(String scheduleText){  // 날짜 데이터가 현재 시간표의 데이터와 중복되지 않는지 체크해준다
        if(scheduleText.equals(""))
        {
            return true;    // 데이터가 비어있는 경우 true값 반환.
        }
        int temp;
        if((temp=scheduleText.indexOf("월")) > -1) {     // scheduleText에 '월' 이라는 단어가 포함되어있으면 그 위치를 temp에 반환.
            temp +=2;
            int starPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++){ // scheduleText 길이보다 작을때 또는 현재위치가 :이 아닐때 까지 반복.
                if(scheduleText.charAt(i)=='['){
                    starPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!monday[Integer.parseInt(scheduleText.substring(starPoint + 1, endPoint))].equals(""))      // 현재 값이 공백이 아니라면 어떤값이 있단는 것이라서 false를 반환.
                    {
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("화")) > -1) {     // scheduleText에 '월' 이라는 단어가 포함되어있으면 그 위치를 temp에 반환.
            temp +=2;
            int starPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++){ // scheduleText 길이보다 작을때 또는 현재위치가 :이 아닐때 까지 반복.
                if(scheduleText.charAt(i)=='['){
                    starPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!tuesday[Integer.parseInt(scheduleText.substring(starPoint + 1, endPoint))].equals(""))      // 현재 값이 공백이 아니라면 어떤값이 있단는 것이라서 false를 반환.
                    {
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("수")) > -1) {     // scheduleText에 '월' 이라는 단어가 포함되어있으면 그 위치를 temp에 반환.
            temp +=2;
            int starPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++){ // scheduleText 길이보다 작을때 또는 현재위치가 :이 아닐때 까지 반복.
                if(scheduleText.charAt(i)=='['){
                    starPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!wednesday[Integer.parseInt(scheduleText.substring(starPoint + 1, endPoint))].equals(""))      // 현재 값이 공백이 아니라면 어떤값이 있단는 것이라서 false를 반환.
                    {
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("목")) > -1) {     // scheduleText에 '월' 이라는 단어가 포함되어있으면 그 위치를 temp에 반환.
            temp +=2;
            int starPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++){ // scheduleText 길이보다 작을때 또는 현재위치가 :이 아닐때 까지 반복.
                if(scheduleText.charAt(i)=='['){
                    starPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!thursday[Integer.parseInt(scheduleText.substring(starPoint + 1, endPoint))].equals(""))      // 현재 값이 공백이 아니라면 어떤값이 있단는 것이라서 false를 반환.
                    {
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("금")) > -1) {     // scheduleText에 '월' 이라는 단어가 포함되어있으면 그 위치를 temp에 반환.
            temp +=2;
            int starPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++){ // scheduleText 길이보다 작을때 또는 현재위치가 :이 아닐때 까지 반복.
                if(scheduleText.charAt(i)=='['){
                    starPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!friday[Integer.parseInt(scheduleText.substring(starPoint + 1, endPoint))].equals(""))      // 현재 값이 공백이 아니라면 어떤값이 있단는 것이라서 false를 반환.
                    {
                        return false;
                    }
                }
            }
        }
        return true;    // 모든과정을 거쳐 중복이 없다면 true 반환.
    }
    public void setting(AutoResizeTextView[] monday, AutoResizeTextView[] tuesday, AutoResizeTextView[] wednesday, AutoResizeTextView[] thursday, AutoResizeTextView[] friday, Context context) {
        int maxLength = 0;
        String maxString = "";
        for(int i=0; i<14; i++){
            if(this.monday[i].length() > maxLength){
                maxLength = this.monday[i].length();
                maxString = this.monday[i];
            }
            if(this.tuesday[i].length() > maxLength){
                maxLength = this.tuesday[i].length();
                maxString = this.tuesday[i];
            }
            if(this.wednesday[i].length() > maxLength){
                maxLength = this.wednesday[i].length();
                maxString = this.wednesday[i];
            }
            if(this.thursday[i].length() > maxLength){
                maxLength = this.thursday[i].length();
                maxString = this.thursday[i];
            }
            if(this.friday[i].length() > maxLength){
                maxLength = this.friday[i].length();
                maxString = this.friday[i];
            }


        }
        for(int i=0; i<14; i++){
            if(!this.monday[i].equals("")){
                monday[i].setText(this.monday[i]);
                monday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            }
            else{
                monday[i].setText(maxString);
                monday[i].setTextColor(context.getResources().getColor(R.color.colorWhite));
            }
            if(!this.tuesday[i].equals("")){
                tuesday[i].setText(this.tuesday[i]);
                tuesday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            }
            else{
                tuesday[i].setText(maxString);
                tuesday[i].setTextColor(context.getResources().getColor(R.color.colorWhite));
            }
            if(!this.wednesday[i].equals("")){
                wednesday[i].setText(this.wednesday[i]);
                wednesday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            }
            else{
                wednesday[i].setText(maxString);
                wednesday[i].setTextColor(context.getResources().getColor(R.color.colorWhite));
            }
            if(!this.thursday[i].equals("")){
                thursday[i].setText(this.thursday[i]);
                thursday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            }
            else{
                thursday[i].setText(maxString);
                thursday[i].setTextColor(context.getResources().getColor(R.color.colorWhite));
            }
            if(!this.friday[i].equals("")){
                friday[i].setText(this.friday[i]);
                friday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            }
            else{
                friday[i].setText(maxString);
                friday[i].setTextColor(context.getResources().getColor(R.color.colorWhite));
            }
            monday[i].resizeText();     // 크기가 커도 자동으로 텍스트뷰의 크기를 맞춰준다.
            tuesday[i].resizeText();
            wednesday[i].resizeText();
            thursday[i].resizeText();
            friday[i].resizeText();
        }
    }
}