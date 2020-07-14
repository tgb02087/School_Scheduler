package com.cookandroid.school_schedule;

public class ListData {
    private String sb_name;
    private String sb_professor;
    private String sb_credit;
    private String sb_mj;
    private String sb_term;
    private String sb_time;
    private  String id;

    public  String getId() {
        return id;
    }

    public  void setId(String id) {
        this.id = id;
    }



    public String getSb_professor() {
        return sb_professor;
    }

    public void setSb_professor(String sb_professor) {
        this.sb_professor = sb_professor;
    }

    public String getSb_credit() {
        return sb_credit;
    }

    public void setSb_credit(String sb_credit) {
        this.sb_credit = sb_credit;
    }

   public String getSb_mj() {
        return sb_mj;
    }

    public void setSb_mj(String sb_mj) {
        this.sb_mj = sb_mj;
    }

    public String getSb_term() {
        return sb_term;
    }

    public void setSb_term(String sb_term) {
        this.sb_term = sb_term;
    }
    public String getSb_time() {
        return sb_time;
    }

    public void setSb_time(String sb_time) {
        this.sb_time = sb_time;
    }

    public String getSb_name() {
        return sb_name;
    }

    public void setSb_name(String sb_name) {
        this.sb_name = sb_name;
    }
    /*public ListData(String name, String professor, String credit, String mj, String term){
        sb_name=name;
        sb_professor=professor;
        sb_credit=credit;
        sb_mj=mj;
        sb_term=term;
    }*/
}
