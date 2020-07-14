package com.cookandroid.school_schedule;

public class LoginInfor {
    private static String userID;



    public static String getUserID() {
        return userID;
    }

    public  void setUserID(String userID) {
        this.userID = userID;
    }
    public LoginInfor(String userID){
        this.userID = userID;
    }
}
