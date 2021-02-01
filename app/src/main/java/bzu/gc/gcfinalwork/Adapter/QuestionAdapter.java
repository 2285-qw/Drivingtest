package bzu.gc.gcfinalwork.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.List;

import bzu.gc.gcfinalwork.R;
import bzu.gc.gcfinalwork.entity.Question;

public class QuestionAdapter extends ArrayAdapter<Question> {
    int resource;
    public QuestionAdapter(Context context, int resource, List<Question> lists) {
        super(context, resource,lists);
        this.resource=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Question question=getItem(position);
        View view;
        if (convertView != null){
            view=convertView;
        }else {
            view= View.inflate(getContext(), resource, null);
        }
        TextView w_tittle=view.findViewById(R.id.wrong_tittle);
        TextView w_answer=view.findViewById(R.id.wrong_answer);
        TextView item1=view.findViewById(R.id.wrong_item1);
        TextView item2=view.findViewById(R.id.wrong_item2);
        TextView item3=view.findViewById(R.id.wrong_item3);
        TextView item4=view.findViewById(R.id.wrong_item4);
        SmartImageView questionimg=view.findViewById(R.id.wrong_questionimg);
        TextView explains=view.findViewById(R.id.wrong_explain);
        w_tittle.setText(question.getQuestion());
        w_answer.setText(String.valueOf(question.getAnswer()));
        item1.setText(question.getItem1());
        item2.setText(question.getItem2());
        item3.setText(question.getItem3());
        item4.setText(question.getItem4());
        questionimg.setImageUrl(question.getUrl());
        explains.setText(question.getExplains());
        Log.d("position",position+"");
        Log.d("id",question.getId()+"");
        return view;
    }
}
