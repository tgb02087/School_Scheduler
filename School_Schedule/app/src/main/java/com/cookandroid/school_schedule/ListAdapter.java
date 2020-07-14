package com.cookandroid.school_schedule;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.CustomViewHolder> {

    private ArrayList<ListData> mList = null;
    private Activity context = null;

    public ListAdapter(Activity context, ArrayList<ListData> list) {
        this.context = context;
        this.mList = list;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView professor;
        protected TextView credit;
        protected TextView mj;
        protected Button editBtn;
        protected TextView term;
        protected TextView time;
        protected TextView id;


        public CustomViewHolder(View view) {
            super(view);

            this.name = (TextView) view.findViewById(R.id.sbname_list);
            this.professor = (TextView) view.findViewById(R.id.sbprofessor_list);
            this.credit = (TextView) view.findViewById(R.id.sbcredit_list);
            this.mj = (TextView) view.findViewById(R.id.sbmj_list);
            this.term = (TextView) view.findViewById(R.id.sbterm_list);
            this.time = (TextView) view.findViewById(R.id.sbtime_list);
            this.id = (TextView) view.findViewById(R.id.sb_id);
            this.editBtn = (Button)view.findViewById(R.id.item_editBtn);

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Subject_Edit.class);

                    intent.putExtra("name",name.getText().toString());
                    intent.putExtra("professor",professor.getText().toString());
                    intent.putExtra("credit",credit.getText().toString());
                    intent.putExtra("mj",mj.getText().toString());
                    intent.putExtra("term",term.getText().toString());
                    intent.putExtra("time",time.getText().toString());
                    intent.putExtra("id",id.getText().toString());
                    //intent.putExtra("id",listData.getId()); // 과목 id값 넘기기

                    context.startActivity(intent);
                }
            });
        }
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subject_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.name.setText(mList.get(position).getSb_name());
        viewholder.professor.setText(mList.get(position).getSb_professor());
        viewholder.credit.setText(mList.get(position).getSb_credit());
        viewholder.mj.setText(mList.get(position).getSb_mj());
        viewholder.term.setText(mList.get(position).getSb_term());
        viewholder.time.setText(mList.get(position).getSb_time());
        viewholder.id.setText(mList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
